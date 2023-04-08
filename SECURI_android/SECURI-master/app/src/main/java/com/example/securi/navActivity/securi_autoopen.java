package com.example.securi.navActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.securi.R;
import com.example.securi.listView.securi_datainfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class securi_autoopen extends Activity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference2;

    TextView doorOpen, checkHistory;
    TextView doorStateTxt, serverState, lockState;
    LinearLayout doorOpen_extend, checkHistory_extend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_autoopen);
        serverState = findViewById(R.id.serverState);
        lockState = findViewById(R.id.lockState);
        doorStateTxt = findViewById(R.id.doorStateTxt);
        doorOpen = findViewById(R.id.doorOpen);
        doorOpen_extend = findViewById(R.id.doorOpen_extend);
        checkHistory = findViewById(R.id.checkHistory);
        checkHistory_extend = findViewById(R.id.checkHistory_extend);

        mDatabase = FirebaseDatabase.getInstance();
        mReference2 = mDatabase.getReference().child("server");
        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String state = "on";
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    if (messageData.equals(state)) {
                        serverState.setText("서버상태\n정상");
                        serverState.setTextColor(Color.BLUE);


                    } else {
                        serverState.setText("서버상태\n불안정");
                        serverState.setTextColor(Color.RED);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        checkHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), securi_datainfo.class);
                startActivity(intent);
            }
        });
        checkHistory_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), securi_datainfo.class);
                startActivity(intent);
            }
        });


    }
}