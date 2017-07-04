package com.buaa.mooc.entity;

import java.sql.Timestamp;

/**
 * Created by windrises on 2017/6/28.
 */
public class Homework {
    private Integer homeworkId;
    private Integer courseId;
    private String homeworkName;
    private Timestamp startTime;
    private Timestamp deadLine;
    private Integer submitMaxTimes;
    private String homeworkInformation;
    private Integer proportion;

    public Homework() {
        super();
    }

    public Integer getId() {
        return homeworkId;
    }

    public void setId(Integer id) {
        this.homeworkId = id;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer id) {
        this.homeworkId = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer id) {
        this.courseId = id;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String name) {
        this.homeworkName = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp date) {
        this.startTime = date;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp date) {
        this.deadLine = date;
    }

    public Integer getSubmitMaxTimes() {
        return submitMaxTimes;
    }

    public void setSubmitMaxTimes(Integer times) {
        this.submitMaxTimes = times;
    }

    public String getHomeworkInformation() {
        return homeworkInformation;
    }

    public void setHomeworkInformation(String homeworkInformation) {
        this.homeworkInformation = homeworkInformation;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }
}
