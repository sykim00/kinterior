package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.NoticeDao;
public class NoticeDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao ndao = NoticeDao.getInstance();
		int result = ndao.deleteNotice(nid);
		request.setAttribute("deleteResult", "글 삭제 성공"); 
	}
}
