package com.lec.kinterior.dto;
import java.sql.Date;
public class NoticeDto {
	private int nid;
	private String aid;
	private String aname;
	private String ntitle;
	private String ncontent;
	private Date nrdate;
	public NoticeDto(int nid, String aid, String aname, String ntitle, String ncontent, Date nrdate) {
		this.nid = nid;
		this.aid = aid;
		this.aname = aname;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nrdate = nrdate;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Date getNrdate() {
		return nrdate;
	}
	public void setNrdate(Date nrdate) {
		this.nrdate = nrdate;
	}
	@Override
	public String toString() {
		return "NoticeDto [nid=" + nid + ", aid=" + aid + ", aname=" + aname + ", ntitle=" + ntitle + ", ncontent="
				+ ncontent + ", nrdate=" + nrdate + "]";
	}
}
