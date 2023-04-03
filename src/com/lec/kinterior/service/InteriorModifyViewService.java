package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.InteriorDao;
import com.lec.kinterior.dto.InteriorDto;
public class InteriorModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		InteriorDao idao = InteriorDao.getInstance();
		InteriorDto idto = idao.interiorModify_view(pid);
		request.setAttribute("interiorModify", idto);
	}
}
