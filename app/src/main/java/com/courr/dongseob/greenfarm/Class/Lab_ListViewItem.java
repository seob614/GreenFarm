package com.courr.dongseob.greenfarm.Class;

import android.graphics.drawable.Drawable;

public class Lab_ListViewItem {
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

}

