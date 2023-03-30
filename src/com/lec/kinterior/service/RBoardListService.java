package com.lec.kinterior.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dao.ReviewBoardDao;
import com.lec.kinterior.dto.ConsultantDto;
import com.lec.kinterior.dto.ReviewBoardDto;

public class RBoardListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")!=null) {
				pageNum = (String) request.getAttribute("pageNum");
			}else {
				pageNum = "1";
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5, BLOCKSIZE = 5;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow + PAGESIZE -1;
		ReviewBoardDao rDao = ReviewBoardDao.getInstance();
		ArrayList<ReviewBoardDto> reviewList = rDao.reviewList(startRow, endRow);
		request.setAttribute("reviewList", reviewList);
		int reviewTotCnt = rDao.reviewTotCnt();
		int pageTotCnt = (int)Math.ceil((double)reviewTotCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageTotCnt) {
			endPage = pageTotCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE); //블록사이즈
		request.setAttribute("pageNum", currentPage); //현재페이지
		request.setAttribute("startPage", startPage); //시작페이지
		request.setAttribute("endPage", endPage); // 끝페이지
		request.setAttribute("reviewTotCnt", reviewTotCnt); // 총 글갯수
		request.setAttribute("pageTotCnt", pageTotCnt); // 총 페이지
	}
}
