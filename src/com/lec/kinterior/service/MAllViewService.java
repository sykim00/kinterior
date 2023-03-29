package com.lec.kinterior.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lec.kinterior.dao.MemberDao;
public class MAllViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE = 5;
		int startRow = (currentPage - 1)*PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		MemberDao mDao = MemberDao.getInstance();
		request.setAttribute("members", mDao.getMemberList(startRow, endRow));
		int memberTotcnt = mDao.getMemberTot();
		int totPageCnt = (int) Math.ceil((double)(memberTotcnt/PAGESIZE));
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > totPageCnt) {
			endPage = totPageCnt;
		}
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totPageCnt", totPageCnt);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("memberTotcnt", memberTotcnt);
	}
}
