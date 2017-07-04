package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/2.
 */
public class GroupRecruit {
    private Integer grid;
    private Integer convener;
    private String recruit_information;
    private String group_name;
    private Integer cid;
    private Integer isSubmitted; // 0为未提交批准 1为已被老师批准

    public Integer getGrid() {
        return grid;
    }

    public void setGrid(Integer grid) {
        this.grid = grid;
    }

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

    public Integer getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Integer isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
}
