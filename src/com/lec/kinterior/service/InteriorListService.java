package com.lec.kinterior.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.kinterior.dao.InteriorDao;
import com.lec.kinterior.dto.InteriorDto;
public class InteriorListService implements Service {
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
		final int PAGESIZE = 6, BLOCKSIZE = 5;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow + PAGESIZE -1;
		InteriorDao idao = InteriorDao.getInstance();
		ArrayList<InteriorDto> interiorList = idao.interiorList(startRow, endRow);
		request.setAttribute("interiorList", interiorList);
		int interiorTotCnt = idao.interiorTotCnt();
		int pageTotCnt = (int)Math.ceil((double)interiorTotCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageTotCnt) {
			endPage = pageTotCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE); //블록사이즈
		request.setAttribute("pageNum", currentPage); //현재페이지
		request.setAttribute("startPage", startPage); //시작페이지
		request.setAttribute("endPage", endPage); // 끝페이지
		request.setAttribute("interiorTotCnt", interiorTotCnt); // 총 글갯수
		request.setAttribute("pageTotCnt", pageTotCnt); // 총 페이지
	}
}
