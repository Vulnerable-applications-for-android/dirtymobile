package com.example.dirtymobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KeyboardCache extends AppCompatActivity {

    EditText evPassword;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_cache);

        evPassword = findViewById(R.id.etPassword);
        btnSend = findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyboardCache.this, "Verify keyboard dictionary", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
