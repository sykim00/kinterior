package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.MemberDto;
import com.lec.kinterior.dto.ReviewBoardDto;
public class RBoardContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		ReviewBoardDao rDao = ReviewBoardDao.getInstance();
		ReviewBoardDto review = rDao.getReivewBoard(rid);
		request.setAttribute("review", review);
	}
}
