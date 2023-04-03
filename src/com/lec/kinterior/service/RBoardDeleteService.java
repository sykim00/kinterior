package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.ReviewBoardDao;
public class RBoardDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		ReviewBoardDao rdao = ReviewBoardDao.getInstance();
		int result = rdao.deleteReivew(rid);
		if(result==ReviewBoardDao.SUCCESS) {
			request.setAttribute("reviewDelete", "글 삭제했습니다.");
		}else {
			request.setAttribute("reviewDelete", "글 삭제 실패했습니다.");
		}
	}
}
