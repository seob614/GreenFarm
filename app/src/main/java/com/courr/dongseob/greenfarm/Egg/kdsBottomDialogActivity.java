package com.courr.dongseob.greenfarm.Egg;

import android.media.MediaPlayer;
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
import com.courr.dongseob.greenfarm.Lol_bottomdialogActivity;
import com.courr.dongseob.greenfarm.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kdsBottomDialogActivity extends BottomSheetDialogFragment implements View.OnClickListener {

    public static kdsBottomDialogActivity getInstance() { return new kdsBottomDialogActivity(); }

    private Button bt_exit;

    private static MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_kds_bottom_dialog, container,false);

        setCancelable(false);

        mp = MediaPlayer.create(getActivity(), R.raw.kds);
        mp.setLooping(true);
        mp.start();

        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.kdsmusic).into(imageView1);

        bt_exit = (Button) view.findViewById(R.id.bt_exit);

        bt_exit.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_exit:
                mp.stop();
                break;

        }
        dismiss();
    }
}
