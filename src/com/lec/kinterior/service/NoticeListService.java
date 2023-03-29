package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.NoticeDao;
public class NoticeListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE = 4;
		int startRow = (currentPage - 1)*PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		NoticeDao nDao = NoticeDao.getInstance();
		request.setAttribute("NoticeBoard", nDao.noticeList(startRow, endRow));
		int noticeTotCnt = nDao.NoticeTotCnt(); // 총 글 갯수
		int nTotPageCnt = (int) Math.ceil((double)noticeTotCnt/PAGESIZE); // 총 페이지 갯수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage > nTotPageCnt) {
			endPage = nTotPageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("nTotPageCnt", nTotPageCnt);
		request.setAttribute("noticeTotCnt", noticeTotCnt);
	}
}
