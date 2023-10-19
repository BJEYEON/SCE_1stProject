package com.lec.sce.dto;

public class AdminDto {
	private String aid;
	private String apw;
	private String aname;
	private String atel;
	public AdminDto() {}
	public AdminDto(String aid, String apw, String aname, String atel) {
		super();
		this.aid = aid;
		this.apw = apw;
		this.aname = aname;
		this.atel = atel;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApw() {
		return apw;
	}
	public void setApw(String apw) {
		this.apw = apw;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAtel() {
		return atel;
	}
	public void setAtel(String atel) {
		this.atel = atel;
	}
	@Override
	public String toString() {
		return "AdminDto [aid=" + aid + ", apw=" + apw + ", aname=" + aname + ", atel=" + atel + "]";
	}
}
