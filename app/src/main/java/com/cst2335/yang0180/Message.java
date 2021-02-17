package com.cst2335.yang0180;

public class Message {

    private long id;
    private boolean isSend;
    private String content;

    public Message(boolean isSend, String content) {
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
