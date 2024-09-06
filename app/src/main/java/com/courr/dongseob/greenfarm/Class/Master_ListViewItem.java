package com.courr.dongseob.greenfarm.Class;

import android.graphics.drawable.Drawable;

public class Master_ListViewItem {
    private Drawable Reserve_iconDrawable ;
    private String Reserve_titleStr ;
    private String Reserve_descStr ;

    private String Reserve_clearStr;
    private String Reserve_nownumStr;
    private String Reserve_mynumStr;

    public void Reserve_setIcon(Drawable icon) {
        Reserve_iconDrawable = icon ;
    }
    public void Reserve_setTitle(String title) {
        Reserve_titleStr = title ;
    }
    public void Reserve_setDesc(String desc) {
        Reserve_descStr = desc ;
    }

    public void Reserve_setClear(String clear) {
        Reserve_clearStr = clear ;
    }
    public void Reserve_setNownum(String nownum) {
        Reserve_nownumStr = nownum ;
    }
    public void Reserve_setMynum(String mynum) {
        Reserve_mynumStr = mynum ;
    }


    public Drawable Reserve_getIcon() {
        return this.Reserve_iconDrawable ;
    }
    public String Reserve_getTitle() {
        return this.Reserve_titleStr ;
    }
    public String Reserve_getDesc() {
        return this.Reserve_descStr ;
    }

    public String Reserve_getClear() {
        return this.Reserve_clearStr ;
    }
    public String Reserve_getNownum() {
        return this.Reserve_nownumStr ;
    }
    public String Reserve_getMynum() {
        return this.Reserve_mynumStr ;
    }



}

