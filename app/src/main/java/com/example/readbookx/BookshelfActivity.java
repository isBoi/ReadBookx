package com.example.readbookx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookx.basic.Book;

import java.util.List;

public class BookshelfActivity extends AppCompatActivity {
    private TextView bs_icon1,bs_icon2;
    public RecyclerView content;
    public BookAdapter bookAdapter;
    private LinearLayout search,bookshelf;
    public static List<Book> list;
    static public SharedPreferences mysharedpreferences;
    static public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dao dao=new Dao(BookshelfActivity.this);
        list=dao.getBooks();
        setContentView(R.layout.activity_bookshelf);
        mysharedpreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=mysharedpreferences.edit();
        bs_icon1=findViewById(R.id.bs_icon1);
        bs_icon2=findViewById(R.id.bs_icon2);
        bookshelf=findViewById(R.id.bs_bookshelf);
        search=findViewById(R.id.bs_search);
        final Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont2.ttf");
        bs_icon1.setTypeface(iconfont);
        bs_icon2.setTypeface(iconfont);
        content=findViewById(R.id.bs_content);
        content.setLayoutManager(new LinearLayoutManager(BookshelfActivity.this));
        bookAdapter=new BookAdapter(BookshelfActivity.this,list);
        content.setAdapter(bookAdapter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookshelfActivity.this,SearchBookActivity.class);
                startActivity(intent);
            }
        });
    }
}
