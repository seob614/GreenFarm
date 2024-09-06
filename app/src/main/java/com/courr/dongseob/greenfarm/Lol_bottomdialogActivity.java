package com.courr.dongseob.greenfarm;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lol_bottomdialogActivity extends BottomSheetDialogFragment implements View.OnClickListener {

    public static Lol_bottomdialogActivity getInstance() { return new Lol_bottomdialogActivity(); }

    private TextView t_link;

    private Button bt_exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lol_bottomdialog, container,false);

        t_link = (TextView) view.findViewById(R.id.t_link);

        String text = "관람하시려면 -여기-를 클릭하세요.";
        t_link.setText(text);

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };

        Pattern pattern1 = Pattern.compile("-여기-");

        Linkify.addLinks(t_link, pattern1, "https://www.youtube.com/channel/UCOQrEO-EW_F06L7_UCErI9Q",null,mTransform);

        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.lolpic).into(imageView1);

        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.lolpic2).into(imageView2);

        bt_exit = (Button) view.findViewById(R.id.bt_exit);

        bt_exit.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_exit:
                break;

        }
        dismiss();
    }
}
