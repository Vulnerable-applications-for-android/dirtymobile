package com.example.dirtymobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BadIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_intent);

        Intent intent = new Intent();
        String value = intent.getStringExtra("value");
        if (value == "hack") {
            String flag = AES.decrypt("vqy0D2MoQoIFOXD4kryRIQ==", "hQBSxjgLD8nf_TsB");
            intent.putExtra("Secret", flag);
        } else {
            intent.putExtra("Secret", "I can give you secret");
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
