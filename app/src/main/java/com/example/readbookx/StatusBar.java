package com.example.readbookx;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

public class StatusBar {
    private Activity activity;
    private int color;

    //初始化activity、color
    public StatusBar(Activity activity,int color){
        this.activity=activity;
        this.color=color;
    }
    //初始化activity
    public StatusBar(Activity activity){
        this.activity=activity;
    }
    public void changeColor(int color){
        this.color=color;
    }
    //将状态栏设置为传入的color
    public void setStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    //隐藏状态栏
    public void hideStatusBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
