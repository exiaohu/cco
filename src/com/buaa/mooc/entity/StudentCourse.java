package com.buaa.mooc.entity;

public class StudentCourse {

    private StudentCoursePK pk;
    private Double score;
    private Integer gid;
    private Double group_contribute;

    public StudentCoursePK getPk() {
        return pk;
    }

    public void setPk(StudentCoursePK ipk) {
        this.pk = ipk;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Double getGroup_contribute() {
        return group_contribute;
    }

    public void setGroup_contribute(Double group_contribute) {
        this.group_contribute = group_contribute;
    }
}
