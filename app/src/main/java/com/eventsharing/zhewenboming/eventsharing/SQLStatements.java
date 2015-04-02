package com.eventsharing.zhewenboming.eventsharing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhewen on 4/1/2015.
 */
public class SQLStatements {
    public static final int DATABASE_VERSION = 1;

    //all table names
    public static final String CIRCLE_TABLE = "Circles";
    public static final String EVENT_TABLE = "Events";
    public static final String COMMENTS_TABLE = "Comments";
    public static final String USER_TABLE = "Users";
    public static final String USER_CIRCLE_TABLE = "User_Circle";
    public static final String USER_EVENT_TABLE = "User_Event";
    public static final String EVENT_COMMENT_TABLE = "Event_Comment";
    public static final String FRIEND_TABLE = "Friends";

    //all sql queries for creating tables
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT)";
    public static final String CREATE_EVENT_TABLE = "CREATE TABLE " + EVENT_TABLE + "(id INTEGER PRIMARY KEY, title TEXT, description TEXT, ownerId INTEGER)";
    public static final String CREATE_COMMENTS_TABLE = "CREATE TABLE " + COMMENTS_TABLE + "(id INTEGER PRIMARY KEY,eventId INTEGER, content TEXT, userId INTEGER)";
    public static final String CREATE_CIRCLE_TABLE = "CREATE TABLE " + CIRCLE_TABLE + "(id INTEGER PRIMARY KEY,name TEXT, ownerId INTEGER)";
    public static final String CREATE_USER_CIRCLE_TABLE = "CREATE TABLE " + USER_CIRCLE_TABLE + "(id INTEGER PRIMARY KEY,circleId Integer, userId INTEGER)";
    public static final String CREATE_USER_EVENT_TABLE = "CREATE TABLE " + USER_EVENT_TABLE + "(id INTEGER PRIMARY KEY,eventId Integer, userId INTEGER)";
    public static final String CREATE_EVENT_COMMENT_TABLE = "CREATE TABLE " + EVENT_COMMENT_TABLE + "(id INTEGER PRIMARY KEY,commentId Integer, eventId INTEGER)";
    public static final String CREATE_FRIEND_TABLE = "CREATE TABLE " + FRIEND_TABLE + "(id INTEGER ,friendId Integer, primary key(id, friendId))";

    //all sql queries
    public static final String INSERT_USER = "insert into " + USER_TABLE + "(name, password) values (?, ?)" ;
    public static final String INSERT_CIRCLE = "insert into " + CIRCLE_TABLE + "(name, ownerId) values (?, ?)" ;

    public static final String DELETE_USER_IN_CIRCLE = "delete from " + CIRCLE_TABLE + "(name, ownerId) values (?, ?)" ;

    public static List<String> allTables(){
        List<String> allTables = new ArrayList<>();
        allTables.add(CIRCLE_TABLE);
        allTables.add(EVENT_TABLE);
        allTables.add(COMMENTS_TABLE);
        allTables.add(USER_TABLE);
        return allTables;
    }
 }
