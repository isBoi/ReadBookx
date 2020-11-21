package com.example.readbookx;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readbookx.basic.Chapter;

import java.util.ArrayList;
import java.util.List;

public class contentAdapter extends RecyclerView.Adapter{
    private Context context;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
        list=chapter.getContent();
    }

    private Chapter chapter;
    private List<String> list;/*=new ArrayList<String>(){{
       add("斗罗大陆，天斗帝国西南，法斯诺行省。") ;
       add("圣魂村，如果只是听其名，那么，这绝对是个相当令人惊讶的名字，可实际上，这只不过是法斯诺行省诺丁城南一个只有三百余户的小村而已。之所以名为圣魂，是因为传说中，在百年前这里曾经走出过一位魂圣级别的魂师，从而得名。这也是圣魂村永远的骄傲。") ;
       add("圣魂村外，尽是大片的农耕之地，这里出产的粮食和蔬菜，都要供给到诺丁城，诺丁城在法斯诺行省中虽然算不得大城市，但这里毕竟距离与另一帝国接壤处很近，也自然是两大帝国商人交易的起始地之一，诺丁城因此而繁荣，附带的，令城市周围这些村庄中的平民生活也比其他地方要好的多。");
       add("天刚蒙蒙亮，远处东方升起一抹淡淡的鱼肚白色，毗邻圣魂村的一座只有百余米高的小山包上，却已经多了一道瘦小的身影。");
       add("那是个只有五、六岁的孩子，显然，他经常承受太阳的温暖，皮肤呈现出健康的小麦色，黑色短发看上去很利落，一身衣服虽然朴素，到也干净。");
       add("对于他这么大的孩子来说，攀爬这百米高的山丘可并不是什么容易的事，但奇怪的是，当他来到山顶的制高点时却面不红、气不喘，一副怡然自得的样子。");
       add("男孩儿在山顶上坐了下来，他的双眼死死的盯视着东方那抹渐渐明亮的鱼肚白色，鼻间缓缓吸气，再从口中徐徐吐出，吸气绵绵、呼气微微，竟是形成了一个美妙的循环。");
       add("正在这时，他的眼睛突然瞪大了，远处天边那抹渐渐明亮的鱼肚白色中，仿佛闪过一丝淡淡的紫气，如果不是有着惊人的目力和足够专注的话，是绝对无法发现它存在的。");
       add("紫气的出现，令男孩儿的精神完全集中起来，他甚至不再呼气，只是轻微而徐缓的吸气，同时双眼紧紧的盯视着那抹倏隐倏现的紫色。");
       add("紫气出现的时间并不长，当东方那一抹鱼肚白逐渐被升起的朝阳之色覆盖时，紫气已经完全消失了。");
       add("男孩儿这才缓缓闭上双眼，同时长长的呼出一口体内的浊气。一道白色气流如同匹练般从他口中吐出，然后再徐徐散去。");
       add("静坐半晌，男孩儿才再次睁眼，不知是否因为那天边紫气的沾染，他眼眸中竟然闪烁着一层淡淡的紫意，尽管这紫色并没有持续太长时间就悄然收敛，但当它存在的时候，却是那么清晰。");
       add("颓然一叹，男孩儿做出一个绝不应该出现在他这个年龄的无奈表情，摇了摇头，自言自语的道：“还是不行，我的玄天功依旧无法冲破第一重的瓶颈。这已经整整三个月了，究竟是为什么哪怕是需要依靠紫气东来只能清晨修炼的紫极魔瞳一直都在进步。玄天功不能突破瓶颈，我的玄玉手也无法再做提升。当初我修炼的时候，在第一重到第二重之间，似乎并没有遇到这样的情况。玄天功一共九重，怎么这第一重就如此麻烦难道，是因为这个世界与我那原本的世界不同么”");
       add(" 来到这个世界已经五年多的时间了，眼前的这个孩子，正是当初在唐门跳崖明志的唐三。当他从昏迷中清醒过来的时候，发现除了温暖的感觉什么也做不了。但意料中的死亡并没有来临，很快，他就通过一个挤压的过程来到了这个世界。");
       add("直到很长时间之后，唐三才明白了这是怎么回事。自己没有死，但也已经不再是以前那个唐三。");
       add(" 出生之后，唐三用了接近一年的时间，才学会了这个世界的语言。他还记得，在自己出生的时候，虽然还无法睁眼观看，但却听到一个浑厚的男音在撕心裂肺般的哭喊着。当他学会了这个世界的语言，凭借着过人的记忆回忆时，也只能记起，那个男人似乎在喊，三妹，不要抛下我，而那个男人，就是他的父亲唐昊。他在这个世界的母亲，那时候就已经死于难产之中。");
    }};*/
    public contentAdapter(Context context ,Chapter chapter) {
        this.context = context;
        this.chapter=chapter;
        list=chapter.getContent();
    }
    public String bakcgroundcolor="#d7c6a2";
    public String color="#302303";

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType == TYPE_HEADER) {
            return new TitleViewHolder(LayoutInflater.from(context).inflate(R.layout.title_adapter,parent,false));
        } else {
            ContentViewHolder contentholder= new ContentViewHolder(LayoutInflater.from(context).inflate(R.layout.pargraphadapter,parent,false));
           return contentholder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position!=0) {
            ContentViewHolder contentholder=(ContentViewHolder)holder;
            contentholder.content.setText("\t\t\t" + list.get(position-1));
            contentholder.content.setBackgroundColor(Color.parseColor(bakcgroundcolor));
            contentholder.content.setTextColor(Color.parseColor(color));
            contentholder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContentActivity contextActivity=(ContentActivity)context;
                    if(contextActivity.isvisible){
                        contextActivity.bottom.setVisibility(View.INVISIBLE);
                        contextActivity.top.setVisibility(View.INVISIBLE);
                        contextActivity.statusBar.changeColor(R.color.transparent);
                        contextActivity.statusBar.setStatusBarColor();
                        contextActivity.isvisible=!contextActivity.isvisible;
                    }else{
                        contextActivity.bottom.setVisibility(View.VISIBLE);
                        contextActivity.top.setVisibility(View.VISIBLE);
                        contextActivity.statusBar.changeColor(((ContentActivity) context).statusColor);
                        contextActivity.statusBar.setStatusBarColor();
                        contextActivity.isvisible=!contextActivity.isvisible;
                    }
//                    Toast.makeText(context,"content被点击了",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            TitleViewHolder titleholder=(TitleViewHolder)holder;
            titleholder.title.setText(chapter.getChaptername());
            titleholder.title.setBackgroundColor(Color.parseColor(bakcgroundcolor));
            titleholder.title.setTextColor(Color.parseColor(color));
            titleholder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContentActivity contextActivity=(ContentActivity)context;
                    if(contextActivity.isvisible){
                        contextActivity.bottom.setVisibility(View.INVISIBLE);
                        contextActivity.top.setVisibility(View.INVISIBLE);
                        contextActivity.statusBar.changeColor(R.color.transparent);
                        contextActivity.statusBar.setStatusBarColor();
                        contextActivity.isvisible=!contextActivity.isvisible;
                    }else{
                        contextActivity.bottom.setVisibility(View.VISIBLE);
                        contextActivity.top.setVisibility(View.VISIBLE);
                        contextActivity.statusBar.changeColor(((ContentActivity) context).statusColor);
                        contextActivity.statusBar.setStatusBarColor();
                        contextActivity.isvisible=!contextActivity.isvisible;
                    }
//                    Toast.makeText(context,"title被点击了",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }else{
            return TYPE_NORMAL;
        }
    }
    @Override
    public int getItemCount() {
        return list.size()+1;
    }
    class ContentViewHolder extends RecyclerView.ViewHolder {
            private TextView content;
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.tv_recycleview_content);
        }
       /* public void changebackground(int color){
            content.setBackgroundColor(color);
        }*/
    }
    class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_recycleview_title);
        }
    }
    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

}
