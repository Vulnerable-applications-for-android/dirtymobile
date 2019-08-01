package com.example.dirtymobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class V6 extends AppCompatActivity {

    Button btnInput;
    Button btnURI;
    Button btnSensitive;
    Button btnJavascript;
    Button btnWebview;
    Button btnJavaObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v6);

        btnJavascript = findViewById(R.id.btnJavascript);
        btnSensitive = findViewById(R.id.btnSensitive);
        btnWebview = findViewById(R.id.btnWebview);
        btnJavaObjects = findViewById(R.id.btnJavaObjects);
        btnInput = findViewById(R.id.btnInput);
        btnURI = findViewById(R.id.btnURI);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(V6.this, InputValidation.class);
                startActivity(intent);
            }
        });

        btnURI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "sendsms://message?recipient=666666666&text=ilovepizza";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        btnJavascript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(V6.this, WebV.class);
                intent.putExtra("url", "http://evilsite.local/index.html");
                startActivity(intent);

            }
        });

        btnWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String flag = AES.decrypt("96b5Uf5l2GhFcw+Sch6Cxw==", "hQBSxjgLD8nf_TsB");
                String fileContent = "<script src=\"http://evilsite.local/script.js\"></script>";
                FileOutputStream fos = null;
                String fileName = "site.html";
                String fullFileName = "file:///data/data/com.example.dirtymobile/files/" + fileName;
                try {
                    fos = openFileOutput(fileName, MODE_PRIVATE);
                    fos.write(fileContent.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(V6.this, WebV.class);
                intent.putExtra("url", fullFileName);
                startActivity(intent);
            }
        });

        btnJavaObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(V6.this, WebV.class);
                intent.putExtra("url", "jobject");
                startActivity(intent);
            }
        });

        btnSensitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(V6.this, SensitiveExposure.class);
                startActivity(intent);
            }
        });
    }
}
