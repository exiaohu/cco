package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/6/28.
 */
public class File {
    private Integer id;
    private Integer uploadby;
    private String filename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUploadby() {
        return uploadby;
    }

    public void setUploadby(Integer uploadby) {
        this.uploadby = uploadby;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
