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

import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dto.AdminDto;
import com.lec.kinterior.dto.ConsultantDto;
import com.lec.kinterior.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class ConsultReplyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("consultantBoardFileUp");
		int maxSize = 1024*1024*10;
		String cfilename = "";
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			cfilename = mRequest.getFilesystemName(param);
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto) session.getAttribute("member");
			String ctitle, ccontent, cip;
			int cgroup, cstep, cindent;
			if(member!=null) {
				String mid = member.getMid();
				ctitle = mRequest.getParameter("ctitle");
				ccontent = mRequest.getParameter("ccontent");
				cip = request.getRemoteAddr();
				cgroup = Integer.parseInt(mRequest.getParameter("cgroup"));
				cstep = Integer.parseInt(mRequest.getParameter("cstep"));
				cindent = Integer.parseInt(mRequest.getParameter("cindent"));
				ConsultantDao cdao = ConsultantDao.getInstance();
				ConsultantDto cdto = new ConsultantDto(0, mid, null, null, null, ctitle, ccontent, cfilename, null, cgroup, cstep, cindent, cip);
				int result = cdao.consultantReply(cdto);
				if(result == ConsultantDao.SUCCESS) {  // 답변글 쓰기 성공
					request.setAttribute("conReplyResult", "회원 답변글 쓰기 성공");
				}else {
					request.setAttribute("conReplyResult", "회원 답변글 쓰기 실패");
				}
			}else {
				request.setAttribute("conReplyResult", "로그인해야 이용가능합니다.");
			}
			AdminDto admin = (AdminDto) session.getAttribute("admin");
			if(admin!=null) {
				String aid = admin.getAid();
				ctitle = mRequest.getParameter("ctitle");
				ccontent = mRequest.getParameter("ccontent");
				cip = request.getRemoteAddr();
				cgroup = Integer.parseInt(mRequest.getParameter("cgroup"));
				cstep = Integer.parseInt(mRequest.getParameter("cstep"));
				cindent = Integer.parseInt(mRequest.getParameter("cindent"));
				ConsultantDao cdao = ConsultantDao.getInstance();
				ConsultantDto cdto = new ConsultantDto(0, null, null, aid, null, ctitle, ccontent, cfilename, null, cgroup, cstep, cindent, cip);
				int result = cdao.consultantReply(cdto);
				if(result==ConsultantDao.SUCCESS) {
					request.setAttribute("conReplyResult", "관리자 답변글 쓰기 성공");
					System.out.println("관리자 답변글 쓰기 성공");
				}else {
					request.setAttribute("conReplyResult", "관리자 답변글 쓰기 실패");
					System.out.println("관리자 답변글 쓰기 실패");
				}
			}else {
				request.setAttribute("conReplyResult", "관리자로 로그인해야 이용가능합니다.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(cfilename!=null) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path+"/"+cfilename);
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/sykim/source/first_project/kinterior/WebContent/consultantBoardFileUp/"+cfilename);
				byte[] bs = new byte[(int) serverFile.length()];
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