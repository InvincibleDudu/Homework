package com.invdu.homework;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.invdu.homework.bean.Discussion;
import com.invdu.homework.bean.Homework;
import com.invdu.homework.bean.SubQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by InvincibleDudu on 04/16/2020
 */

public class DBHelper extends SQLiteOpenHelper {        //数据库操纵类
    private static final String TAG = "UserDBHelper";
    private static final String DB_NAME = "homework.db";
    private static final int DB_VERSION = 3;
    //    private static DBHelper mHelper = null;hwdadw
//    private SQLiteDatabase mDB = null;
//    private static final String TABLE_NAME = "test";

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //    public DBHelper(Context context, String name){
//        this(context, name);
//    }
    @Override
    public void onCreate(SQLiteDatabase db) {       //创建Homework表
//        String create = "create table if not exists homework(\n" +
//                "\tID varchar(50) primary key,\n" +
//                "\tvalue varchar(50)\n" +
//                ");";
//        String drop = "drop table homework";
//        db.execSQL(drop);
        String create = "create table if not exists homework (id integer primary key autoincrement, chapter text," +
                " title text, description text, answer text, score interger, isSub interger, included_questions interger)";
        db.execSQL(create);
    }

    public boolean addHomework(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("chapter", homework.getChapter());
        cv.put("title", homework.getTitle());
        cv.put("answer", homework.getAnswer());
        cv.put("score", homework.getScore());
        cv.put("isSub", homework.isSub());
        cv.put("description", homework.getDescription());
        cv.put("included_questions", homework.getIncludedQuestions());

        long insert = db.insert("homework", null, cv);
        db.close();
        return insert != -1;
    }

    public void addDiscussion(Discussion d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", d.getId());
        cv.put("username", d.getUsername());
        cv.put("time", d.getTime());
        cv.put("message", d.getMessage());
        long insert = db.insert("discussion", null, cv);
        db.close();
    }

    public List<Homework> getAllHomework() {        //以List形式获取数据库中所有作业
        List<Homework> returnList = new ArrayList<>();
        String sql = "select * from homework";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String chapter = cursor.getString(1);
                String title = cursor.getString(2);
                String description = cursor.getString(3);
                String answer = cursor.getString(4);
                int score = cursor.getInt(5);
                boolean isSub = cursor.getInt(6) == 1;
                int includedQuestions = cursor.getInt(7);
                Homework homework = new Homework(id, chapter, title, description, answer, score, isSub, includedQuestions);
                returnList.add(homework);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<SubQuestion> getAllSubQuestions() {
        List<SubQuestion> returnList = new ArrayList<>();
        String sql = "select * from sub_question";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String comment = cursor.getString(1);
                String content = cursor.getString(2);
//                Homework homework = new Homework(chapter, title, description, answer, score, isSub, includedQuestions);
                SubQuestion sub = new SubQuestion(id, comment, content);
                returnList.add(sub);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Discussion> getAllDiscussions() {
        List<Discussion> returnList = new ArrayList<>();
        String sql = "select * from discussion";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String message = cursor.getString(1);
                String time = cursor.getString(2);
                String username = cursor.getString(3);
                Discussion d = new Discussion(id, message, time, username);
                returnList.add(d);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public void updateHomework(int id, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        String alter = "update homework set answer ='" + answer + "' where ID = '" + id + "'";
        db.execSQL(alter);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS homework");
        onCreate(db);
    }
}
