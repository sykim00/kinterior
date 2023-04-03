package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lec.kinterior.dao.MemberDao;
import com.lec.kinterior.dto.MemberDto;
public class MLoginService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("next", request.getParameter("next"));
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.memberLogin(mid, mpw);
		if(result==MemberDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mid);
			session.setAttribute("member", member);
		}else {
			request.setAttribute("mLoginErrorMsg", "아이디와 비밀번호를 확인해주세요.");
		}
	}
}