package com.eventsharing.zhewenboming.eventsharing.Models;

/**
 * Created by Zhewen on 4/1/2015.
 */
public class Comment {
    private int id;
    private int eventId;
    private int userId;
    private String content;

    public Comment(int uId, int eId, String c){
        this.eventId = eId;
        this.userId = uId;
        this.content = c;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
