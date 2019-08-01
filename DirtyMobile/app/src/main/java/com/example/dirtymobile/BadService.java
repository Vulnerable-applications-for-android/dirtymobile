package com.example.dirtymobile;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;

public class BadService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";

    public BadService() {
        super("BadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg = intent.getStringExtra("value");
        if (msg == "hack") {
            String flag = AES.decrypt("f/iCVHYS3GKQFl3JO2T4FQ==", "hQBSxjgLD8nf_TsB");
            String resultTxt = flag;
            Log.v("ServiceResult", resultTxt);
        } else {
            String resultTxt = "I can log secret";
            Log.v("ServiceResult", resultTxt);
        }

    }
}