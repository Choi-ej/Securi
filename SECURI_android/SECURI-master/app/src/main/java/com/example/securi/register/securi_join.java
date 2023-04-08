package com.example.securi.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class securi_join extends AppCompatActivity {
    private static final String TAG = "securi_join";
    Button joinbtn;
    EditText userpw, useremail;
    private AlertDialog dialog;
    private FirebaseAuth firebaseAuth;

    private String email = "";
    private String password = "";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securi_join);

        userpw = findViewById(R.id.userpw);
        useremail = findViewById(R.id.useremail);
        joinbtn = findViewById(R.id.joinbtn);

        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = useremail.getText().toString();
                password = userpw.getText().toString();

                if (isValidEmail() && isValidPasswd()) {
                    createUser(email, password);
                }
            }
        });
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

    //회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //회원가입 성공
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(securi_join.this, R.string.success_join, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(securi_join.this, securi_login.class);
                    startActivity(intent);
                } else {
                    //회원가입 실패
                    Log.d(TAG, "Failed to read value.");
                    Toast.makeText(securi_join.this, R.string.failed_join, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}