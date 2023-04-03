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
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.InteriorDao;
import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.AdminDto;
import com.lec.kinterior.dto.InteriorDto;
import com.lec.kinterior.dto.MemberDto;
import com.lec.kinterior.dto.ReviewBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class InteriorWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("interiorFileUp");
		int maxSize = 1024*1024;
		String pfilename = "";
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				pfilename = mRequest.getFilesystemName(param);
			}
			HttpSession session = request.getSession();
			AdminDto admin = (AdminDto) session.getAttribute("admin");
			if(admin!=null) {
				String aid = admin.getAid();
				String ptitle = mRequest.getParameter("ptitle");
				String pcontent = mRequest.getParameter("pcontent");
				pfilename = pfilename == null ? "noimg.jpg" : pfilename;
				InteriorDao idao = InteriorDao.getInstance();
				InteriorDto interior = new InteriorDto(0, aid, ptitle, pcontent, pfilename, null);
				int result = idao.writeInterior(interior);
				if(result == InteriorDao.SUCCESS) {
					request.setAttribute("interiorWriteResult", "시공사례 글 작성 완료");
				}else {
					request.setAttribute("interiorWriteResult", "시공사례 글 작성 실패");
				}
			}else {
				request.setAttribute("interiorWriteResult", "관리자로 로그인 해주세요.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage() + "파일업로드실패");
		}
		if(pfilename!=null) {
			InputStream is = null;
			OutputStream os = null;
			File serverfile = new File(path + "/" + pfilename);
			try {
				is = new FileInputStream(serverfile);
				os = new FileOutputStream("C:/sykim/source/first_project/kinterior/WebContent/interiorFileUp/"+pfilename);
				byte[] bs = new byte[(int) serverfile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs, 0, readByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage() + "파일 업로드 실패 ");
			}finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "파일 업로드실패");
				}
			}
		}
	}
}
