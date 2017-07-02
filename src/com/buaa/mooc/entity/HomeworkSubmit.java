package com.buaa.mooc.entity;

import java.sql.Timestamp;

/**
 * Created by windrises on 2017/6/29.
 */
public class HomeworkSubmit {
    private HomeworkSubmitPK pk;
    private Integer fid;
    private Timestamp submitTime;
    private String submitContent;
    private Integer isCorrect;
    private Float score;
    private String remark;
    private Integer submitTimes;
    public HomeworkSubmit() {
        super();
    }
    public HomeworkSubmit(HomeworkSubmitPK homeworkSubmitPk, Integer fid, Timestamp submitTime,
                          String submitContent, Integer isCorrect, Float score, String remark, Integer submitTimes) {
        super();
        this.pk = homeworkSubmitPk;
        this.fid = fid;
        this.submitTime = submitTime;
        this.submitContent = submitContent;
        this.isCorrect = isCorrect;
        this.score = score;
        this.remark = remark;
        this.submitTimes = submitTimes;
        //System.out.println("----------------" + fid);
    }

    public HomeworkSubmitPK getPk() { return pk; }
    public void setPk(HomeworkSubmitPK pk) {
        this.pk = pk;
    }
    public Integer getFid() { return fid; }
    public void setFid(Integer fid) { this.fid = fid; }
    public Timestamp getSubmitTime(){
        return submitTime;
    }
    public void setSubmitTime(Timestamp date) {this.submitTime = date; }
    public String getSubmitContent() {return submitContent; }
    public void setSubmitContent(String submitContent) {this.submitContent = submitContent; }
    public Integer getIsCorrect() {return isCorrect; }
    public void setIsCorrect(Integer isCorrect) {this.isCorrect = isCorrect; }
    public Float getScore() {return score; }
    public void setScore(Float score) {this.score = score; }
    public String getRemark() {return remark; }
    public void setRemark(String remark) {this.remark = remark; }
    public Integer getSubmitTimes() {return submitTimes; }
    public void setSubmitTimes(Integer submitTimes) {this.submitTimes = submitTimes; }

}
