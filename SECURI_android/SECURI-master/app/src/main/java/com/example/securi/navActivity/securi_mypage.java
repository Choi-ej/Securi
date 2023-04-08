package com.example.securi.navActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;

public class securi_mypage extends AppCompatActivity {
    Button serverInfo, dataInfo, notiInfo;
    TextView myId, myEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_mypage);
        serverInfo = findViewById(R.id.serverInfo);
        dataInfo = findViewById(R.id.dataInfo);
        notiInfo = findViewById(R.id.notiInfo);
        myId = findViewById(R.id.myId);
        myEmail = findViewById(R.id.myEmail);
    }
}