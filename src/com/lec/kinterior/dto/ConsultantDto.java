package com.lec.kinterior.dto;
import java.sql.Date;
public class ConsultantDto {
	private int cid;
	private String mid;
	private String aid;
	private String ctitle;
	private String ccontent;
	private String cfilename;
	private Date crdate;
	private int cgroup;
	private int cstep;
	private int cindent;
	private String cip;
	public ConsultantDto(int cid, String mid, String aid, String ctitle, String ccontent, String cfilename, Date crdate,
			int cgroup, int cstep, int cindent, String cip) {
		this.cid = cid;
		this.mid = mid;
		this.aid = aid;
		this.ctitle = ctitle;
		this.ccontent = ccontent;
		this.cfilename = cfilename;
		this.crdate = crdate;
		this.cgroup = cgroup;
		this.cstep = cstep;
		this.cindent = cindent;
		this.cip = cip;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCfilename() {
		return cfilename;
	}
	public void setCfilename(String cfilename) {
		this.cfilename = cfilename;
	}
	public Date getCrdate() {
		return crdate;
	}
	public void setCrdate(Date crdate) {
		this.crdate = crdate;
	}
	public int getCgroup() {
		return cgroup;
	}
	public void setCgroup(int cgroup) {
		this.cgroup = cgroup;
	}
	public int getCstep() {
		return cstep;
	}
	public void setCstep(int cstep) {
		this.cstep = cstep;
	}
	public int getCindent() {
		return cindent;
	}
	public void setCindent(int cindent) {
		this.cindent = cindent;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	@Override
	public String toString() {
		return "ConsultantDto [cid=" + cid + ", mid=" + mid + ", aid=" + aid + ", ctitle=" + ctitle + ", ccontent="
				+ ccontent + ", cfilename=" + cfilename + ", crdate=" + crdate + ", cgroup=" + cgroup + ", cstep="
				+ cstep + ", cindent=" + cindent + ", cip=" + cip + "]";
	}
}
