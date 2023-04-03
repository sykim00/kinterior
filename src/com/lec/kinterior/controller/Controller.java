package com.lec.kinterior.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.service.ALoginService;
import com.lec.kinterior.service.ConsultContentService;
import com.lec.kinterior.service.ConsultDeleteService;
import com.lec.kinterior.service.ConsultModifyService;
import com.lec.kinterior.service.ConsultModifyViewService;
import com.lec.kinterior.service.ConsultReplyService;
import com.lec.kinterior.service.ConsultReplyViewService;
import com.lec.kinterior.service.ConsultWriteService;
import com.lec.kinterior.service.ConsultantListService;
import com.lec.kinterior.service.InteriorContentService;
import com.lec.kinterior.service.InteriorListService;
import com.lec.kinterior.service.InteriorModifyService;
import com.lec.kinterior.service.InteriorModifyViewService;
import com.lec.kinterior.service.InteriorWriteService;
import com.lec.kinterior.service.InteriorsDeleteService;
import com.lec.kinterior.service.MAllViewService;
import com.lec.kinterior.service.MLoginService;
import com.lec.kinterior.service.MLogoutService;
import com.lec.kinterior.service.MModifyService;
import com.lec.kinterior.service.MWithdrawalService;
import com.lec.kinterior.service.MidConfirmService;
import com.lec.kinterior.service.MjoinService;
import com.lec.kinterior.service.MypageModifyView;
import com.lec.kinterior.service.NoticeContentService;
import com.lec.kinterior.service.NoticeDeleteService;
import com.lec.kinterior.service.NoticeListService;
import com.lec.kinterior.service.NoticeModifyService;
import com.lec.kinterior.service.NoticeModifyViewService;
import com.lec.kinterior.service.NoticeWriteService;
import com.lec.kinterior.service.RBoardContentService;
import com.lec.kinterior.service.RBoardDeleteService;
import com.lec.kinterior.service.RBoardListService;
import com.lec.kinterior.service.RBoardModifyService;
import com.lec.kinterior.service.RBoardModifyViewService;
import com.lec.kinterior.service.RBoardWriteService;
import com.lec.kinterior.service.Service;
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) { // 메인
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) { // 회원가입 
			viewPage = "member/join.jsp";
		}else if(command.equals("/midConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
		}else if(command.equals("/join.do")) {
			service = new MjoinService();
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/loginView.do")) { // 로그인
			viewPage = "member/login.jsp";
		}else if(command.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/logout.do")) {
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/mypageView.do")){
			viewPage = "member/mypage.jsp";
		}else if(command.equals("/modifyView.do")) {
			service = new MypageModifyView();
			service.execute(request, response);
			viewPage = "member/mypageModify.jsp";
		}else if(command.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/withdrawal.do")) {
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/adminLoginView.do")) { // 관리자
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/allView.do")) {
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(command.equals("/consultList.do")) { // 견적문의 게시판
			service = new ConsultantListService();
			service.execute(request, response);
			viewPage = "consultant/consultantList.jsp";
		}else if(command.equals("/consultWriteView.do")) {
			viewPage = "consultant/consultantWrite.jsp";
		}else if(command.equals("/consultWrite.do")) {
			service = new ConsultWriteService();
			service.execute(request, response);
			viewPage = "consultList.do";
		}else if(command.equals("/consultContent.do")) {
			service = new ConsultContentService();
			service.execute(request, response);
			viewPage = "consultant/consultantContent.jsp";
		}else if(command.equals("/consultModifyView.do")) {
			service = new ConsultModifyViewService();
			service.execute(request, response);
			viewPage = "consultant/consultantModify.jsp";
		}else if(command.equals("/consultModify.do")) {
			service = new ConsultModifyService();
			service.execute(request, response);
			viewPage = "consultList.do";
		}else if(command.equals("/consultDelete.do")) {
			service = new ConsultDeleteService();
			service.execute(request, response);
			viewPage = "consultList.do";
		}else if(command.equals("/consultReplyView.do")) {
			service = new ConsultReplyViewService();
			service.execute(request, response);
			viewPage = "consultant/consultReply.jsp";
		}else if(command.equals("/consultReply.do")) {
			service = new ConsultReplyService();
			service.execute(request, response);
			viewPage = "consultList.do";
		}else if(command.equals("/noticeList.do")) { // 공지사항 게시판 
			service = new NoticeListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		}else if(command.equals("/noticeWriteView.do")) {
			viewPage = "notice/noticeWrite.jsp";
		}else if(command.equals("/noticeWrite.do")) {
			service = new NoticeWriteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		}else if(command.equals("/noticeContent.do")) {
			service = new NoticeContentService();
			service.execute(request, response);
			viewPage = "notice/noticeContent.jsp";
		}else if(command.equals("/noticeModifyView.do")) {
			service = new NoticeModifyViewService();
			service.execute(request, response);
			viewPage = "notice/noticeModify.jsp";
		}else if(command.equals("/noticeModify.do")) {
			service = new NoticeModifyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		}else if(command.equals("/noticeDelete.do")) {
			service = new NoticeDeleteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		}else if(command.equals("/rboardList.do")) { // 리뷰게시판
			service = new RBoardListService();
			service.execute(request, response);
			viewPage = "reviewBoard/rboardList.jsp";
		}else if(command.equals("/rboardWriteView.do")) {
			viewPage = "reviewBoard/rboardWrite.jsp";
		}else if(command.equals("/rboardWrite.do")) {
			service = new RBoardWriteService();
			service.execute(request, response);
			viewPage = "rboardList.do";
		}else if(command.equals("/rboardContent.do")) {
			service = new RBoardContentService();
			service.execute(request, response);
			viewPage = "reviewBoard/rboardContent.jsp";
		}else if(command.equals("/rboardModifyView.do")) {
			service = new RBoardModifyViewService();
			service.execute(request, response);
			viewPage = "reviewBoard/rboardModify.jsp";
		}else if(command.equals("/rboardModify.do")) {
			service = new RBoardModifyService();
			service.execute(request, response);
			viewPage = "rboardList.do";
		}else if(command.equals("/rboardDelete.do")) {
			service = new RBoardDeleteService();
			service.execute(request, response);
			viewPage = "rboardList.do";
		}else if(command.equals("/interiorList.do")) {  // 인테리어  
			service = new InteriorListService();
			service.execute(request, response);
			viewPage = "interior/interiorList.jsp";
		}else if(command.equals("/interiorWriteView.do")) {
			viewPage = "interior/interiorWrite.jsp";
		}else if(command.equals("/interiorWrite.do")) {
			service = new InteriorWriteService();
			service.execute(request, response);
			viewPage = "interiorList.do";
		}else if(command.equals("/interiorContent.do")) {
			service = new InteriorContentService();
			service.execute(request, response);
			viewPage = "interior/interiorContent.jsp";
		}else if(command.equals("/interiorModifyView.do")) {
			service = new InteriorModifyViewService();
			service.execute(request, response);
			viewPage = "interior/interiorModify.jsp";
		}else if(command.equals("/interiorModify.do")) {
			service = new InteriorModifyService();
			service.execute(request, response);
			viewPage = "interiorList.do";
		}else if(command.equals("/interiorDelete.do")) {
			service = new InteriorsDeleteService();
			service.execute(request, response);
			viewPage = "interiorList.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}