package com.example.readbookx.basic;

import java.util.ArrayList;
import java.util.Map;

public class Book {
    private int bookid;//id名
    private String bookName;//书名
    private String author;//作者
    private String imageurl;//图片url
    private String novelurl;//小说url
    private String state;//连载状态
    private Map<String,String> map;//章节 与url对应信息
    private String decortion;//书的简介
    private ArrayList<Chapter> chapterArrayList;//缓存章节信息
    private String currentchapter;//目前已经读取的章节

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getNovelurl() {
        return novelurl;
    }
    public void setNovelurl(String novelurl) {
        this.novelurl = novelurl;
    }

    public Book(){

    }

    Book(String bookName, String author, String imageurl, String state, String decortion, String novelurl) {
        this.bookName = bookName;
        this.author = author;
        this.imageurl = imageurl;
        this.state = state;
        this.decortion = decortion;
        this.novelurl=novelurl;
    }

    public ArrayList<Chapter> getChapterArrayList() {
        return chapterArrayList;
    }

    public void setChapterArrayList(ArrayList<Chapter> chapterArrayList) {
        this.chapterArrayList = chapterArrayList;
    }

    public String getCurrentchapter() {
        return currentchapter;
    }

    public void setCurrentchapter(String currentchapter) {
        this.currentchapter = currentchapter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap() {

    }

    public String getDecortion() {
        return decortion;
    }

    public void setDecortion(String decortion) {
        this.decortion = decortion;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
