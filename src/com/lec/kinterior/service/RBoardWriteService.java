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

import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.MemberDto;
import com.lec.kinterior.dto.ReviewBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class RBoardWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("reviewPhotoUp");
		int maxSize = 1024*1024;
		String rphoto = "";
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				rphoto = mRequest.getFilesystemName(param);
			}
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto) session.getAttribute("member");
			if(member!=null) {
				String mid = member.getMid();
				String rtitle = mRequest.getParameter("rtitle");
				String rcontent = mRequest.getParameter("rcontent");
				rphoto = rphoto == null ? "noimg.jpg" : rphoto;
				String rip = request.getRemoteAddr();
				ReviewBoardDao rDao = ReviewBoardDao.getInstance();
				ReviewBoardDto rdto = new ReviewBoardDto(0, mid, null, rtitle, rcontent, rphoto, null, maxSize, rip);
				int result = rDao.writeReview(rdto);
				if(result == ReviewBoardDao.SUCCESS) {
					request.setAttribute("ReviewWriteResult", "후기 글 감사합니다.");
				}else {
					request.setAttribute("ReviewWriteResult", "후기 글 작성 실패했습니다.");
				}
			}else {
				request.setAttribute("ReviewWriteResult", "시공이력이 있는 회원만 글쓰기 가능합니다.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(rphoto!=null) {
			InputStream is = null;
			OutputStream os = null;
			File serverfile = new File(path + "/" + rphoto);
			try {
				is = new FileInputStream(serverfile);
				os = new FileOutputStream("C:/sykim/source/first_project/kinterior/WebContent/reviewPhotoUp/"+rphoto);
				byte[] bs = new byte[(int) serverfile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs, 0, readByteCnt);
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
