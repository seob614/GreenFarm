package com.courr.dongseob.greenfarm.Class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.courr.dongseob.greenfarm.R;

import java.util.ArrayList;

public class Chat_ListViewAdapter extends BaseAdapter {

    private ArrayList<Chat_ListViewItem> listViewItemList = new ArrayList<Chat_ListViewItem>() ;

    // ListViewAdapter의 생성자
    public Chat_ListViewAdapter() {

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

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Chat_ListViewItem listViewItem = listViewItemList.get(position);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.youchat_listviewitem, parent, false);

        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;
        TextView whoTextView = (TextView) convertView.findViewById(R.id.textView3) ;


        // 아이템 내 각 위젯에 데이터 반영
        //iconImageView.setImageDrawable(listViewItem.getIcon());

        titleTextView.setText(listViewItem.getTitle());

        descTextView.setText(listViewItem.getDesc());

        whoTextView.setText(listViewItem.getWho());
        whoTextView.setTextColor(listViewItem.getwhoColor());


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
    public void addItem(String who,String title,String desc ){
        Chat_ListViewItem item = new Chat_ListViewItem();

        if (who.contains("질문")){
            item.setWho(who);
            item.setwhoColor(Color.GREEN);
        }
        if (who.contains("관리자 답변")){
            item.setWho(who);
            item.setwhoColor(Color.RED);
        }

        item.setTitle(title);

        item.setDesc(desc);

        listViewItemList.add(item);
    }

    //수정부분
    public void removeItem(String title){

        for (int i=0;i<listViewItemList.size();i++){
            if (listViewItemList.get(i).getTitle().contains(title)){
                listViewItemList.remove(i);
            }
        }

    }

    public void clear(){
        listViewItemList.clear();
    }
}
