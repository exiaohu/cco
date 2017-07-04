package com.buaa.mooc.entity;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by lenovo on 2017/7/3.
 */
public class Resource {
    private Integer rid;
    private Integer cid;
    private Integer uploadby;
    private String rname;
    private Timestamp attendTime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getUploadby() {
        return uploadby;
    }

    public void setUploadby(Integer uploadby) {
        this.uploadby = uploadby;
    }

    public Timestamp getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Timestamp attendTime) {
        this.attendTime = attendTime;
    }
}
