package com.example.securi.navActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class securi_home extends AppCompatActivity {
TextView camState, sensorState, doorState, autoOpen, outInfo;
private FirebaseDatabase mDatabase;
private DatabaseReference mReference2;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.securi_home);
            autoOpen = findViewById(R.id.autoOpen);
            outInfo = findViewById(R.id.outInfo);
            doorState = findViewById(R.id.doorState);
            camState = findViewById(R.id.camState);
            sensorState = findViewById(R.id.sensorState);


    }
}