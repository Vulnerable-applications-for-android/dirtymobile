package com.example.dirtymobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    TextView etPIN;
    Button btnPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPIN = findViewById(R.id.etPIN);
        btnPIN = findViewById(R.id.btnPIN);

        btnPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPIN = etPIN.getText().toString();
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(enteredPIN.getBytes());
                    byte[] hash = md.digest();
                    String hashed = String.format("%032X", new BigInteger(1, hash));
                    if (hashed.equals("5019B77C0B7C95F1F7C2A597D2F73A0C")) {
                        Intent intent = new Intent(LoginActivity.this, RestrictedActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Bad PIN", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
