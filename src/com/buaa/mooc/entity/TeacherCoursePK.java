package com.buaa.mooc.entity;

import java.io.Serializable;

public class TeacherCoursePK implements Serializable {
	private Integer tid;
	private Integer cid;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
