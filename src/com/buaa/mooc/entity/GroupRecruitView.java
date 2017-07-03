package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/3.
 */
public class GroupRecruitView {
    private Integer grid;
    private String recruit_information;
    private String group_name;
    private Integer cid;
    private Long s_count;

    public Integer getGrid() {
        return grid;
    }

    public void setGrid(Integer grid) {
        this.grid = grid;
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

    public Long getS_count() {
        return s_count;
    }

    public void setS_count(Long s_count) {
        this.s_count = s_count;
    }
}
