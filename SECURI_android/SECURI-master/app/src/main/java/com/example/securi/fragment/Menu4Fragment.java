package com.example.securi.fragment;

import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
//import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.securi.listView.ListViewAdapter;
import com.example.securi.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Menu4Fragment extends ListFragment {
    ListViewAdapter adapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Adapter 생성 및 Adapter 지정.
        adapter = new ListViewAdapter();
        setListAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("open/Push");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SimpleDateFormat fmt = new SimpleDateFormat("MMM dd일 yyyy HH:mm:ss a", Locale.getDefault());
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    // child 내에 있는 데이터만큼 반복합니다.
                    long timeStamp = messageData.getValue(Long.class);
                    Date date = new Date(timeStamp);
                    String msg = fmt.format(date);
                    adapter.addItem(msg);
                }
                adapter.notifyDataSetChanged();

            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
