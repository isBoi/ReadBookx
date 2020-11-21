package com.example.readbookx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.readbookx.basic.Book;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dao {
    private int id=-1;
    private Database database;
    public Dao(Context context){
        database=new Database(context);
    }
    public int getid(){
        SQLiteDatabase db = database.getReadableDatabase();
        String sql="select max(id) from bookinformation";
        Cursor cursor = db.rawQuery(sql, null);
        int id=0;
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex("max(id)");
            id=cursor.getInt(index);
        }
        db.close();
        return id+1;
    }
    public boolean insert(Book book){
        if(id==-1){
            id=getid();
        }
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values=new ContentValues();//添加数据
        values.put("id",id);
        values.put("image_url",book.getImageurl());
        values.put("author",book.getAuthor());
        values.put("norvel_url",book.getNovelurl());
        values.put("bookname",book.getBookName());
        values.put("state",book.getState());
        values.put("current_chapter",book.getCurrentchapter());
        values.put("decortion",book.getDecortion());
        try {
            db.insert("bookinformation",null,values);
            ContentValues values2=new ContentValues();//添加数据
            if(book.getMap()!=null){
                int chapter_id=1;
                for (Map.Entry<String, String> entry : book.getMap().entrySet()) {
                    values2.put("id",id);
                    values2.put("chapter_id",chapter_id++);
                    values2.put("chapter_name",entry.getKey());
                    values2.put("url",entry.getValue());
                    db.insert("chapter_url",null,values2);
                }
            }
            id++;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();

        }
    }
    public boolean saveMap(int id,Map<String,String> map){
        SQLiteDatabase db = database.getWritableDatabase();
        try {
            ContentValues values2 = new ContentValues();//添加数据
            if (map != null) {
                int chapter_id = 1;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    values2.put("id", id);
                    values2.put("chapter_id", chapter_id++);
                    values2.put("chapter_name", entry.getKey());
                    values2.put("url", entry.getValue());
                    db.insert("chapter_url", null, values2);
                }
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            db.close();
        }
    }
    public Book getBook(int id){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor query = db.query(
                false, "chapter_url", new String[]{"chapter_name", "url"},
                "id = ?", new String[]{String.valueOf(id)}, null, null,
                "chapter_id", null, null);
        String url=null,chaptername=null;
        Map<String,String> map=new LinkedHashMap<String,String>();
        while(query.moveToNext()){
            int index=query.getColumnIndex("chapter_name");
            int index_url=query.getColumnIndex("url");
            chaptername=query.getString(index);
            url=query.getString(index_url);
            if(chaptername!=null&&url!=null){
                map.put(chaptername,url);
            }
            chaptername=null;
            url=null;
        }
        Book book=new Book();
        Cursor query2 = db.query(
                false, "bookinformation", null,
                "id = ?", new String[]{String.valueOf(id)}, null, null,
                null, null, null);
        while(query2.moveToNext()){
            int index_id=query2.getColumnIndex("id");
            int index_image=query2.getColumnIndex("image_url");
            int index_bookname=query2.getColumnIndex("bookname");
            int index_author=query2.getColumnIndex("author");
            int index_norvel=query2.getColumnIndex("norvel_url");
            int index_state=query2.getColumnIndex("state");
            int index_current=query2.getColumnIndex("current_chapter");
            int index_decortion=query2.getColumnIndex("decortion");
            book.setBookid(query2.getInt(index_id));
            book.setImageurl(query2.getString(index_image));
            book.setBookName(query2.getString(index_bookname));
            book.setAuthor(query2.getString(index_author));
            book.setNovelurl(query2.getString(index_norvel));
            book.setState(query2.getString(index_state));
            book.setCurrentchapter(query2.getString(index_current));
            book.setDecortion(query2.getString(index_decortion));
        }
        if(map.size()!=0){
            book.setMap(map);
        }
        db.close();
        return book;
    }
    public Map<String,String> getMap(int id){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor query = db.query(
                false, "chapter_url", new String[]{"chapter_name", "url"},
                "id = ?", new String[]{String.valueOf(id)}, null, null,
                "chapter_id", null, null);
        String url=null,chaptername=null;
        Map<String,String> map=new LinkedHashMap<String,String>();
        while(query.moveToNext()){
            int index=query.getColumnIndex("chapter_name");
            int index_url=query.getColumnIndex("url");
            chaptername=query.getString(index);
            url=query.getString(index_url);
            if(chaptername!=null&&url!=null){
                map.put(chaptername,url);
            }
            chaptername=null;
            url=null;
        }
        db.close();
        return map;
    }
    public Book getnomapBook(int id){
        SQLiteDatabase db = database.getReadableDatabase();
        Book book=new Book();
        Cursor query2 = db.query(
                false, "bookinformation", null,
                "id = ?", new String[]{String.valueOf(id)}, null, null,
                null, null, null);
        while(query2.moveToNext()){
            int index_id=query2.getColumnIndex("id");
            int index_image=query2.getColumnIndex("image_url");
            int index_bookname=query2.getColumnIndex("bookname");
            int index_author=query2.getColumnIndex("author");
            int index_norvel=query2.getColumnIndex("norvel_url");
            int index_state=query2.getColumnIndex("state");
            int index_current=query2.getColumnIndex("current_chapter");
            int index_decortion=query2.getColumnIndex("decortion");
            book.setBookid(query2.getInt(index_id));
            book.setImageurl(query2.getString(index_image));
            book.setBookName(query2.getString(index_bookname));
            book.setAuthor(query2.getString(index_author));
            book.setNovelurl(query2.getString(index_norvel));
            book.setState(query2.getString(index_state));
            book.setCurrentchapter(query2.getString(index_current));
            book.setDecortion(query2.getString(index_decortion));
        }
        db.close();
        return book;
    }
    public List<Book> getBooks(){
        int id=getid();
        List<Book> list=new ArrayList<>();
        for(int i=1;i<id;i++){
            list.add(getBook(i));
        }
        return list;
    }
}
