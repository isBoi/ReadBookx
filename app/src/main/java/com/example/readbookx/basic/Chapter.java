package com.example.readbookx.basic;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private List<String> content;
    private String chaptername;
    Chapter(String chaptername){
        this.chaptername=chaptername;
    }
    public List<String> getContent() {
        return content;
    }
    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
}
