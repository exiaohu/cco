package com.buaa.mooc.entity;

import java.sql.Timestamp;

/**
 * Created by 我不承认 on 2017/7/4.
 */
public class Message {
    private String content;
    private MessagePK pk;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessagePK getPk() {
        return pk;
    }

    public void setPk(MessagePK pk) {
        this.pk = pk;
    }
}
