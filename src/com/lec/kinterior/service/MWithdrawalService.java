package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dao.MemberDao;
import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.MemberDto;
public class MWithdrawalService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mid = null;
		int cnt = 0;
		MemberDto sessionMember = (MemberDto) session.getAttribute("member");
		if(sessionMember!=null) {
			mid = sessionMember.getMid();
		}
		ConsultantDao cdao = ConsultantDao.getInstance();
		int consultant = cdao.preWithdrawalMemberStep(mid); // 견적문의 글 삭제 
		ReviewBoardDao bdao = ReviewBoardDao.getInstance();
		int review = bdao.preWithdrawalMemberStep(mid); // 후기게시판 글 삭제
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.deleteMember(mid);
		session.invalidate();
		cnt = consultant + review;
		if(result==MemberDao.SUCCESS) {
			request.setAttribute("withdrawalResult", "회원탈퇴가 완료되었습니다. 작성하신 글 " + cnt + "개 모두 삭제 되었습니다.");
		}else {
			request.setAttribute("withdrawalResult", "로그인 상태가 아닙니다.");
		}
	}
}
