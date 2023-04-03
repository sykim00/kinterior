package com.lec.kinterior.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dto.ConsultantDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ConsultModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("consultantBoardFileUp");
		int maxSize = 1024*1024*10;
		String cfilename = "", dbcfilename = null;
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
				String param = params.nextElement();
				cfilename = mRequest.getFilesystemName(param);
				dbcfilename = mRequest.getParameter("dbcfilename");
				if(cfilename==null) {
					cfilename = dbcfilename;
				}
				int cid = Integer.parseInt(mRequest.getParameter("cid"));
				String ctitle = mRequest.getParameter("ctitle");
				String ccontent = mRequest.getParameter("ccontent");
				String cip = request.getRemoteAddr();
				ConsultantDao cDao = ConsultantDao.getInstance();
				ConsultantDto cdto = new ConsultantDto(cid, null, null, null, null, ctitle, ccontent, cfilename, null, 0, 0, 0, cip);
				int result = cDao.consultantModify(cdto);
				if(result==ConsultantDao.SUCCESS) { // 글 수정 완료
					request.setAttribute("conModifyResult", "글 수정 성공");
				}else {
					request.setAttribute("conModifyResult", "글 수정 실패");
				}
				request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(dbcfilename!=null && !cfilename.equals(dbcfilename)) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path + "/" + cfilename);
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("/Users/gimsoyeon/sykim/source/project_1/k_interior/WebContent/consultantBoardFileUp/"+cfilename);
				byte[] bs = new byte[(int) serverFile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs,0,readByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}