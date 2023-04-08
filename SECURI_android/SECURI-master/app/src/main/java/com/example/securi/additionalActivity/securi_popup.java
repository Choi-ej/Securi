package com.example.securi.additionalActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

import com.example.securi.R;

public class securi_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securi_popup);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
