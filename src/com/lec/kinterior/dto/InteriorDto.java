package com.lec.kinterior.dto;
import java.sql.Date;
public class InteriorDto {
	private int pid;
	private String aid;
	private String ptitle;
	private String pcontent;
	private String pfilename;
	private Date prdate;
	public InteriorDto(int pid, String aid, String ptitle, String pcontent, String pfilename, Date prdate) {
		this.pid = pid;
		this.aid = aid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pfilename = pfilename;
		this.prdate = prdate;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPfilename() {
		return pfilename;
	}
	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}
	public Date getPrdate() {
		return prdate;
	}
	public void setPrdate(Date prdate) {
		this.prdate = prdate;
	}
	@Override
	public String toString() {
		return "InteriorDto [pid=" + pid + ", aid=" + aid + ", ptitle=" + ptitle + ", pcontent=" + pcontent
				+ ", pfilename=" + pfilename + ", prdate=" + prdate + "]";
	}
}
