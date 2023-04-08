package com.example.securi.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.navActivity.MainActivity;
import com.example.securi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class securi_login extends AppCompatActivity {
TextView createAccount, findAccount;
EditText userid, userpw;
Button loginbtn;
private FirebaseAuth firebaseAuth;
private String email = "";
private String password = "";
private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_login);
        firebaseAuth = FirebaseAuth.getInstance();

        userid = findViewById(R.id.userid);
        userpw = findViewById(R.id.userpw);
        loginbtn = findViewById(R.id.loginbtn);
        createAccount = findViewById(R.id.createAccount);
        findAccount = findViewById(R.id.findAccount);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = userid.getText().toString();
                password = userpw.getText().toString();

                if (isValidEmail() && isValidPasswd()) {
                    loginUser(email, password);
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(securi_login.this, securi_join.class);
                startActivity(intent);
            }
        });
    }
        public void login(View view) {

        }
        //이메일 유효성 검사
        private boolean isValidEmail() {
            if (email.isEmpty()) {
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return false;
            } else {
                return true;
            }
        }

        private boolean isValidPasswd() {
            if (password.isEmpty()) {
                return false;
            } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                return false;
            } else {
                return true;
            }
        }
        private void loginUser(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // 로그인 성공
                    Toast.makeText(securi_login.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(securi_login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 실패
                    Toast.makeText(securi_login.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}