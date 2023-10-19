package com.lec.sce.dto;

public class TestCarApplyDto {
	private int tnum;
	private int cnum;
	private String tname;
	private String ttel;
	private String tgender;
	private String tarea;
	private String thall;
	private String tselmind;
	private String temail;
	public TestCarApplyDto() {}
	public TestCarApplyDto(int tnum, int cnum, String tname, String ttel, String tgender, String tarea, String thall,
			String tselmind, String temail) {
		super();
		this.tnum = tnum;
		this.cnum = cnum;
		this.tname = tname;
		this.ttel = ttel;
		this.tgender = tgender;
		this.tarea = tarea;
		this.thall = thall;
		this.tselmind = tselmind;
		this.temail = temail;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTtel() {
		return ttel;
	}
	public void setTtel(String ttel) {
		this.ttel = ttel;
	}
	public String getTgender() {
		return tgender;
	}
	public void setTgender(String tgender) {
		this.tgender = tgender;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getThall() {
		return thall;
	}
	public void setThall(String thall) {
		this.thall = thall;
	}
	public String getTselmind() {
		return tselmind;
	}
	public void setTselmind(String tselmind) {
		this.tselmind = tselmind;
	}
	public String getTemail() {
		return temail;
	}
	public void setTemail(String temail) {
		this.temail = temail;
	}
	@Override
	public String toString() {
		return "TestCarApplyDto [tnum=" + tnum + ", cnum=" + cnum + ", tname=" + tname + ", ttel=" + ttel + ", tgender="
				+ tgender + ", tarea=" + tarea + ", thall=" + thall + ", tselmind=" + tselmind + ", temail=" + temail
				+ "]";
	}
}
