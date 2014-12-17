package com.ptcm.model;

import java.sql.Date;

public class Schedule {
	private int bus;
	private Date initialtime, finishtime;
	private int id, idcar, iddriver, idstaff;
	private String name;
	public Schedule(int bus, Date initialtime, Date finishtime, int id,
			int idcar, int iddriver, int idstaff, String name) {
		super();
		this.bus = bus;
		this.initialtime = initialtime;
		this.finishtime = finishtime;
		this.id = id;
		this.idcar = idcar;
		this.iddriver = iddriver;
		this.idstaff = idstaff;
		this.name = name;
	}
	public int getBus() {
		return bus;
	}
	public void setBus(int bus) {
		this.bus = bus;
	}
	public Date getInitialtime() {
		return initialtime;
	}
	public void setInitialtime(Date initialtime) {
		this.initialtime = initialtime;
	}
	public Date getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcar() {
		return idcar;
	}
	public void setIdcar(int idcar) {
		this.idcar = idcar;
	}
	public int getIddriver() {
		return iddriver;
	}
	public void setIddriver(int iddriver) {
		this.iddriver = iddriver;
	}
	public int getIdstaff() {
		return idstaff;
	}
	public void setIdstaff(int idstaff) {
		this.idstaff = idstaff;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Schedule [bus=" + bus + ", initialtime=" + initialtime
				+ ", finishtime=" + finishtime + ", id=" + id + ", idcar="
				+ idcar + ", iddriver=" + iddriver + ", idstaff=" + idstaff
				+ ", name=" + name + "]";
	}
	
}
