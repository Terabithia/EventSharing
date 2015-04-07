package com.eventsharing.zhewenboming.eventsharing;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.Event;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
//import com.eventsharing.zhewenboming.eventsharing.SQLStatements;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DATABASE_NAME = "EvenSharing.db";

    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement stmt;


    public DatabaseHelper(Context context) {
        this.context = context;
        EvenSharingOpenHelper openHelper = new EvenSharingOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
    }

    /************************Method for user**********************/
    public long insertUser(String name, String password) {
        this.stmt = this.db.compileStatement(SQLStatements.INSERT_USER);
        this.stmt.bindString(1, name);
        this.stmt.bindString(2, password);
        return this.stmt.executeInsert();
    }
    public void deleteUser(int id){
        this.db.delete(SQLStatements.USER_CIRCLE_TABLE,"userId = " + id, null);
        this.db.delete(SQLStatements.USER_EVENT_TABLE,"userId = " + id, null);
        this.db.delete(SQLStatements.USER_TABLE,"id = " + id,null);
    }
    public List<String> selectAllUsers(String username, String password) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "name" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "name", "password", "id" }, null, null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0),cursor.getString(1));
                user.setId(cursor.getInt(2));
                user.setCircle(getCirclesByUserId(cursor.getInt(2)));
                user.setFriends(getFriendsByUserId(cursor.getInt(2)));
                list.add(user);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public User getUserById(int id){
        User user = new User("","");
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "name","password" }, "id = "+ id , null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {

                user = new User(cursor.getString(0),cursor.getString(1));
                user.setId(id);
                user.setCircle(getCirclesByUserId(id));
                user.setFriends(getFriendsByUserId(id));

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return user;
    }
    public List<Integer> getUsersByCircleId(int id){
        List<Integer> users = new ArrayList<>();
        Cursor cursor = this.db.query(SQLStatements.USER_CIRCLE_TABLE, new String[] { "userId"},"circleId = "+id, null, null, null, "userId desc");
        if (cursor.moveToFirst()) {
            do {
                users.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return users;
    }
    public List<Integer> getFriendsByUserId(int id){
        List<Integer> friends = new ArrayList();
        Cursor cursor = this.db.query(SQLStatements.FRIEND_TABLE, new String[] { "friendId"},"userId = " + id, null, null, null, "friendId desc");
        if (cursor.moveToFirst()) {
            do {
                friends.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return friends;
    }
    public void addFriendById(int userId, int friendId){
        ContentValues insertValues = new ContentValues();
        insertValues.put("userId", userId);
        insertValues.put("friendId",friendId);
        this.db.insert(SQLStatements.FRIEND_TABLE,null,insertValues);

    }
    public User getUserByName(String name){
        User user = new User("","");
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "id", "name","password" }, "name = "+ "'" + name + "'", null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {

                user = new User(cursor.getString(1),cursor.getString(2));
                user.setId(cursor.getInt(0));
                user.setCircle(getCirclesByUserId(cursor.getInt(0)));
                user.setFriends(getFriendsByUserId(cursor.getInt(0)));
                user.setEvents(getEventsByUserId(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return user;
    }
    /************************Method for circle**********************/
    public void insertCircle(String name, int ownerId) {
//        this.stmt = this.db.compileStatement(SQLStatements.INSERT_CIRCLE);
//        this.stmt.bindString(1, name);
//        this.stmt.bindString(2, ownerId);
//        return this.stmt.executeInsert();
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", name);
        insertValues.put("ownerId", ownerId);
        this.db.insert(SQLStatements.CIRCLE_TABLE, null, insertValues);
    }
    public List<Integer> getCirclesByUserId(int id){
        List<Integer> circles = new ArrayList();
        Cursor cursor = this.db.query(SQLStatements.CIRCLE_TABLE, new String[] { "id" }, "ownerId = "+ id , null, null, null, "id desc");
        if (cursor.moveToFirst()) {
            do {
                circles.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return circles;
    }
    public void deleteCircle(int id){
        this.db.delete(SQLStatements.USER_CIRCLE_TABLE,"circleId = " + id, null);
        this.db.delete(SQLStatements.CIRCLE_TABLE,"ownerId = " + id,null);
    }
    public Circle getCircleById(int id){
        Cursor cursor = this.db.query(SQLStatements.CIRCLE_TABLE, new String[] { "name","ownerId" }, "id = "+ id , null, null, null, "name desc");
        Circle c = null;
        if (cursor.moveToFirst()){
            c = new Circle(cursor.getString(0));
            c.setId(id);
            c.setOwnerId(cursor.getInt(1));
            c.setUsers(getUsersByCircleId(id));
        }
        return c;
    }

    public void addUserToCircle(int circleId, int userId){
        ContentValues insertValues = new ContentValues();
        insertValues.put("circleId", circleId);
        insertValues.put("userId", userId);
        this.db.insert(SQLStatements.USER_CIRCLE_TABLE, null, insertValues);
    }

    /*****************Methods for event**************************/
    public void insertEvent(String title, String des, int ownerId, String location) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("title", title);
        insertValues.put("description", des);
        insertValues.put("ownerId", ownerId);
        insertValues.put("location", location);
        this.db.insert(SQLStatements.EVENT_TABLE, null, insertValues);
    }
    public List<Integer> getEventsByUserId(int id){
        List<Integer> events = new ArrayList();
        Cursor cursor = this.db.query(SQLStatements.EVENT_TABLE, new String[] { "id" }, "ownerId = "+ id , null, null, null, "id desc");
        if (cursor.moveToFirst()) {
            do {
                events.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return events;
    }
    public void deleteEvent(int id){
        this.db.delete(SQLStatements.USER_EVENT_TABLE,"eventId = " + id, null);
        this.db.delete(SQLStatements.EVENT_TABLE,"ownerId = " + id,null);
    }
    public Event getEventById(int id){
        Cursor cursor = this.db.query(SQLStatements.EVENT_TABLE, new String[] { "title","description","ownerId","location" }, "id = "+ id , null, null, null, "title desc");
        Event e = null;
        if(cursor.moveToFirst()){
            e = new Event(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
        }
        e.setId(id);
        return e;
    }

    /*********Methods for Comments***********************/


    /************************Method for other**********************/
    private static class EvenSharingOpenHelper extends SQLiteOpenHelper {
        EvenSharingOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, SQLStatements.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create all tables
            db.execSQL(SQLStatements.CREATE_USER_TABLE);
            db.execSQL(SQLStatements.CREATE_COMMENTS_TABLE);
            db.execSQL(SQLStatements.CREATE_CIRCLE_TABLE);
            db.execSQL(SQLStatements.CREATE_EVENT_TABLE);
            db.execSQL(SQLStatements.CREATE_EVENT_COMMENT_TABLE);
            db.execSQL(SQLStatements.CREATE_USER_CIRCLE_TABLE);
            db.execSQL(SQLStatements.CREATE_USER_EVENT_TABLE);
            db.execSQL(SQLStatements.CREATE_FRIEND_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Database", "Upgrading database; this will drop and recreate the tables.");
            List<String> allTables = SQLStatements.allTables();
            for(String s: allTables) db.execSQL("DROP TABLE IF EXISTS " + s);
            onCreate(db);
        }
    }
}
