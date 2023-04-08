package com.example.securi.additionalActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;

public class securi_outdoorinfo extends AppCompatActivity {
    WebView web;
    WebSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_outdoorinfo);
        web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://192.168.43.121:81/stream");
    }
}