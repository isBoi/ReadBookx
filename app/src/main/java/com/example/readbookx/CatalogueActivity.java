package com.example.readbookx;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookx.basic.dingdianMethods;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CatalogueActivity extends AppCompatActivity {
    private StatusBar statusBar;
    private TextView icon_back;
    private TextView tv_title;
    public RecyclerView content;
    public CatalogueAdapter catalogueAdapter;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Map<String,String> map;
    private TextView bookname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        setContentView(R.layout.activity_catalogue);
        tv_title=findViewById(R.id.cat_title);
        icon_back=findViewById(R.id.tv_iconback);
        final Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        icon_back.setTypeface(iconfont);
        bookname=findViewById(R.id.cat_bookname);
        String book=sharedPreferences.getString("bookname","");
        bookname.setText(book);
        String father=sharedPreferences.getString("father","");
        if(father.equals("shelf")){
            int id=sharedPreferences.getInt("id",0);
            Dao dao=new Dao(this);
            map=null;
            map=dao.getMap(id);
            content=findViewById(R.id.cat_content);
            content.setLayoutManager(new LinearLayoutManager(CatalogueActivity.this));
            catalogueAdapter=new CatalogueAdapter(CatalogueActivity.this,map);
            content.setAdapter(catalogueAdapter);
            /*Toast.makeText(CatalogueActivity.this,String.valueOf(id),Toast.LENGTH_SHORT).show();*/
        }else{
            String url=sharedPreferences.getString("url","");
            Mycallable mycallable=new Mycallable(url);
            FutureTask<Map<String,String>> futureTask = new FutureTask(mycallable);
            new Thread(futureTask).start();
            map=null;
            try {
                map=futureTask.get();
            }catch (Exception e){
                e.printStackTrace();
            }
            content=findViewById(R.id.cat_content);
            content.setLayoutManager(new LinearLayoutManager(CatalogueActivity.this));
            catalogueAdapter=new CatalogueAdapter(CatalogueActivity.this,map);
            content.setAdapter(catalogueAdapter);
            /*Toast.makeText(CatalogueActivity.this,url,Toast.LENGTH_SHORT).show();*/
        }
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    class Mycallable implements Callable<Map<String,String>> {
        String norvelurl;
        Mycallable(String norvelurl){
            this.norvelurl=norvelurl;
        }
        @Override
        public Map<String, String> call() throws Exception {
            Map<String,String> map=null;
            dingdianMethods dingdianMethods=new dingdianMethods();
            try {
                map=dingdianMethods.getMap(norvelurl);
            }catch (Exception e){
                e.printStackTrace();
            }
            return map;
        }
    }
}
