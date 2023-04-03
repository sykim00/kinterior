package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.NoticeDao;
import com.lec.kinterior.dto.MemberDto;
import com.lec.kinterior.dto.NoticeDto;
public class NoticeContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto ndto = nDao.getNoticeContent(nid);
		request.setAttribute("noticeContent", ndto);
	}
}