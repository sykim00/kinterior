package com.lec.kinterior.service;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dto.MemberDto;
public class MypageModifyView implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String mgender = member.getMgender();
		Date mbirth = member.getMbirth();
		System.out.println(mbirth);
		System.out.println(mgender);
		String mbirthStr, mbirthYear, mbirthMonth, mbirthDay = null;
		if(mbirth != null) {
			mbirthStr = mbirth.toString();
			mbirthYear = mbirthStr.substring(0, 4);
			mbirthMonth = mbirthStr.substring(5,7);
			mbirthDay = mbirthStr.substring(mbirthStr.lastIndexOf("-") + 1);
			request.setAttribute("mbirthYear", mbirthYear);
			request.setAttribute("mbirthMonth", mbirthMonth);
			request.setAttribute("mbirthDay", mbirthDay);
			System.out.println(mbirthYear);
			System.out.println(mbirthMonth);
			System.out.println(mbirthDay);
		}
	}
}
