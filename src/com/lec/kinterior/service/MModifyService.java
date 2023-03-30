package com.lec.kinterior.service;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lec.kinterior.dao.MemberDao;
import com.lec.kinterior.dto.MemberDto;
public class MModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String dbMpw = request.getParameter("dbMpw");
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		if(mpw.equals("")) {
			mpw = dbMpw;
		}
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		Date mbirth = null;
		String mbirthYear = request.getParameter("mbirthYear");
		String mbirthMonth = request.getParameter("mbirthMonth");
		String mbirthDay = request.getParameter("mbirthDay");
		if(!mbirthYear.equals("") && !mbirthMonth.equals("") && !mbirthDay.equals("")) {
			String mbirthStr = mbirthYear + "-" + ((mbirthMonth.length()==1) ? "0"+ mbirthMonth : mbirthMonth) + "-" + ((mbirthDay.length()==1)? "0" + mbirthDay : mbirthDay); 
			mbirth = Date.valueOf(mbirthStr);
		}
		String memail = request.getParameter("memail");
		String mgender = request.getParameter("mgender");
		String maddress = request.getParameter("maddress");
		MemberDao mDao = MemberDao.getInstance();
		MemberDto member = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, null);
		int result = mDao.memberModify(member);
		if(result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			request.setAttribute("mModifySuccess", "회원정보 수정 성공");
		}else {
			request.setAttribute("mModifyFail", "회원정보 수정 실패");
		}
	}
}