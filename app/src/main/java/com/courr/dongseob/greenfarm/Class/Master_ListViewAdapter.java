package com.courr.dongseob.greenfarm.Class;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.courr.dongseob.greenfarm.R;

import java.util.ArrayList;

public class Master_ListViewAdapter extends BaseAdapter {

    private ArrayList<Master_ListViewItem> listViewItemList = new ArrayList<Master_ListViewItem>() ;

    // ListViewAdapter의 생성자
    public Master_ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.master_listviewitem, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        //수정
        TextView clearTextView = (TextView) convertView.findViewById(R.id.textView3);
        //TextView nownumTextView = (TextView) convertView.findViewById(R.id.textView4) ;
        TextView mynumTextView = (TextView) convertView.findViewById(R.id.textView4) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Master_ListViewItem listViewItem = listViewItemList.get(position);


        // 아이템 내 각 위젯에 데이터 반영


        titleTextView.setText(listViewItem.Reserve_getTitle());

        descTextView.setText(listViewItem.Reserve_getDesc());

        //수정
        clearTextView.setText(listViewItem.Reserve_getClear());

        //nownumTextView.setText(listViewItem.Reserve_getNownum());

        mynumTextView.setText(listViewItem.Reserve_getMynum());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(final String value, final String time) {
        Master_ListViewItem Reserve_item = new Master_ListViewItem();

        Reserve_item.Reserve_setTitle("  "+value);
        Reserve_item.Reserve_setDesc("  "+time);

        //수정
        //Reserve_item.Reserve_setMynum("예약순서: "+mynum);


        listViewItemList.add(Reserve_item);

    }

    //수정부분
    public void removeItem(String time){

        for (int i=0;i<listViewItemList.size();i++){
            if (listViewItemList.get(i).Reserve_getDesc().contains(time)){
                listViewItemList.remove(i);
            }
        }

    }

    //수정부분
    public void clearItem(String clear){

        for (int i=0;i<listViewItemList.size();i++){
            if (listViewItemList.get(i).Reserve_getTitle().contains(clear)){
                Master_ListViewItem listViewItem = listViewItemList.get(i);

                listViewItem.Reserve_setClear("체험완료");

                listViewItemList.set(i,listViewItem);
            }
        }

    }

    //수정부분
    public void nownumItem(String nownum){

        for (int i=0;i<listViewItemList.size();i++){
            Master_ListViewItem listViewItem = listViewItemList.get(i);

            listViewItem.Reserve_setNownum("현재: "+nownum);

            listViewItemList.set(i,listViewItem);
        }
    }

    public String getTile(int i){
        return listViewItemList.get(i).Reserve_getTitle();
    }

    public String getDesc(int i){
        return listViewItemList.get(i).Reserve_getDesc();
    }

    public void clear(){
        listViewItemList.clear();
    }



}
