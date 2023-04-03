package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.MemberDao;
public class MidConfirmService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.midConfirm(mid);
		if(result == MemberDao.MEMBER_EXISTENT) {
			request.setAttribute("midResult","<p>이미 사용중이거나 탈퇴한 아이디입니다.</p>");
		}else {
			request.setAttribute("midResult", "사용가능한 아이디입니다.");
		}
	}
}
