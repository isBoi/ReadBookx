package com.example.readbookx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

//
    /* context   上下文
    *   name 名称
    *   factory 游标工厂
    *    version 版本号
    *
    * */
    public Database(@Nullable Context context) {
        super(context, "readbook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table bookinformation\n" +
                "( id int,image_url varchar(100),bookname varchar(50),author varchar(50),norvel_url varchar(100),state varchar(50) ,current_chapter varchar(50),decortion varchar(200) ," +
                "constraint bookinformation_pk primary key (id))";
        String sql2="create table chapter_url ( id int ,chapter_id int,chapter_name varchar(50),url varchar(100));";
        String sql3="create table chapter_content( id int,chapter_id int,sentence varchar(500),sentence_id int)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
