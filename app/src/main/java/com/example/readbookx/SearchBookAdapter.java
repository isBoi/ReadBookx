package com.example.readbookx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.readbookx.basic.Book;
import com.example.readbookx.basic.dingdianMethods;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class SearchBookAdapter extends RecyclerView.Adapter{
    SearchBookAdapter(Context context){
        this.context=context;
    }
    private Context context;
    private List<Book> list;/*{{
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
        add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                "连载","很不错呀","..."));
    }};;*/
    private int TYPE_TOP=1000;
    private int TYPE_BODY=1001;
    private int TYPE_BOTTOM=1002;
    private int TYPE_NO=1003;
    private String text="--快点进行查询吧!--";
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_TOP)
            return new SearchTopViewHolder(LayoutInflater.from(context).inflate(R.layout.searchbooktop,parent,false));
        else if(viewType==TYPE_BODY)
            return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.searchbookadapter,parent,false));
        else if(viewType==TYPE_NO)
            return new SearchNoViewHolder(LayoutInflater.from(context).inflate(R.layout.searchnobook,parent,false));
        else
            return new SearchBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.searchbottombook,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(list==null||list.size()==0){
            if(position==0){
                SearchTopViewHolder bookTopViewHolder=(SearchTopViewHolder) holder;
                final Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont2.ttf");
                bookTopViewHolder.iconfont.setTypeface(iconfont);
                bookTopViewHolder.textView.setOnClickListener(new MyClick(bookTopViewHolder.getEditText()));
            } else if(position==1){
                SearchNoViewHolder searchNoViewHolder=(SearchNoViewHolder)holder;
                final Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont3.ttf");
                searchNoViewHolder.iconfont.setTypeface(iconfont);
            }else{
                SearchBottomViewHolder bookTopViewHolder=(SearchBottomViewHolder) holder;
                bookTopViewHolder.textView.setText(text);
            }
        }
        else{
            if(position==0){
                final SearchTopViewHolder bookTopViewHolder=(SearchTopViewHolder) holder;
                final Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont2.ttf");
                bookTopViewHolder.iconfont.setTypeface(iconfont);
                bookTopViewHolder.textView.setOnClickListener(new MyClick(bookTopViewHolder.getEditText()));
            } else if(position==list.size()+1){
                SearchBottomViewHolder bookTopViewHolder=(SearchBottomViewHolder) holder;
                bookTopViewHolder.textView.setText("--查询完毕--");
            }else{
                SearchViewHolder searchViewHolder=(SearchViewHolder) holder;
                final Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont4.ttf");
                searchViewHolder.iconfont.setTypeface(iconfont);
                searchViewHolder.author.setText(list.get(position-1).getAuthor());
                searchViewHolder.state.setText(list.get(position-1).getState());
                searchViewHolder.bookname.setText(list.get(position-1).getBookName());
                searchViewHolder.introduction.setText(list.get(position-1).getDecortion());
                searchViewHolder.iconfont.setOnClickListener(new MyClick2(searchViewHolder.iconfont,list.get(position-1)));
                Glide.with(context).load(list.get(position-1).getImageurl()).into(searchViewHolder.imageView);
                searchViewHolder.relativeLayout.setOnClickListener(new MyClick3(list.get(position-1).getNovelurl(),list.get(position-1).getBookName()));
            }
        }

    }

    @Override
    public int getItemCount() {
        if(list==null||list.size()==0){
            return 3;
        }else
            return list.size()+2;
    }
    @Override
    public int getItemViewType(int position) {
        if(list==null||list.size()==0){
            if(position==0){
                return TYPE_TOP;
            }else if(position==1){
                return TYPE_NO;
            }
            else {
                return TYPE_BOTTOM;
            }
        }else{
            if(position==0){
                return TYPE_TOP;
            } else if(position <=list.size()){
                return TYPE_BODY;
            }else{
                return TYPE_BOTTOM;
            }
        }

    }
    class Mycallable2 implements Callable<Map<String,String>>{
        String norvelurl;
        Mycallable2(String norvelurl){
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
    class Mycallable implements Callable<List<Book>>{
        String text;
        Mycallable(String text){
            this.text=text;
        }
        @Override
        public List<Book> call() throws Exception {
            List<Book> list=null;
            dingdianMethods dingdianMethods=new dingdianMethods();
            try {
                list=dingdianMethods.getBook(text);
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
    }
    class MyClick implements View.OnClickListener{
        private EditText editText;
        public MyClick(EditText editText) {
            this.editText = editText;
        }
        @Override
        public void onClick(View view) {
            /*list.add(new Book("全职法师","乱","https://dingdiann.cdn.bcebos.com/web/bookfiles/BookImages/23863.jpg",
                    "连载","很不错呀","..."));*/
            /*InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput( 0 , InputMethodManager.HIDE_NOT_ALWAYS);*/
            text="抱歉。。。没有找到哦！";
            String text=editText.getText().toString();
            Mycallable callable=new Mycallable(text);
            FutureTask<List<Book>> futureTask = new FutureTask(callable);
            new Thread(futureTask).start();
            try {
                list=futureTask.get();
            }catch (Exception e){
                e.printStackTrace();
            }
            SearchBookActivity searchBookActivity=(SearchBookActivity)context;
            searchBookActivity.searchBookAdapter.notifyDataSetChanged();
//            Toast.makeText(context, editText.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyClick2 implements View.OnClickListener{

        private boolean  issave=false;
        private Book book;
        private TextView textView;
        public MyClick2(TextView textView,Book book) {
            this.textView = textView;
            this.book=book;
        }

        @Override
        public void onClick(View view) {
            if(!issave){
                final Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont4.ttf");
                textView.setText(R.string.icon10);
                Dao dao=new Dao(context);
                int id=dao.getid();
                book.setBookid(id);
                Mycallable2 mycallable2=new Mycallable2(book.getNovelurl());
                FutureTask<Map<String,String>> futureTask = new FutureTask(mycallable2);
                new Thread(futureTask).start();
                Map<String,String> map=null;
                try {
                    map=futureTask.get();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(map!=null){
                    book.setMap(map);
                }
                BookshelfActivity.list.add(book);
                boolean a=dao.insert(book);
                if(a==false){
                    Toast.makeText(context,"加入书架失败!",Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setTextColor(Color.parseColor("#42E61A"));;
                textView.setTypeface(iconfont);
                Toast.makeText(context,"加入书架成功!",Toast.LENGTH_SHORT).show();
                issave=!issave;
            }
        }
    }
    class MyClick3 implements View.OnClickListener{
        private String url;
        private String bookname;
        public MyClick3(String url,String bookname) {
            this.url = url;
            this.bookname=bookname;
        }
        @Override
        public void onClick(View view) {
            SearchBookActivity.editor.putString("father","search");
            SearchBookActivity.editor.putString("url",url);
            SearchBookActivity.editor.putString("bookname",bookname);
            SearchBookActivity.editor.apply();
            Intent intent=new Intent(context,CatalogueActivity.class);
            context.startActivity(intent);
           /* Toast.makeText(context,url,Toast.LENGTH_SHORT).show();*/
        }
    }
    class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView bookname,state,author,introduction,iconfont;
        private RelativeLayout relativeLayout;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.sb_image);
            bookname=itemView.findViewById(R.id.sb_bookname);
            state=itemView.findViewById(R.id.sb_state);
            author=itemView.findViewById(R.id.sb_author);
            introduction=itemView.findViewById(R.id.sb_introduction);
            iconfont=itemView.findViewById(R.id.sb_iconfont5);
            relativeLayout=itemView.findViewById(R.id.sb_relativelayout);
        }
    }
    class SearchTopViewHolder extends RecyclerView.ViewHolder{
        private TextView iconfont;
        private EditText editText;
        private RelativeLayout relativeLayout;
        public EditText getEditText() {
            return editText;
        }

        private TextView textView;
        public SearchTopViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.sb_search);
            editText=itemView.findViewById(R.id.sb_edittext);
            editText.clearFocus();
            iconfont=itemView.findViewById(R.id.sb_icon4);
            relativeLayout=itemView.findViewById(R.id.edit_wrapper);
        }
    }
    class SearchNoViewHolder extends RecyclerView.ViewHolder{
        private TextView iconfont;
        public SearchNoViewHolder(@NonNull View itemView) {
            super(itemView);
            iconfont=itemView.findViewById(R.id.sb_icon3);
        }
    }
    class SearchBottomViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public SearchBottomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.sb_bottom_tv);
        }
    }
}
