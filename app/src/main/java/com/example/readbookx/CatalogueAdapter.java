package com.example.readbookx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookx.basic.Chapter;
import com.example.readbookx.basic.dingdianMethods;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CatalogueAdapter  extends RecyclerView.Adapter{
    Context context;
    int choice=-1;
    private int TYPE_BODY = 1000;
    private int TYPE_BOTTOM = 1001;
    CatalogueAdapter(Context context,Map<String,String> map){
        this.context=context;
        this.map=map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        if(map==null)
            this.map=null;
        this.map = map;
    }

    private Map<String,String> map;/*=new LinkedHashMap<String,String>()
    {{
        put("第一章 斗罗大陆，异界唐三（一）","1");
        put("第一章 斗罗大陆，异界唐三（二）","2");
        put("第一章 斗罗大陆，异界唐三（三）","3");
        put("第一章 斗罗大陆，异界唐三（四）","4");
        put("第二章 废武魂与先天满魂力（一）","5");
        put("第二章 废武魂与先天满魂力（二）","6");
        put("第二章 废武魂与先天满魂力（三）","7");
        put("第二章 废武魂与先天满魂力（四）","8");
        put("第三章 双生武魂(一)","9");
        put("第三章 双生武魂(二)","10");
        put("第三章 双生武魂(三)","11");
        put("第三章 双生武魂(四)","12");
        put("第三章 双生武魂(五)","13");
        put("第三章 双生武魂(六)","14");
        put("第三章 双生武魂(七)","15");
        put("第三章 双生武魂(八)","16");
        put("第三章 双生武魂(九)","17");
        put("第三章 双生武魂(十)","18");
        put("第三章 双生武魂(十一)","19");
    }};*/
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_BODY)
            return new CatalogueViewHolder(LayoutInflater.from(context).inflate(R.layout.catalogueadapter,parent,false));
        else
            return new CataloguebottomViewHolder(LayoutInflater.from(context).inflate(R.layout.catlogue_bottom_adpter,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(map==null){
            CataloguebottomViewHolder cataloguebottomViewHolder=(CataloguebottomViewHolder) holder;
            cataloguebottomViewHolder.tv1.setTextColor(Color.parseColor("#313131"));
        }else{
            if(position!=map.size()){
                CatalogueViewHolder catalogueViewHolder=(CatalogueViewHolder) holder;
                if(position!=choice){
                    catalogueViewHolder.tv1.setTextColor(Color.parseColor("#313131"));
                    Set set=map.keySet();
                    String text=(String)map.keySet().toArray()[position];
                    catalogueViewHolder.tv1.setText(text);
                }else{
                    catalogueViewHolder.tv1.setTextColor(Color.parseColor("#e6585f"));
                    String text=(String)map.keySet().toArray()[position];
                    catalogueViewHolder.tv1.setText(text);
                }
                catalogueViewHolder.wrapper.setOnClickListener(new myonclick(catalogueViewHolder.tv1.getText().toString()));
            }else{
                CataloguebottomViewHolder cataloguebottomViewHolder=(CataloguebottomViewHolder) holder;
                cataloguebottomViewHolder.tv1.setTextColor(Color.parseColor("#313131"));
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(map!=null&&position <map.size()){
            return TYPE_BODY;
        }else{
            return TYPE_BOTTOM;
        }
    }

    @Override
    public int getItemCount() {
        if(map==null)
            return 1;
        return map.size()+1;
    }
    class CatalogueViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout wrapper;
        private TextView tv1;
        public CatalogueViewHolder(@NonNull View itemView) {
            super(itemView);
            wrapper=itemView.findViewById(R.id.cat_wrapper);
            tv1=itemView.findViewById(R.id.cat_tv1);
        }
    }
    class myonclick implements View.OnClickListener{
        myonclick(String url){
            this.url=url;
        }
        String url;
        @Override
        public void onClick(View view) {
            CatalogueActivity.editor.putString("chapterurl",map.get(url));
            CatalogueActivity.editor.apply();
            Intent intent=new Intent(context,ContentActivity.class);
            context.startActivity(intent);
//            Toast.makeText(context,"url为"+map.get(url),Toast.LENGTH_SHORT).show();
        }
    }
    class CataloguebottomViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        public CataloguebottomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.cat_bottom_tv);
        }
    }

}
