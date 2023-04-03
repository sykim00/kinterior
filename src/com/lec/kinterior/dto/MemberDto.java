package com.lec.kinterior.dto;
import java.sql.Date;
public class MemberDto {
	private String mid;
	private String mpw;
	private String mname;
	private String mtel;
	private Date mbirth;
	private String memail;
	private String mgender;
	private String maddress;
	private Date mrdate;
	public MemberDto(String mid, String mpw, String mname, String mtel, Date mbirth, String memail, String mgender,
			String maddress, Date mrdate) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mtel = mtel;
		this.mbirth = mbirth;
		this.memail = memail;
		this.mgender = mgender;
		this.maddress = maddress;
		this.mrdate = mrdate;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public Date getMrdate() {
		return mrdate;
	}
	public void setMrdate(Date mrdate) {
		this.mrdate = mrdate;
	}
	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mtel=" + mtel + ", mbirth=" + mbirth
				+ ", memail=" + memail + ", mgender=" + mgender + ", maddress=" + maddress + ", mrdate=" + mrdate + "]";
	}
}