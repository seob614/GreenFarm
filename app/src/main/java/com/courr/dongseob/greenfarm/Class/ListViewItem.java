package com.courr.dongseob.greenfarm.Class;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.R;

public class ListViewItem {

    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;

    private int deccolor;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    //수정
    public void setDecColor(int color){
        deccolor = color;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    //수정
    public int getDecColor(){
        return this.deccolor;
    }
}
