package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.ConsultantDao;
public class ConsultDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cgroup = Integer.parseInt(request.getParameter("cgroup"));
		int cstep = Integer.parseInt(request.getParameter("cstep"));
		int cindent = Integer.parseInt(request.getParameter("cindent"));
		ConsultantDao cDao = ConsultantDao.getInstance();
		int deleteCnt = cDao.deleteConsultant(cgroup, cstep, cindent);
		if(deleteCnt >= ConsultantDao.SUCCESS) {
			request.setAttribute("consultResult", "작성하신 글" + deleteCnt +"개 삭제되었습니다.");
		}else {
			request.setAttribute("consultResult", "작성하신 글이 모두 삭제되지 않았습니다.");
		}
	}
}