package com.example.dirtymobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class URIActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri);

        Intent intent = getIntent();
        System.out.println(Intent.ACTION_VIEW);
        System.out.println(intent.getAction());

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String recipient = uri.getQueryParameter("recipient");
            if (uri.getQueryParameter("secret") != null) {
                String text = AES.decrypt("na5VJyRE+zYpacKMQoraHQ==", "hQBSxjgLD8nf_TsB");
                String response = sendSMS(recipient, text);
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            } else {
                String text = uri.getQueryParameter("text");
                String response = sendSMS(recipient, text);
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String sendSMS(String recipient, String text) {
        try {
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(recipient, null, text, null, null);
            return "SMS Sent";
        } catch (Exception e) {
            e.printStackTrace();
            return "SMS Sending Error";
        }
    }

}
