package com.lec.sce.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int bnum;
	private String mid;
	private String mname;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private String bimage;
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bindent;
	private String bip;
	public BoardDto() {}
	public BoardDto(int bnum, String mid, String mname, String btitle, String bcontent, Timestamp bdate, String bimage,
			int bhit, int bgroup, int bstep, int bindent, String bip) {
		super();
		this.bnum = bnum;
		this.mid = mid;
		this.mname = mname;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bimage = bimage;
		this.bhit = bhit;
		this.bgroup = bgroup;
		this.bstep = bstep;
		this.bindent = bindent;
		this.bip = bip;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
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
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public Timestamp getBdate() {
		return bdate;
	}
	public void setBdate(Timestamp bdate) {
		this.bdate = bdate;
	}
	public String getBimage() {
		return bimage;
	}
	public void setBimage(String bimage) {
		this.bimage = bimage;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getBstep() {
		return bstep;
	}
	public void setBstep(int bstep) {
		this.bstep = bstep;
	}
	public int getBindent() {
		return bindent;
	}
	public void setBindent(int bindent) {
		this.bindent = bindent;
	}
	public String getBip() {
		return bip;
	}
	public void setBip(String bip) {
		this.bip = bip;
	}
	@Override
	public String toString() {
		return "BoardDto [bnum=" + bnum + ", mid=" + mid + ", mname=" + mname + ", btitle=" + btitle + ", bcontent="
				+ bcontent + ", bdate=" + bdate + ", bimage=" + bimage + ", bhit=" + bhit + ", bgroup=" + bgroup
				+ ", bstep=" + bstep + ", bindent=" + bindent + ", bip=" + bip + "]";
	}
	
	
}
