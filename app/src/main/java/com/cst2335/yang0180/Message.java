package com.cst2335.yang0180;

import java.io.Serializable;

public class Message implements Serializable {

    private long id;
    private boolean isSend;
    private String content;

    public Message(boolean isSend, String content) {
        this.isSend = isSend;
        this.content = content;
    }

    public Message(long id, boolean isSend, String content) {
        this.id = id;
        this.isSend = isSend;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
