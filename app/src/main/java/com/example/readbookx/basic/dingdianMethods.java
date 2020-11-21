package com.example.readbookx.basic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class dingdianMethods implements getsource {
    private static final String server = "https://www.dingdiann.com";//服务器主域名;
    private static final String search = "https://www.dingdiann.com/searchbook.php?keyword=";//网页内置搜索引擎域名
    @Override
    public  List<Book> getBook(String bookname)throws IOException{
        ArrayList<Book> bookList = new ArrayList<Book>();
        String searchKey = search + bookname;
        Document doc = Jsoup.connect(searchKey).get();
        ArrayList<Element> bookNames = new ArrayList<Element>(doc.getElementsByClass("s2"));
        ArrayList<Element> authorNames = new ArrayList<Element>(doc.getElementsByClass("s4"));
        ArrayList<Element> bookStates = new ArrayList<Element>(doc.getElementsByClass("s7"));
        String bookName;
        String author;
        String imgurl;
        String state;
        String decoration;
        String novelurl;
        for (int i = 1 ;i<bookNames.size();i++){
            bookName = bookNames.get(i).text().toString();
            novelurl = server+bookNames.get(i).select("a[href]").attr("href").toString();
            state = bookStates.get(i).text().toString();
            author = authorNames.get(i).text().toString();
            Document bookdoc = Jsoup.connect(novelurl).get();
            imgurl = "https:" + bookdoc.getElementById("fmimg").select("img[src]").attr("src").toString();
            String bookDecoration = bookdoc.getElementById("intro").text().toString().replaceAll(" 　　","");
            bookList.add(new Book(bookName,author,imgurl,state,bookDecoration,novelurl));

        }
        return bookList;
    }

    @Override
    public Map<String, String> getMap(String novelurl) throws IOException {
        Document doc = Jsoup.connect(novelurl).get();
        Map<String,String> index = new LinkedHashMap<String, String>();
        Elements elements = doc.getElementsByTag("dd");
        int count=0;
        for (Element e :elements){
            if (count>11){
                String contentUrl = server + e.select("a[href]").attr("href").toString();
                String sectionName = e.text().toString();
                index.put(sectionName,contentUrl);
            }else count++;
        }
        return index;
    }

    @Override
    public Chapter getChapter(String novelurl) throws IOException {
        Document doc = Jsoup.connect(novelurl).get();
        Elements elements = doc.getElementsByTag("dd");
        String contentView = doc.getElementById("content").text().toString();
        String sectionName = doc.getElementsByTag("h1").text().toString();
        ArrayList<String> result = new ArrayList<String>();
        Collections.addAll(result, contentView.replaceAll("　　", "").split(" "));
        Chapter chapter = new Chapter(sectionName);
        chapter.setContent(result);
        return chapter;
    }

}
