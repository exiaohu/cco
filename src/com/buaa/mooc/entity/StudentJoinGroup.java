package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentJoinGroup {
    private Integer granted; // 0为未确定 1为同意 2为拒绝
    private StudentJoinGroupPK pk;

    public Integer getGranted() {
        return granted;
    }

    public void setGranted(Integer granted) {
        this.granted = granted;
    }

    public StudentJoinGroupPK getPk() {
        return pk;
    }

    public void setPk(StudentJoinGroupPK pk) {
        this.pk = pk;
    }
}
