package com.lec.kinterior.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.InteriorDao;

public class InteriorsDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pid = Integer.parseInt(request.getParameter("pid"));
		InteriorDao idao = InteriorDao.getInstance();
		int result = idao.deleteInterior(pid);
		if(result==InteriorDao.SUCCESS) {
			request.setAttribute("interiorDelete", "글 삭제했습니다.");
		}else {
			request.setAttribute("interiorDelete", "글 삭제 실패했습니다.");
		}
	}

}
