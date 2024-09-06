package com.courr.dongseob.greenfarm.Class;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.courr.dongseob.greenfarm.My.MychatActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MasterchatlistActivity extends AppCompatActivity {

    private String studentnum;
    private String name;
    private String token;

    private String chatstudentnum;
    private String chatname;
    private String chattoken;

    private ListView listView;

    private ArrayList<String> chatarrayList = new ArrayList<>();
    private ArrayAdapter<String> chatarrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterchatlist);

        Intent intent = getIntent();

        studentnum = intent.getStringExtra("studentnum");

        name = intent.getStringExtra("name");

        token = intent.getStringExtra("token");

        listView = (ListView) findViewById(R.id.list);

        chatarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chatarrayList);
        listView.setAdapter(chatarrayAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("채팅");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());

                }

                chatarrayList.clear();
                chatarrayList.addAll(set);

                chatarrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = ((TextView) view).getText().toString();
                int start = item.indexOf("(");
                int end = item.indexOf(")");

                chatstudentnum = item.substring(0,start);
                chatname = item.substring(start+1,end);
                chattoken = item.substring(end+1,item.length());

                Intent master_intent = new Intent(MasterchatlistActivity.this, MychatActivity.class);

                master_intent.putExtra("studentnum", chatstudentnum);
                master_intent.putExtra("name",chatname);
                master_intent.putExtra("token",chattoken);

                master_intent.putExtra("masterstudentnum",studentnum);
                master_intent.putExtra("mastername",name);
                startActivity(master_intent);
            }
        });





    }
}
