package com.example.dirtymobile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SensitiveExposure extends AppCompatActivity {


    Button btnActivities;
    Button btnContent;
    Button btnIntents;
    Button btnServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensitive_exposure);

        btnContent = findViewById(R.id.btnContent);
        btnActivities = findViewById(R.id.btnActivities);
        btnIntents = findViewById(R.id.btnIntents);
        btnServices = findViewById(R.id.btnServices);

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensitiveExposure.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(SensitiveExposure.this, BadService.class);
                msgIntent.putExtra("value", "whatever");
                startService(msgIntent);
                Toast.makeText(SensitiveExposure.this, "Service called", Toast.LENGTH_LONG).show();
            }
        });

        btnIntents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensitiveExposure.this, BadIntent.class);
                startActivityForResult(intent, 1);
            }
        });

        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "content://com.example.dirtymobile.SecretsProvider";
                String table = "secrets";
                Uri users = Uri.parse(URL + "/" + table);
                Cursor c = managedQuery(users, null, null, null, "user");
                if (c != null) {
                    if (c.moveToFirst()) {
                        do {
                            String user = c.getString(c.getColumnIndex("user"));
                            Toast.makeText(SensitiveExposure.this, URL, Toast.LENGTH_SHORT).show();
                        } while (c.moveToNext());
                    }
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String secret = data.getStringExtra("Secret");
            Toast.makeText(SensitiveExposure.this, secret, Toast.LENGTH_SHORT).show();
        }
    }
}

