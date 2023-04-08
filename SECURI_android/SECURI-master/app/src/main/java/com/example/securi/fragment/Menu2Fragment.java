package com.example.securi.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.securi.R;
import com.example.securi.listView.securi_datainfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu2Fragment extends Fragment implements View.OnClickListener{

    TextView doorStateTxt,lockState,serverState, checkHistory, doorOpen;
    LinearLayout checkHistory_extend, doorOpen_extend;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabase2;
    private DatabaseReference mReference;
    private DatabaseReference mReference2;
    private DatabaseReference mReference3;
    private ChildEventListener mChild;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.securi_autoopen, container, false);
        checkHistory = view.findViewById(R.id.checkHistory);
        doorOpen = view.findViewById(R.id.doorOpen);
        doorStateTxt = view.findViewById(R.id.doorStateTxt);
        lockState = view.findViewById(R.id.lockState);
        serverState = view.findViewById(R.id.serverState);
        checkHistory_extend = view.findViewById(R.id.checkHistory_extend);
        doorOpen_extend = view.findViewById(R.id.doorOpen_extend);
        checkHistory_extend.setOnClickListener(this);
        doorOpen_extend.setOnClickListener(this);
        checkHistory.setOnClickListener(this);

        mDatabase2 = FirebaseDatabase.getInstance().getReference("lock");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                    if (value.equals(state)) {
                        doorStateTxt.setText("출입문 잠금 중");
                        doorStateTxt.setTextColor(Color.BLUE);
                        lockState.setText("잠금장치\n작동 중");
                        lockState.setTextColor(Color.BLUE);
                    } else {
                        doorStateTxt.setText("출입문 잠금 해제");
                        doorStateTxt.setTextColor(Color.RED);
                        lockState.setText("잠금장치\n작동 중지");
                        lockState.setTextColor(Color.RED);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase2 = FirebaseDatabase.getInstance().getReference("lock");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                if (value.equals(state)) {
                    doorStateTxt.setText("출입문 잠금 중");
                    doorStateTxt.setTextColor(Color.BLUE);
                    lockState.setText("잠금장치\n작동 중");
                    lockState.setTextColor(Color.BLUE);

                } else {
                    doorStateTxt.setText("출입문 잠금 해제");
                    doorStateTxt.setTextColor(Color.RED);
                    lockState.setText("잠금장치\n작동 중지");
                    lockState.setTextColor(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mDatabase2 = FirebaseDatabase.getInstance().getReference("server");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                String value = dataSnapshot.getValue(String.class);
                if (value.equals(state)) {
                    serverState.setText("서버 상태\n정상");
                    serverState.setTextColor(Color.BLUE);

                } else {
                    serverState.setText("서버 상태\n불안정");
                    serverState.setTextColor(Color.RED);
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
            case R.id.checkHistory:
            case R.id.checkHistory_extend:
                Intent intent = new Intent(getActivity(), securi_datainfo.class);
                startActivity(intent);
                break;
            case R.id.doorOpen:
            case R.id.doorOpen_extend:
                mDatabase = FirebaseDatabase.getInstance();
                mReference3 = mDatabase.getReference("door");
                String status = "a";
                mReference3.setValue(status);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String status = "c";
                        mReference3.setValue(status);
                    }
                }, 3000);// 3초 정도 딜레이를 준 후 시작
                Toast.makeText(getActivity(), "문이 열렸습니다.", Toast.LENGTH_SHORT).show();
                break;
        }

        }
    }
