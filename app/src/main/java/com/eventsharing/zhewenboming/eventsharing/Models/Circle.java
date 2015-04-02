package com.eventsharing.zhewenboming.eventsharing.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhewen on 3/14/2015.
 */
public class Circle {
    private String _name;
    private int id;
    private List<User> users;



    public Circle(String name){
        _name = name;
        users = new ArrayList<>();
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    //additional methods

}