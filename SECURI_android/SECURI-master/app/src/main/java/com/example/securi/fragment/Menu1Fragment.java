package com.example.securi.fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.securi.R;
import com.example.securi.additionalActivity.securi_addnfc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu1Fragment extends Fragment implements View.OnClickListener {
private FirebaseDatabase mDatabase;
private DatabaseReference mReference;
TextView nfcNum;
Switch conSensor, conCam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.securi_sensor,container,false);
        Button addNfc = view.findViewById(R.id.addNfc);
        Button delNfc = view.findViewById(R.id.delNfc);
        nfcNum = view.findViewById(R.id.nfcNum);
        conSensor = view.findViewById(R.id.conSensor);
        conCam = view.findViewById(R.id.conCam);
        LinearLayout conSensor_extend = view.findViewById(R.id.conSensor_extend);
        LinearLayout conCam_extend = view.findViewById(R.id.conCam_extend);
        addNfc.setOnClickListener(this);
        delNfc.setOnClickListener(this);
        conSensor.setOnClickListener(this);
        conSensor_extend.setOnClickListener(this);
        conCam.setOnClickListener(this);
        conCam_extend.setOnClickListener(this);
        conSensor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mDatabase = FirebaseDatabase.getInstance();
                    mReference = mDatabase.getReference("sensor");
                    mReference.setValue("on");
                    Toast.makeText(getActivity(), "도어센서가 켜졌습니다", Toast.LENGTH_SHORT).show();
                }else{
                    mDatabase = FirebaseDatabase.getInstance();
                    mReference = mDatabase.getReference("sensor");
                    mReference.setValue("off");
                    Toast.makeText(getActivity(), "도어센서가 꺼졌습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        conCam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mDatabase = FirebaseDatabase.getInstance();
                    mReference = mDatabase.getReference("camera");
                    mReference.setValue("on");
                    Toast.makeText(getActivity(), "카메라가 켜졌습니다", Toast.LENGTH_SHORT).show();
                }else{
                    mDatabase = FirebaseDatabase.getInstance();
                    mReference = mDatabase.getReference("camera");
                    mReference.setValue("off");
                    Toast.makeText(getActivity(), "카메라가 꺼졌습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("nfc/Push");
        mReference.addValueEventListener(new ValueEventListener() {
                @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot value : dataSnapshot.getChildren()) {
                        String nfcSerial = value.getValue(String.class);
                        nfcNum.setText(nfcSerial);
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
            case R.id.addNfc:
                mDatabase = FirebaseDatabase.getInstance();
                mReference = mDatabase.getReference("door");
                String status = "b";
                mReference.setValue(status);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String status = "c";
                        mReference.setValue(status);
                    }
                }, 3000);// 3초 정도 딜레이를 준 후 시작
                Toast.makeText(getActivity(), "기기에 nfc카드를 태그해주세요.", Toast.LENGTH_LONG).show();
                break;
            case R.id. delNfc:
                mDatabase = FirebaseDatabase.getInstance();
                mReference = mDatabase.getReference("nfc/Push");
                mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getValue(String.class);
                        dataSnapshot.getRef().removeValue();
                        Toast.makeText(getActivity(), "등록된 카드가 삭제 되었습니다", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
                /*
            case R.id.conCam:
            case R.id.conCam_extend:
                mDatabase = FirebaseDatabase.getInstance();
                mReference = mDatabase.getReference("camera");
                String status = "OFF";
                mReference.setValue(status);
                Toast.makeText(getActivity(), "카메라가 꺼졌습니다", Toast.LENGTH_SHORT).show();

                break;

            case R.id.conSensor:
            case R.id.conSensor_extend:
                mDatabase = FirebaseDatabase.getInstance();
                mReference = mDatabase.getReference("sensor");
                status = "OFF";
                mReference.setValue(status);
                Toast.makeText(getActivity(), "도어센서가 꺼졌습니다", Toast.LENGTH_SHORT).show();
                break;
                */
        }
    }

}