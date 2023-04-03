package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.InteriorDao;
import com.lec.kinterior.dto.InteriorDto;
public class InteriorContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		InteriorDao idao = InteriorDao.getInstance();
		InteriorDto interior = idao.getInteriorContents(pid);
		request.setAttribute("interior", interior);
	}
}
