package com.example.securi.listView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class securi_datainfo extends AppCompatActivity {
    ListView listview_datainfo;
    ArrayList<ListViewItem_datainfo> list;
    ListViewAdapter_datainfo adapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private DatabaseReference mReference2;
    private ChildEventListener mChild;

    public securi_datainfo() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_datainfo);
        listview_datainfo = findViewById(R.id.listview_datainfo);
        list = new ArrayList<>();

        //어댑터에 추가
        adapter = new ListViewAdapter_datainfo(securi_datainfo.this, list);
        listview_datainfo.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("1ZJbhzSqJ457JgqfCwJ1s5lsDurmTmWNNAXc8ILxotkM/sheet");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MMM dd일\bHH:mm:ss a", Locale.getDefault());
                String baseUrl = "https://drive.google.com/uc?export=view&id=";
                String urlFmt, fmt2, timestamp;
                int count = 0;
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    // child 내에 있는 데이터만큼 반복합니다.
                    timestamp = messageData.getKey();
                    long timestamp2 = Long.parseLong(timestamp);
                    Date date = new Date(timestamp2);
                    String msg = fmt.format(date);
                    String url = messageData.getValue().toString();
                    urlFmt = url.replace("{Id=", "");
                    fmt2 = urlFmt.replace("}", "");
                    url = baseUrl + fmt2;
                    adapter.addDTO(msg,url);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listview_datainfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem_datainfo dto = (ListViewItem_datainfo) adapter.getItem(position);
            }
        });
    }
}
