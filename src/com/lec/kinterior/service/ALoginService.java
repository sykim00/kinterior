package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.AdminDao;
import com.lec.kinterior.dto.AdminDto;
public class ALoginService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.adminLoginChk(aid, apw);
		if(result == AdminDao.SUCCESS) { // 
			HttpSession session = request.getSession();
			AdminDto adto = aDao.getAdmin(aid);
			session.setAttribute("admin", adto);
			request.setAttribute("adminLogin", "관리자 계정으로 로그인했습니다.");
		}else {
			request.setAttribute("adminLoginResult", "관리자 계정 로그인 실패했습니다.");
		}
	}
}