package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/3.
 */
public class StudentRecruitView {
    private Integer granted; // 0为未确定 1为同意
    private Integer convener;
    private String recruit_information;
    private String group_name;
    private Integer cid;
    private StudentRecruitViewPK pk;

    public Integer getConvener() {
        return convener;
    }

    public void setConvener(Integer convener) {
        this.convener = convener;
    }

    public String getRecruit_information() {
        return recruit_information;
    }

    public void setRecruit_information(String recruit_information) {
        this.recruit_information = recruit_information;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGranted() {
        return granted;
    }

    public void setGranted(Integer granted) {
        this.granted = granted;
    }

    public StudentRecruitViewPK getPk() {
        return pk;
    }

    public void setPk(StudentRecruitViewPK pk) {
        this.pk = pk;
    }
}
