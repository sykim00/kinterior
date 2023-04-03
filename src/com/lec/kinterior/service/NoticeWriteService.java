package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.NoticeDao;
import com.lec.kinterior.dto.AdminDto;
import com.lec.kinterior.dto.NoticeDto;
public class NoticeWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		HttpSession session = request.getSession();
		AdminDto admin = (AdminDto)session.getAttribute("admin");
		if(admin!=null) {
			String aid = admin.getAid();
			NoticeDao nDao = NoticeDao.getInstance();
			NoticeDto ndto = new NoticeDto(0, aid, null, ntitle, ncontent, null);
			int result = nDao.writeNotice(ndto);
			if(result == NoticeDao.SUCCESS) {
				request.setAttribute("noticeResult", "공지사항 글쓰기 성공");
			}else {
				request.setAttribute("noticeResult", "공지사항 글쓰기 실패");
			}			
		}else {
			request.setAttribute("noticeResult", "관리자만 로그인하여 글작성 가능합니다.");
		}
	}
}
