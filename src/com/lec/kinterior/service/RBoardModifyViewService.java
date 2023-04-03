package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.ReviewBoardDto;
public class RBoardModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		ReviewBoardDao rdao = ReviewBoardDao.getInstance();
		ReviewBoardDto rdto = rdao.review_Modify(rid);
		request.setAttribute("reviewModify", rdto);
	}
}
