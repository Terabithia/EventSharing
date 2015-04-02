package com.eventsharing.zhewenboming.eventsharing.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhewen on 3/14/2015.
 */
public class User {

    private String _userName;
    private String _password;
    private int id;
    private List<Circle> circles;
    private List<User> friends;

    // getters and setters
    public User(String userName, String password){
        _userName = userName;
        _password = password;
        circles = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Circle> getCircle() {
        return circles;
    }

    public void setCircle(List<Circle> circles) {
        //possible changes: call sql to change the data
        this.circles = circles;
    }


    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }


}
