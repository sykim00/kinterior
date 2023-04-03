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

import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.ReviewBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RBoardModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("reviewPhotoUp");
		int maxSize = 1024*1024;
		String rphoto = "", dbrphoto = null;
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			rphoto = mRequest.getFilesystemName(param);
			dbrphoto = mRequest.getParameter("dbrphoto");
			if(rphoto==null) {
				rphoto = dbrphoto;
			}
			int rid = Integer.parseInt(mRequest.getParameter("rid"));
			String rtitle = mRequest.getParameter("rtitle");
			String rcontent = mRequest.getParameter("rcontent");
			String rip = request.getRemoteAddr();
			ReviewBoardDao rdao = ReviewBoardDao.getInstance();
			ReviewBoardDto rdto = new ReviewBoardDto(rid, null, null, rtitle, rcontent, rphoto, null, 0, rip);
			int result = rdao.reviewModify(rdto);
			if(result == ReviewBoardDao.SUCCESS) {
				request.setAttribute("reviewModify", "리뷰글 수정 성공");
			}else {
				request.setAttribute("reviewModify", "리뷰글 수정 실패");
			}
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(dbrphoto!=null && !rphoto.equals(dbrphoto)) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path + "/" + rphoto);
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("/Users/gimsoyeon/sykim/source/project_1/k_interior/WebContent/reviewPhotoUp/"+rphoto);
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
