package com.lec.sce.dto;

public class CarDto {
	private int cnum;
	private String cname;
	private String coil;
	private String cengine;
	private String ctrance;
	private String chp;
	private String ccc;
	private String cyear;
	private String cimage;
	public CarDto() {}
	public CarDto(int cnum, String cname, String coil, String cengine, String ctrance, String chp, String ccc,
			String cyear, String cimage) {
		super();
		this.cnum = cnum;
		this.cname = cname;
		this.coil = coil;
		this.cengine = cengine;
		this.ctrance = ctrance;
		this.chp = chp;
		this.ccc = ccc;
		this.cyear = cyear;
		this.cimage = cimage;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCoil() {
		return coil;
	}
	public void setCoil(String coil) {
		this.coil = coil;
	}
	public String getCengine() {
		return cengine;
	}
	public void setCengine(String cengine) {
		this.cengine = cengine;
	}
	public String getCtrance() {
		return ctrance;
	}
	public void setCtrance(String ctrance) {
		this.ctrance = ctrance;
	}
	public String getChp() {
		return chp;
	}
	public void setChp(String chp) {
		this.chp = chp;
	}
	public String getCcc() {
		return ccc;
	}
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}
	public String getCyear() {
		return cyear;
	}
	public void setCyear(String cyear) {
		this.cyear = cyear;
	}
	public String getCimage() {
		return cimage;
	}
	public void setCimage(String cimage) {
		this.cimage = cimage;
	}
	@Override
	public String toString() {
		return "CarDto [cnum=" + cnum + ", cname=" + cname + ", coil=" + coil + ", cengine=" + cengine + ", ctrance="
				+ ctrance + ", chp=" + chp + ", ccc=" + ccc + ", cyear=" + cyear + ", cimage=" + cimage + "]";
	}
}
