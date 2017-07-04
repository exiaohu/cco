package com.buaa.mooc.entity;

import java.sql.Date;

public class Course {

    private Integer cid;
    private String cname;
    private Integer termId;
    private Integer credit;
    private Date beginDate;
    private Date endDate;
    private String address;
    private String outline;
    private Integer fid;
    private Integer group_mem_max;
    private Integer group_mem_min;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {this.fid = fid; }

    public Integer getGroup_mem_max() {
        return group_mem_max;
    }

    public void setGroup_mem_max(Integer group_mem_max) {
        this.group_mem_max = group_mem_max;
    }

    public Integer getGroup_mem_min() {
        return group_mem_min;
    }

    public void setGroup_mem_min(Integer group_mem_min) {
        this.group_mem_min = group_mem_min;
    }
}
