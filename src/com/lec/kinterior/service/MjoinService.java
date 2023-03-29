package com.lec.kinterior.service;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lec.kinterior.dao.MemberDao;
import com.lec.kinterior.dto.MemberDto;
public class MjoinService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		Date mbirth = null;
		String mbirthYear = request.getParameter("mbirthYear");
		String mbirthMonth = request.getParameter("mbirthMonth");
		String mbirthDay = request.getParameter("mbirthDay");
		String memail = request.getParameter("memail");
		String mgender = request.getParameter("mgender");
		String maddress = request.getParameter("maddress");
		if(!mbirthYear.equals("") && !mbirthMonth.equals("") && !mbirthDay.equals("")) {
			String mbirthStr = mbirthYear + "-" + ((mbirthMonth.length()==1)? "0"+mbirthMonth : mbirthMonth)
					+ "-" + ((mbirthDay.length()==1)? "0"+mbirthDay : mbirthDay);
			mbirth = Date.valueOf(mbirthStr);
		}
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.midConfirm(mid);
		if(result == MemberDao.MEMBER_NONEXISTENT) { // 가입가능한 아이디라서 회원가입 진행
			MemberDto member = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, mgender, maddress, null);
			result = mDao.joinMember(member);
			if(result==MemberDao.SUCCESS) { // 회원가입성공
				request.setAttribute("joinResult", "회원가입이 완료되었습니다.");
				//HttpSession session = request.getSession();
				//session.setAttribute("mid", mid);
			}else if(result==MemberDao.FAIL) { // 회원가입 실패
				request.setAttribute("joinErrorMsg", "정보가 너무 길어 회원가입 실패되었습니다.");
			}
		}else {
			request.setAttribute("joinErrorMsg", "중복된 아이디는 회원가입이 불가합니다.");
		}
	}
}
