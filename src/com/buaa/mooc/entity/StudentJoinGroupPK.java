package com.buaa.mooc.entity;

import java.io.Serializable;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentJoinGroupPK implements Serializable {
    private Integer grid;
    private Integer sid;

    public Integer getGrid() {
        return grid;
    }

    public void setGrid(Integer grid) {
        this.grid = grid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
