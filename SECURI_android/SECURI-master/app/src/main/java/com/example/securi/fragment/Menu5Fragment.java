package com.example.securi.fragment;

import android.content.Intent;
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

import com.example.securi.R;
import com.example.securi.listView.securi_datainfo;
import com.example.securi.additionalActivity.securi_notiinfo;
import com.example.securi.additionalActivity.securi_serverinfo;

public class Menu5Fragment  extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.securi_mypage, container, false);
        TextView serverInfo = view.findViewById(R.id.serverInfo);
        TextView dataInfo = view.findViewById(R.id.dataInfo);
        TextView notiInfo = view.findViewById(R.id.notiInfo);
        LinearLayout serverInfo_extend = view.findViewById(R.id.serverInfo_extend);
        LinearLayout dataInfo_extend = view.findViewById(R.id.dataInfo_extend);
        LinearLayout notiInfo_extend = view.findViewById(R.id.notiInfo_extend);
        serverInfo.setOnClickListener(this);
        dataInfo.setOnClickListener(this);
        notiInfo.setOnClickListener(this);
        serverInfo_extend.setOnClickListener(this);
        dataInfo_extend.setOnClickListener(this);
        notiInfo_extend.setOnClickListener(this);
        return view;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.serverInfo:
            case R.id.serverInfo_extend:
                Intent intent = new Intent(getActivity(), securi_serverinfo.class);
                startActivity(intent);
                break;
            case R.id.dataInfo:
            case R.id.dataInfo_extend:
                Intent intent2 = new Intent(getActivity(), securi_datainfo.class);
                startActivity(intent2);
                break;
            case R.id.notiInfo:
            case R.id.notiInfo_extend:
                Intent intent3 = new Intent(getActivity(), securi_notiinfo.class);
                startActivity(intent3);
                break;
        }
    }
}
