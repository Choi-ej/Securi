package com.example.securi.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.securi.R;
import com.example.securi.navActivity.securi_autoopen;
import com.example.securi.register.securi_login;
import com.example.securi.additionalActivity.securi_outdoorinfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu3Fragment extends Fragment implements View.OnClickListener {
    TextView doorState,sensorState,camState,outInfo, autoOpen;
    LinearLayout outInfo_extend, autoOpen_extend;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    private DatabaseReference mReference2;
    private DatabaseReference mReference3;
    FragmentManager manager;
    private Menu2Fragment menu2Fragment = new Menu2Fragment();
    public Menu3Fragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.securi_home,container,false);
        outInfo = view.findViewById(R.id.outInfo);
        autoOpen = view.findViewById(R.id.autoOpen);
        outInfo_extend = view.findViewById(R.id.outInfo_extend);
        autoOpen_extend = view.findViewById(R.id.autoOpen_extend);
        doorState = view.findViewById(R.id.doorState);
        camState = view.findViewById(R.id.camState);
        sensorState = view.findViewById(R.id.sensorState);
        outInfo.setOnClickListener(this);
        autoOpen.setOnClickListener(this);
        outInfo_extend.setOnClickListener(this);
        autoOpen_extend.setOnClickListener(this);
        manager = getFragmentManager();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("lock");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                    if(value.equals(state)){
                        doorState.setText("잠금 ON");
                        doorState.setTextColor(Color.BLUE);

                    }else{
                        doorState.setText("잠금 OFF");
                        doorState.setTextColor(Color.RED);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mReference2 = mDatabase.getReference("camera");
        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                    if (value.equals(state)) {
                        camState.setText("ON");
                        camState.setTextColor(Color.BLUE);
                    } else {
                        camState.setText("OFF");
                        camState.setTextColor(Color.RED);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReference3 = mDatabase.getReference("sensor");
        mReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                    if (value.equals(state)) {
                        sensorState.setText("ON");
                        sensorState.setTextColor(Color.BLUE);

                    } else {
                        sensorState.setText("OFF");
                        sensorState.setTextColor(Color.RED);
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.outInfo:
            case R.id.outInfo_extend:
                Intent intent = new Intent(getActivity(), securi_outdoorinfo.class);
                startActivity(intent);
                break;
            case R.id.autoOpen:
            case R.id.autoOpen_extend:
                manager.beginTransaction().replace(R.id.frame_layout, menu2Fragment).commit();
                break;
        }
    }
}