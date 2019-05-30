package com.rest.lightOn.entity;

//import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Point;

@Entity
@Table(name = "tbcctv")
public class Cctv {
	
	@Id
	private @Column(name="cctv_id") int cctv_id;
	private @Column(name="org_id") int org_id;
	private @Column(name="ADMI_CD") String admi_cd;
	
	private @Column(name="ctrl") String ctrl;
	private @Column(name="addr") String addr;
	private @Column(name="addr_s") String addr_s;
	private @Column(name="service") String service;
	private @Column(name="purpose") String purpose;
	private @Column(name="clss") String clss;
	private @Column(name="pixel") String pixel;
	private @Column(name="x_axis") int x_axis;
	private @Column(name="y_axis") int y_axis;
	private @Column(name="std_year") String std_year;
	
	@Column(name = "shape", columnDefinition = "POINT") 
	private Point shape;

	public int getCctv_id() {
		return cctv_id;
	}

	public void setCctv_id(int cctv_id) {
		this.cctv_id = cctv_id;
	}

	public int getOrg_id() {
		return org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}

	public String getAdmi_cd() {
		return admi_cd;
	}

	public void setAdmi_cd(String admi_cd) {
		this.admi_cd = admi_cd;
	}

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr_s() {
		return addr_s;
	}

	public void setAddr_s(String addr_s) {
		this.addr_s = addr_s;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getClss() {
		return clss;
	}

	public void setClss(String clss) {
		this.clss = clss;
	}

	public String getPixel() {
		return pixel;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}

	public int getX_axis() {
		return x_axis;
	}

	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}

	public int getY_axis() {
		return y_axis;
	}

	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}

	public String getStd_year() {
		return std_year;
	}

	public void setStd_year(String std_year) {
		this.std_year = std_year;
	}

	public Point getShape() {
		return shape;
	}

	public void setShape(Point shape) {
		this.shape = shape;
	}

	
	
}
