package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.NoticeDao;
import com.lec.kinterior.dto.NoticeDto;
public class NoticeModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto notice = new NoticeDto(nid, null, null, ntitle, ncontent, null);
		int result = nDao.noticeModify(notice);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("nModifyResult", "글 수정 성공");
		}else {
			request.setAttribute("nModifyResult", "글 수정 실패");
		}
	}
}
