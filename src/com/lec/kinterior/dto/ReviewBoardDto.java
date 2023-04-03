package com.lec.kinterior.dto;

import java.sql.Date;

public class ReviewBoardDto {
	private int rid;
	private String mid;
	private String mname;
	private String rtitle;
	private String rcontent;
	private String rphoto;
	private Date rdate;
	private int rhit;
	private String rip;
	public ReviewBoardDto(int rid, String mid, String mname, String rtitle, String rcontent, String rphoto, Date rdate,
			int rhit, String rip) {
		this.rid = rid;
		this.mid = mid;
		this.mname = mname;
		this.rtitle = rtitle;
		this.rcontent = rcontent;
		this.rphoto = rphoto;
		this.rdate = rdate;
		this.rhit = rhit;
		this.rip = rip;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getRtitle() {
		return rtitle;
	}
	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRphoto() {
		return rphoto;
	}
	public void setRphoto(String rphoto) {
		this.rphoto = rphoto;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public int getRhit() {
		return rhit;
	}
	public void setRhit(int rhit) {
		this.rhit = rhit;
	}
	public String getRip() {
		return rip;
	}
	public void setRip(String rip) {
		this.rip = rip;
	}
	@Override
	public String toString() {
		return "ReviewBoardDto [rid=" + rid + ", mid=" + mid + ", mname=" + mname + ", rtitle=" + rtitle + ", rcontent="
				+ rcontent + ", rphoto=" + rphoto + ", rdate=" + rdate + ", rhit=" + rhit + ", rip=" + rip + "]";
	}
}