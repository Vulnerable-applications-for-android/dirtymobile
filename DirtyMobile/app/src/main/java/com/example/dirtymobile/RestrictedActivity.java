package com.example.dirtymobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RestrictedActivity extends AppCompatActivity {

    TextView tvRestricted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restricted);

        tvRestricted = findViewById(R.id.tvRestricted);

        String flag = AES.decrypt("yWN0npAbEtsBsLqh/MRNZg==", "hQBSxjgLD8nf_TsB");
        tvRestricted.setText(flag);
    }
}
