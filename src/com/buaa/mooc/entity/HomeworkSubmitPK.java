package com.buaa.mooc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by windrises on 2017/6/29.
 */
public class HomeworkSubmitPK implements Serializable {
    private Integer hid;

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    private Integer sid;


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
