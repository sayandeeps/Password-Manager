package com.simistudio.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class password_database extends SQLiteOpenHelper {
    private static final String dbname = "pswd.db";
    public password_database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="create table accounts (id integer primary key autoincrement, account text, username text, password text)" ;//table creating querry
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void   onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        onCreate(sqLiteDatabase);

    }
    public boolean insert_data(String account, String username, String password){
    SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("account", account);
        c.put("username", username);
        c.put("password", password);
        long r=db.insert("accounts",null,c);
        if (r==-1) return false;
        else
            return true;
    }
    public Cursor get_info(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from accounts ", null);
        return cursor;
    }
}
