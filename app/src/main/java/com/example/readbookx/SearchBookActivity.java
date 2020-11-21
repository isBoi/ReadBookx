package com.example.readbookx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class SearchBookActivity extends AppCompatActivity {
    private TextView sb_icon1,sb_icon2;
    private LinearLayout search,bookshelf;
    public RecyclerView content;
    public SearchBookAdapter searchBookAdapter;
    static public SharedPreferences mysharedpreferences;
    static public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        sb_icon1=findViewById(R.id.sb_icon1);
        sb_icon2=findViewById(R.id.sb_icon2);
        search=findViewById(R.id.sb_bottom_search);
        bookshelf=findViewById(R.id.sb_bookshelf);
        mysharedpreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=mysharedpreferences.edit();
        final Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont2.ttf");
        sb_icon1.setTypeface(iconfont);
        sb_icon2.setTypeface(iconfont);
        content=findViewById(R.id.sb_content);
        content.setLayoutManager(new LinearLayoutManager(SearchBookActivity.this));
        searchBookAdapter=new SearchBookAdapter(SearchBookActivity.this);
        content.setAdapter(searchBookAdapter);
        bookshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchBookActivity.this,BookshelfActivity.class);
                startActivity(intent);
            }
        });
    }

}
