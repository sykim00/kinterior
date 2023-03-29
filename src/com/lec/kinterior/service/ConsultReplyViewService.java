package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dto.ConsultantDto;
public class ConsultReplyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		ConsultantDao cdao = ConsultantDao.getInstance();
		ConsultantDto originconBoard = cdao.consModify_reply(cid);
		request.setAttribute("originconBoard", originconBoard);
	}
}
