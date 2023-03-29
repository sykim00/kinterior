package com.lec.kinterior.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.kinterior.dao.ConsultantDao;
import com.lec.kinterior.dto.AdminDto;
import com.lec.kinterior.dto.ConsultantDto;
import com.lec.kinterior.dto.MemberDto;
public class ConsultContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		ConsultantDao cDao = ConsultantDao.getInstance();
		ConsultantDto cdto = cDao.getConsultantDto(cid);
		request.setAttribute("consultantContent", cdto);
		// 이글을 읽을 수 있는 권한 : 로그인한 id==cdto.mid 이거나 내가 쓴 그룹과 같을 경우
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		String mid = null;
		if(member != null) {
			mid = member.getMid();
		}
		ArrayList<Integer> cgroups =  cDao.cgroupList(mid);
		String readOk = "fail";
		if(mid!=null && mid.equals(cdto.getMid())) {
			readOk = "success";
		}
		for(Integer cgroup : cgroups) {
			if(cgroup == cdto.getCgroup()) {
				readOk = "success";
			}
		}
		AdminDto admin = (AdminDto) session.getAttribute("admin");
		if(admin!=null) {
			readOk = "success";
		}
		request.setAttribute("readOk", readOk);
	}
}
