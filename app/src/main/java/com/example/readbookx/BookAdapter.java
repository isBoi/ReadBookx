package com.example.readbookx;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.readbookx.basic.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter{
    BookAdapter(Context context,List list){
        this.context=context;
        this.list=list;
    }
    private Context context;
    private List<Book> list;/*=new ArrayList<Book>(){{
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
    }};*/
    private int TYPE_TOP=1000;
    private int TYPE_BODY=1001;
    private int TYPE_BOTTOM=1002;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_TOP)
            return new BookTopViewHolder(LayoutInflater.from(context).inflate(R.layout.booktopadapter,parent,false));
        else if(viewType==TYPE_BODY)
            return new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.bookadapter,parent,false));
        else
            return new BookBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.bookbottomadapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position==0){
            BookTopViewHolder bookTopViewHolder=(BookTopViewHolder) holder;
        }else if(position==list.size()+1){
            BookBottomViewHolder bookBottomViewHolder=(BookBottomViewHolder) holder;
        }else{
            BookViewHolder bookViewHolder=(BookViewHolder) holder;
            bookViewHolder.author.setText(list.get(position-1).getAuthor());
            bookViewHolder.state.setText(list.get(position-1).getState());
            bookViewHolder.bookname.setText(list.get(position-1).getBookName());
            Glide.with(context).load(list.get(position-1).getImageurl()).into(bookViewHolder.imageView);
            bookViewHolder.linearLayout.setOnClickListener(new MyClick(list.get(position-1).getBookid(),list.get(position-1).getBookName()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+2;
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_TOP;
        }
        else if(position <=list.size()){
            return TYPE_BODY;
        }else{
            return TYPE_BOTTOM;
        }
    }
    class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView bookname,state,author;
        private LinearLayout linearLayout;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.ba_image);
            bookname=itemView.findViewById(R.id.ba_bookname);
            state=itemView.findViewById(R.id.ba_state);
            author=itemView.findViewById(R.id.ba_author);
            linearLayout=itemView.findViewById(R.id.ba_ll);
        }
    }
    class BookTopViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1;
        public BookTopViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.ba_top_tv);
        }
    }
    class BookBottomViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1;
        public BookBottomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.ba_bottom_tv);
        }
    }
    class MyClick implements View.OnClickListener{
        MyClick(int id,String bookname){
            this.id=id;
            this.bookname=bookname;
        }
        private int id;
        private String bookname;
        @Override
        public void onClick(View view) {
            BookshelfActivity.editor.putString("father","shelf");
            BookshelfActivity.editor.putInt("id",id);
            BookshelfActivity.editor.putString("bookname",bookname);
            BookshelfActivity.editor.apply();
            Intent intent=new Intent(context,CatalogueActivity.class);
            context.startActivity(intent);
            /*Toast.makeText(context,"url"+url,Toast.LENGTH_SHORT).show();*/
        }
    }
}
