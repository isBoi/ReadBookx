package com.example.readbookx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookx.basic.Chapter;
import com.example.readbookx.basic.dingdianMethods;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ContentActivity extends AppCompatActivity {
    public LinearLayout top;
    public LinearLayout title,mulu,shezhi,light;
    public RecyclerView content;
    public boolean isvisible=false;
    public LinearLayout bottom;
    public StatusBar statusBar;
    public TextView tv_last,tv_next,bookname,chaptername;
    public LinearLayout shezhi1,shezhi2;
    public RelativeLayout root;
    public contentAdapter contentAdapter;
    public TextView iconfont1,iconfont2,iconfont3,iconfont4,iconfont2_text,iconfont3_text,iconfont4_text;
    public int statusColor=R.color.colorbrown;;
    public int state=0;//state=0为白天模式
    public String lasturl,nexturl;
    public String url;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Chapter chapter;
    public String bookName;
    public String[] urls;
    public String[] chapters;
    public int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        bottom=findViewById(R.id.content_bottom);
        title=findViewById(R.id.rv_title);
        top=findViewById(R.id.cotent_top);
        bottom.setVisibility(View.INVISIBLE);
        top.setVisibility(View.INVISIBLE);
        tv_last=findViewById(R.id.tv_last);
        tv_next=findViewById(R.id.tv_next);
        mulu=findViewById(R.id.ll_mulu);
        shezhi=findViewById(R.id.ll_shezhi);
        light=findViewById(R.id.ll_light);
        shezhi1=findViewById(R.id.content_shezhi1);
        shezhi2=findViewById(R.id.content_shezhi2);
        root=findViewById(R.id.rl_root);
        bookname=findViewById(R.id.bookname);
        chaptername=findViewById(R.id.chaptername);
        iconfont2_text=findViewById(R.id.tv_icon2_text);
        iconfont3_text=findViewById(R.id.tv_icon3_text);
        iconfont4_text=findViewById(R.id.tv_icon4_text);
        url=sharedPreferences.getString("chapterurl","");
        Collection<String> values = CatalogueActivity.map.values();
        Set<String> strings2 = CatalogueActivity.map.keySet();
        chapters= strings2.toArray(new String[strings2.size()]);
        urls=values.toArray(new String[values.size()]);
        for(int i=0;i<urls.length;i++){
            if(urls[i].equals(url)){
                position=i;
                break;
            }
        }
        bookName=sharedPreferences.getString("bookname","");
        bookname.setText(bookName);
        Mycallable mycallable=new Mycallable(url);
        FutureTask<Chapter> futureTask = new FutureTask(mycallable);
        new Thread(futureTask).start();
        try {
            chapter=futureTask.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        chaptername.setText(chapter.getChaptername());
        content=findViewById(R.id.rv_content);
        content.setLayoutManager(new LinearLayoutManager(ContentActivity.this));
        contentAdapter=new contentAdapter(ContentActivity.this,chapter);
        content.setAdapter(contentAdapter);
        //设置图标
        final Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        iconfont1 = findViewById(R.id.tv_icon1);
        iconfont1.setTypeface(iconfont);
        iconfont2 = findViewById(R.id.tv_icon2);
        iconfont2.setTypeface(iconfont);
        iconfont3 = findViewById(R.id.tv_icon3);
        iconfont3.setTypeface(iconfont);
        iconfont4 = findViewById(R.id.tv_icon4);
        iconfont4.setTypeface(iconfont);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ContentActivity.this,"我被点击了",Toast.LENGTH_SHORT).show();
                if(isvisible){
                    bottom.setVisibility(View.INVISIBLE);
                    top.setVisibility(View.INVISIBLE);
                    statusBar.changeColor(R.color.transparent);
                    statusBar.setStatusBarColor();
                    isvisible=!isvisible;
                }else{
                    bottom.setVisibility(View.VISIBLE);
                    top.setVisibility(View.VISIBLE);
                    statusBar.changeColor(statusColor);
                    statusBar.setStatusBarColor();
                    isvisible=!isvisible;
                }
            }});
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ContentActivity.this,"bottom被点击了",Toast.LENGTH_SHORT).show();
            }});
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ContentActivity.this,"top被点击了",Toast.LENGTH_SHORT).show();
            }});
        mulu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContentActivity.this,CatalogueActivity.class);
                startActivity(intent);
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* statusBar.changeColor();*/;
               if(state==0){
                   title.setBackgroundColor(getResources().getColor(R.color.backgroundblack));
            /*    TextView tv1=findViewById(R.id.tv_recycleview_title);
                tv1.setBackgroundColor(getResources().getColor(R.color.backgroundblack));
                tv1.setTextColor(getResources().getColor(R.color.characterwhite));*/
                   bookname.setTextColor(getResources().getColor(R.color.characterwhite));
                   chaptername.setTextColor(getResources().getColor(R.color.characterwhite));
                   root.setBackgroundColor(getResources().getColor(R.color.backgroundblack));
                   bottom.setBackgroundColor(getResources().getColor(R.color.backgrounddeepblack));
                   shezhi1.setBackgroundColor(getResources().getColor(R.color.backgrounddeepblack));
                   shezhi2.setBackgroundColor(getResources().getColor(R.color.backgrounddeepblack));
                   top.setBackgroundColor(getResources().getColor(R.color.backgrounddeepblack));
                   contentAdapter.bakcgroundcolor="#181818";
                   contentAdapter.color="#b2b2b2";
                   iconfont1.setTextColor(getResources().getColor(R.color.gray));
                   iconfont2.setTextColor(getResources().getColor(R.color.gray));
                   iconfont3.setTextColor(getResources().getColor(R.color.gray));
                   iconfont4.setText(R.string.icon5);
                   iconfont4.setTextColor(getResources().getColor(R.color.gray));
                   iconfont2_text.setTextColor(getResources().getColor(R.color.gray));
                   iconfont3_text.setTextColor(getResources().getColor(R.color.gray));
                   iconfont4_text.setText("日间");
                   iconfont4_text.setTextColor(getResources().getColor(R.color.gray));
                   contentAdapter.notifyDataSetChanged();
                   bottom.setDividerDrawable(getResources().getDrawable(R.drawable.whitedivider));
                   tv_last.setTextColor(getResources().getColor(R.color.gray));
                   tv_next.setTextColor(getResources().getColor(R.color.gray));
                   statusColor=R.color.backgrounddeepblack;
                   statusBar.changeColor(statusColor);
                   statusBar.setStatusBarColor();
                   state=1;
               }else{
                   title.setBackgroundColor(getResources().getColor(R.color.colorpifu));
            /*    TextView tv1=findViewById(R.id.tv_recycleview_title);
                tv1.setBackgroundColor(getResources().getColor(R.color.backgroundblack));
                tv1.setTextColor(getResources().getColor(R.color.characterwhite));*/
                   bookname.setTextColor(getResources().getColor(R.color.textblack));
                   chaptername.setTextColor(getResources().getColor(R.color.textblack));
                   root.setBackgroundColor(getResources().getColor(R.color.colorpifu));
                   bottom.setBackgroundColor(getResources().getColor(R.color.colorbrown));
                   shezhi1.setBackgroundColor(getResources().getColor(R.color.colorbrown));
                   shezhi2.setBackgroundColor(getResources().getColor(R.color.colorbrown));
                   top.setBackgroundColor(getResources().getColor(R.color.colorbrown));
                   contentAdapter.bakcgroundcolor="#d7c6a2";
                   contentAdapter.color="#302303";
                   iconfont1.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont2.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont3.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont4.setText(R.string.icon4);
                   iconfont4.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont2_text.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont3_text.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   iconfont4_text.setText("夜间");
                   iconfont4_text.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   contentAdapter.notifyDataSetChanged();
                   bottom.setDividerDrawable(getResources().getDrawable(R.drawable.divider));
                   tv_last.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   tv_next.setTextColor(getResources().getColor(R.color.colordeepBrown));
                   statusColor=R.color.colorbrown;
                   statusBar.changeColor(statusColor);
                   statusBar.setStatusBarColor();
                   state=0;
               }
            }
        });
        tv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    Toast.makeText(ContentActivity.this,"这是第一页哦",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    position=position-1;
                    //String chaptername=chapters[position];
                    String url=urls[position];
                    chaptername.setText(chapters[position]);
                    Mycallable mycallable=new Mycallable(url);
                    FutureTask<Chapter> futureTask = new FutureTask(mycallable);
                    new Thread(futureTask).start();
                    try {
                        chapter=futureTask.get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    contentAdapter.setChapter(chapter);
                    contentAdapter.notifyDataSetChanged();
                }
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==urls.length-1){
                    Toast.makeText(ContentActivity.this,"这是最后一页了哦",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    position=position+1;
                    //String chaptername=chapters[position];
                    String url=urls[position];
                    chaptername.setText(chapters[position]);
                    Mycallable mycallable=new Mycallable(url);
                    FutureTask<Chapter> futureTask = new FutureTask(mycallable);
                    new Thread(futureTask).start();
                    try {
                        chapter=futureTask.get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    contentAdapter.setChapter(chapter);
                    contentAdapter.notifyDataSetChanged();
                }
            }
        });
        iconfont1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mulu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContentActivity.this,CatalogueActivity.class);
                startActivity(intent);
            }
        });
        statusBar=new StatusBar(ContentActivity.this,R.color.transparent);
        statusBar.setStatusBarColor();

    }

    class Mycallable implements Callable<Chapter> {
        String chapterurl;
        Mycallable( String chapterurl){
            this.chapterurl=chapterurl;
        }
        @Override
        public Chapter call() throws Exception {
            Chapter chapter=null;
            dingdianMethods dingdianMethods=new dingdianMethods();
            try {
                chapter=dingdianMethods.getChapter(chapterurl);
            }catch (Exception e){
                e.printStackTrace();
            }
            return chapter;
        }
    }
}
