package com.lec.kinterior.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.NoticeDao;
import com.lec.kinterior.dto.NoticeDto;

public class NoticeModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto ndto = nDao.noticeModify_View(nid);
		request.setAttribute("noticeModify", ndto);
	}
}
