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
import com.lec.kinterior.service.MAllViewService;
import com.lec.kinterior.service.MLoginService;
import com.lec.kinterior.service.MLogoutService;
import com.lec.kinterior.service.MModifyService;
import com.lec.kinterior.service.MidConfirmService;
import com.lec.kinterior.service.MjoinService;
import com.lec.kinterior.service.NoticeListService;
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
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}else if(command.equals("/midConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
		}else if(command.equals("/join.do")) {
			service = new MjoinService();
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/loginView.do")) {
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
			viewPage = "member/mypageModify.jsp";
		}else if(command.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/allView.do")) {
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(command.equals("/consultList.do")) {
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
		}else if(command.equals("/noticeList.do")) {
			service = new NoticeListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}