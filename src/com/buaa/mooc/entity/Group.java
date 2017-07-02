package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/2.
 */
public class Group {
    private Integer gid;
    private Integer manager_sid;
    private String information;
    private String gname;
    private Integer cid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getManager_sid() {
        return manager_sid;
    }

    public void setManager_sid(Integer manager_sid) {
        this.manager_sid = manager_sid;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
}
