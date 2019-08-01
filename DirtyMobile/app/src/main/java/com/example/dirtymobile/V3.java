package com.example.dirtymobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;


public class V3 extends AppCompatActivity {

    Button btnKey;
    Button btnCustom;
    Button btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v3);

        btnKey = findViewById(R.id.btnKey);
        btnCustom = findViewById(R.id.btnCustom);
        btnRandom = findViewById(R.id.btnRandom);


        btnKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key =  "superkey";
                String secret = "fl4gsanchez";
                String encryptedString = AES.encrypt(secret, key);
                Log.v("secret", encryptedString);
                Toast.makeText(V3.this, "Encrypted data logged", Toast.LENGTH_SHORT).show();
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "thisssecret";
                String secret = "fl4gbelford";
                String encryptedString = AES.badencrypt(secret, key);
                Log.v("secret", encryptedString);
                Toast.makeText(V3.this, "Encrypted data logged", Toast.LENGTH_SHORT).show();
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                String secret = "fl4gmakowski";
                int value = rand.nextInt(1000);
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(Integer.toString(value).getBytes());
                    byte[] hash = md.digest();
                    String key = String.format("%032X", new BigInteger(1, hash));
                    String encryptedString = AES.encrypt(secret, key);
                    Log.v("secret", encryptedString);
                    Toast.makeText(V3.this, "Encrypted data logged", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
    }
}