package com.courr.dongseob.greenfarm.My;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.courr.dongseob.greenfarm.BoothExperience.VRActivity;
import com.courr.dongseob.greenfarm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PointerActivity extends AppCompatActivity {

    private ListView boothlist;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointer);

        boothlist = (ListView) findViewById(R.id.boothlist);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        boothlist.setAdapter(arrayAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("포인트");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                set.clear();

                while (i.hasNext()) {
                    final String value = ((DataSnapshot) i.next()).getKey();

                    final DatabaseReference pointreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(value);

                    pointreference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            pointreference.removeEventListener(this);
                            arrayList.add(value+ " : "+dataSnapshot.getValue().toString());
                            arrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        boothlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                int a = item.indexOf(":");
                final String reference_str = item.substring(0,a-1);

                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                View mView = layoutInflaterAndroid.inflate(R.layout.point_dialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);
                final EditText InputDialogEditText = (EditText) mView.findViewById(R.id.InputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                            DatabaseReference itemreference = FirebaseDatabase.getInstance().getReference().child("포인트").child(reference_str);

                            itemreference.setValue(InputDialogEditText.getText().toString());

                            arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });
    }
}
