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
import com.lec.kinterior.dto.ConsultantDto;
import com.lec.kinterior.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class ConsultWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("consultantBoardFileUp");
		int maxSize = 1024*1024*10;
		String cfilename = "";
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				cfilename = mRequest.getFilesystemName(param);
			}
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto) session.getAttribute("member");
			if(member!=null) {
				String mid = member.getMid();
				String ctitle = mRequest.getParameter("ctitle");
				String ccontent = mRequest.getParameter("ccontent");
				String cip = request.getRemoteAddr();
				ConsultantDao cDao = ConsultantDao.getInstance();
				ConsultantDto consDto = new ConsultantDto(0, mid, null, null, null, ctitle, ccontent, cfilename, null, 0, 0, 0, cip);
				int result = cDao.writeConsultant(consDto);
				if(result == ConsultantDao.SUCCESS) { // 글쓰기 성공 
					request.setAttribute("consultantWriteResult", "견적문의 감사합니다. 빠른시일내로 답변드리겠습니다.");
				}else { // 글쓰기 실패
					request.setAttribute("consultantWriteResult", "글쓰기 실패했습니다. 제목을 입력해주세요.");
				}
			}else {
				request.setAttribute("consultantWriteResult", "회원가입한 회원만 글쓰기 가능합니다.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(cfilename!=null) {
			InputStream is = null;
			OutputStream os = null;
			File serverfile = new File(path + "/" + cfilename);
			try {
				is = new FileInputStream(serverfile);
				os = new FileOutputStream("C:/sykim/source/first_project/kinterior/WebContent/consultantBoardFileUp/"+cfilename);
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
