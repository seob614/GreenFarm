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
import java.util.Set;

public class Reserve_ListViewAdapter extends BaseAdapter {
    private ArrayList<Reserve_ListViewItem> listViewItemList = new ArrayList<Reserve_ListViewItem>() ;

    // ListViewAdapter의 생성자
    public Reserve_ListViewAdapter() {

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
            convertView = inflater.inflate(R.layout.reserve_listviewitem, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        //수정
        TextView clearTextView = (TextView) convertView.findViewById(R.id.textView3);
        //TextView nownumTextView = (TextView) convertView.findViewById(R.id.textView4) ;
        TextView mynumTextView = (TextView) convertView.findViewById(R.id.textView4) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Reserve_ListViewItem listViewItem = listViewItemList.get(position);


        // 아이템 내 각 위젯에 데이터 반영
        //iconImageView.setImageDrawable(listViewItem.getIcon());
        Glide.with(convertView).load(listViewItem.Reserve_getIcon()).into(iconImageView);

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
    public void addItem(final Context context, final String value, final String time, final String mynum) {
        Reserve_ListViewItem Reserve_item = new Reserve_ListViewItem();

        if (value.contains("VR체험")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.p_it));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);


            listViewItemList.add(Reserve_item);
        }
        if (value.contains("드론체험")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.btdrone));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);


            listViewItemList.add(Reserve_item);
        }
        if (value.contains("쌀을 이용한 요거트 만들기 체험")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.yogurt));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("Flower & Fragrance")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.flowermain));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("디퓨저 만들기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.diffuser));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("리틀 토피어리")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.ff));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("취향대로 닭꼬치")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.stick));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        /*
        if (value.contains("스칸디아모스 가습기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.humidifier));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("햄 in The 샌드위치")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.sandwich));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("치즈만들기 체험")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.cheese));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        */
        //if (value.contains("에너지바,무엇이든지 통조림")){
        //    Reserve_item.Reserve_setTitle("  "+value);
        //    Reserve_item.Reserve_setDesc("  "+time);
        //    Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.juice2));

            //수정
        //    Reserve_item.Reserve_setMynum("나의순서: "+mynum);

        //    listViewItemList.add(Reserve_item);
        //}

        if (value.contains("New 에너지Baam")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.energybar));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }

        if (value.contains("무엇이든지 통조림")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.can));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }

        if (value.contains("귀생충")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.earing));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        if (value.contains("3D 마스크 만들기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.mask));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
/*
        if (value.contains("Oatmeal Soap")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.soap));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        */

        if (value.contains("내 친구 슬라임")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.slime));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        //if (value.contains("친환경 자원과 에너지")){
        //    Reserve_item.Reserve_setTitle("  "+value);
        //    Reserve_item.Reserve_setDesc("  "+time);
        //    Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.greencar));

            //수정
        //    Reserve_item.Reserve_setMynum("나의순서: "+mynum);

        //    listViewItemList.add(Reserve_item);
        //}


        if (value.contains("누름꽃 텀블러 만들기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.tumblr));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        /*

        if (value.contains("로즈 미스트")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.rosemist));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }

        if (value.contains("친환경 가방만들기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.ecobag));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
*/

        if (value.contains("테라리움")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.terrarium));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }

        if (value.contains("우리 쌀 소떡소떡 만들기")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.sikhye));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }
        /*
        if (value.contains("세계의 맛과 멋 한마당")){
            Reserve_item.Reserve_setTitle("  "+value);
            Reserve_item.Reserve_setDesc("  "+time);
            Reserve_item.Reserve_setIcon(ContextCompat.getDrawable(context, R.drawable.globalfood));

            //수정
            Reserve_item.Reserve_setMynum("나의순서: "+mynum);

            listViewItemList.add(Reserve_item);
        }

*/
    }

    //수정부분
    public void removeItem(String title){

        for (int i=0;i<listViewItemList.size();i++){
            if (listViewItemList.get(i).Reserve_getTitle().contains(title)){
                listViewItemList.remove(i);
            }
        }

    }

    //수정부분
    public void clearItem(String clear){

        for (int i=0;i<listViewItemList.size();i++){
            if (listViewItemList.get(i).Reserve_getTitle().contains(clear)){
                Reserve_ListViewItem listViewItem = listViewItemList.get(i);

                listViewItem.Reserve_setClear("체험완료");

                listViewItemList.set(i,listViewItem);
            }
        }

    }

    //수정부분
    public void nownumItem(String nownum){

        for (int i=0;i<listViewItemList.size();i++){
            Reserve_ListViewItem listViewItem = listViewItemList.get(i);

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
