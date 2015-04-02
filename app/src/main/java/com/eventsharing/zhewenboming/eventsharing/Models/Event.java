package com.eventsharing.zhewenboming.eventsharing.Models;

/**
 * Created by Zhewen on 4/1/2015.
 */
public class Event {
    private int id;
    private String title;
    private String desciption;
    private int ownerId;

    public Event(String t, String d, int oId){
        this.title = t;
        this.desciption = d;
        this.ownerId = oId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

}