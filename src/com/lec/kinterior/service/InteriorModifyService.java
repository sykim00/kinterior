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
import com.lec.kinterior.dao.InteriorDao;
import com.lec.kinterior.dto.InteriorDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class InteriorModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("interiorFileUp");
		int maxSize = 1024*1024;
		String pfilename = "", dbpfilename =null;
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			pfilename = mRequest.getFilesystemName(param);
			dbpfilename = mRequest.getParameter("dbpfilename");
			if(pfilename==null) {
				pfilename = dbpfilename;
			}
			int pid = Integer.parseInt(mRequest.getParameter("pid"));
			String ptitle = mRequest.getParameter("ptitle");
			String pcontent = mRequest.getParameter("pcontent");
			InteriorDao idao = InteriorDao.getInstance();
			InteriorDto idto = new InteriorDto(pid, null, ptitle, pcontent, pfilename, null);
			int result = idao.interiorModify(idto);
			if(result == InteriorDao.SUCCESS) {
				request.setAttribute("interiorModify", "시공사례 글 수정 성공");
			}else {
				request.setAttribute("interiorWriteResult", "시공사례 글 수정 실패");
			}
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(dbpfilename!=null && !pfilename.equals(dbpfilename)) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path + "/" + pfilename);
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("/Users/gimsoyeon/sykim/source/project_1/k_interior/WebContent/interiorFileUp/"+pfilename);
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
