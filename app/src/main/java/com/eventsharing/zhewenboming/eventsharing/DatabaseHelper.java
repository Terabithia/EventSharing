package com.eventsharing.zhewenboming.eventsharing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static DatabaseHelper instance;
    private static final String DATABASE_NAME = "EvenSharing.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "Users";
    private static final String CIRCLE_TABLE = "Circles";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT_USER = "insert into " + USER_TABLE + "(name, password) values (?, ?)" ;

    private DatabaseHelper(Context context) {
        this.context = context;
        EvenSharingOpenHelper openHelper = new EvenSharingOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT_USER);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null) instance = new DatabaseHelper(context);
        return instance;
    }

    public long insert(String name, String password) {
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindString(2, password);
        return this.insertStmt.executeInsert();
    }
    public void deleteAll() {
        this.db.delete(USER_TABLE, null, null);
    }

    public List<String> selectAllUsers(String username, String password) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(USER_TABLE, new String[] { "name", "password" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
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

    private static class EvenSharingOpenHelper extends SQLiteOpenHelper {
        EvenSharingOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + USER_TABLE + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT)");

            //add the circle table
            db.execSQL("CREATE TABLE " + CIRCLE_TABLE + "(id INTEGER PRIMARY KEY, name TEXT, description TEXT");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w("Database", "Upgrading database; this will drop and recreate the tables.");
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            onCreate(db);
        }
    }
}
