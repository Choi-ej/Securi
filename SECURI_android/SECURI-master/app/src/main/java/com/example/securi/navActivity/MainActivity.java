package com.example.securi.navActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.securi.additionalActivity.LoadingActivity;
import com.example.securi.R;
import com.example.securi.fragment.Menu1Fragment;
import com.example.securi.fragment.Menu2Fragment;
import com.example.securi.fragment.Menu3Fragment;
import com.example.securi.fragment.Menu4Fragment;
import com.example.securi.fragment.Menu5Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Menu3Fragment frag;
    Menu3Fragment frag2;
    FragmentManager manager;
    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private Menu1Fragment menu1Fragment = new Menu1Fragment();
    private Menu2Fragment menu2Fragment = new Menu2Fragment();
    private Menu3Fragment menu3Fragment = new Menu3Fragment();
    private Menu4Fragment menu4Fragment = new Menu4Fragment();
    private Menu5Fragment menu5Fragment = new Menu5Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        //frag = (Menu3Fragment)manager.findFragmentById(R.id.joinState);
        //frag2 = (Menu3Fragment)manager.findFragmentById(R.id.btnlogin);

        //앱 로딩화면 띄우기
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        //하단 네비게이션 선언
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);



        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        manager.beginTransaction().replace(R.id.frame_layout, new Menu3Fragment()).commit();
        //transaction.replace(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.connect: {
                        manager.beginTransaction().replace(R.id.frame_layout,menu1Fragment).commit();
                        break;
                    }
                    case R.id.autoOpen: {
                        manager.beginTransaction().replace(R.id.frame_layout, menu2Fragment).commit();
                        //transaction.replace(R.id.frame_layout, menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.home: {
                        manager.beginTransaction().replace(R.id.frame_layout, menu3Fragment).commit();
                        //transaction.add(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.history: {
                        manager.beginTransaction().replace(R.id.frame_layout, menu4Fragment).commit();
                        //transaction.replace(R.id.frame_layout, menu4Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.myPage: {
                        manager.beginTransaction().replace(R.id.frame_layout, menu5Fragment).commit();
                        //transaction.replace(R.id.frame_layout, menu5Fragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
}
