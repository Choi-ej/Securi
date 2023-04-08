package com.example.securi.navActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;

public class securi_sensor extends AppCompatActivity {
    LinearLayout conCam_extend, conSensor_extend;
    Button addNfc, delNfc;
    TextView nfcNum, nfcDate, conCam, conSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_sensor);
        addNfc = findViewById(R.id.addNfc);
        delNfc = findViewById(R.id.delNfc);
        conCam = findViewById(R.id.conCam);
        conSensor = findViewById(R.id.conSensor);
        nfcNum = findViewById(R.id.nfcNum);
        nfcDate = findViewById(R.id.nfcDate);
    }
}