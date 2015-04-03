package com.eventsharing.zhewenboming.eventsharing;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.SQLStatements;
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
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "name", "password" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
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
        Cursor cursor = this.db.query(SQLStatements.USER_TABLE, new String[] { "name","password" }, "id = "+ id , null, null, null, "name desc");
        User user = new User(cursor.getString(0),cursor.getString(1));
        user.setId(id);
        user.setCircle(getCirclesByUserId(id));
        user.setFriends(getFriendsByUserId(id));
        return user;
    }
    //need implementation
    public List<Integer> getUsersByCircleId(int id){
        List<Integer> users = new ArrayList<>();
        return users;
    }
    //need implementation
    public List<Integer> getFriendsByUserId(int id){
        List<Integer> friends = new ArrayList();
        return friends;
    }

    /************************Method for circle**********************/
    public long insertCircle(String name, String ownerId) {
        this.stmt = this.db.compileStatement(SQLStatements.INSERT_CIRCLE);
        this.stmt.bindString(1, name);
        this.stmt.bindString(2, ownerId);
        return this.stmt.executeInsert();
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
        Circle c = new Circle(cursor.getString(0));
        c.setId(id);
        c.setOwnerId(cursor.getInt(1));
        c.setUsers(getUsersByCircleId(id));
        return c;
    }


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
