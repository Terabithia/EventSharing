package com.eventsharing.zhewenboming.eventsharing;
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
    private static DatabaseHelper instance;
    private static final String DATABASE_NAME = "EvenSharing.db";


    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;


    private DatabaseHelper(Context context) {
        this.context = context;
        EvenSharingOpenHelper openHelper = new EvenSharingOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();

    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null) instance = new DatabaseHelper(context);
        return instance;
    }


    /************************Method for user**********************/
    public long insertUser(String name, String password) {
        this.insertStmt = this.db.compileStatement(SQLStatements.INSERT_USER);
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindString(2, password);
        return this.insertStmt.executeInsert();
    }
    public void deleteAllUser() {
        this.db.delete(SQLStatements.USER_TABLE, null, null);
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
