package com.buaa.mooc.entity;

import java.io.Serializable;

public class StudentCoursePK implements Serializable {

    private Integer sid;
    private Integer cid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


}
