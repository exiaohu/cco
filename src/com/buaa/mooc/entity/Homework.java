package com.buaa.mooc.entity;

import java.sql.Timestamp;

/**
 * Created by windrises on 2017/6/28.
 */
public class Homework {
    private int homeworkId;
    private int courseId;
    private String homeworkName;
    private Timestamp startTime;
    private Timestamp deadLine;
    private int submitMaxTimes;
    private String homeworkInformation;
    private Integer proportion;


    public Homework() {
        super();
    }

    public int getId() {
        return homeworkId;
    }

    public void setId(int id) {
        this.homeworkId = id;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int id) {
        this.homeworkId = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int id) {
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

    public int getSubmitMaxTimes() {
        return submitMaxTimes;
    }

    public void setSubmitMaxTimes(int times) {
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
