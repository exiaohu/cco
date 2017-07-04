package com.buaa.mooc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 我不承认 on 2017/7/4.
 */
public class MessagePK implements Serializable {
    private Integer sid;
    private Timestamp insert_time;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Timestamp getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Timestamp insert_time) {
        this.insert_time = insert_time;
    }
}
