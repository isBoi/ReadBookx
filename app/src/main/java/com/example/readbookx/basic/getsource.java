package com.example.readbookx.basic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface getsource {
     public List<Book> getBook(String bookname)throws IOException;
     public Map<String,String> getMap(String novelurl)throws IOException;
     public Chapter getChapter(String bookurl)throws IOException;
}
