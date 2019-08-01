package com.example.dirtymobile;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class V5 extends AppCompatActivity {

    Button btnUnencrypted;
    Button btnTLS;
    Button btnPinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v5);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        btnUnencrypted = findViewById(R.id.btnUnencrypted);
        btnTLS = findViewById(R.id.btnTLS);
        btnPinning = findViewById(R.id.btnPinning);

        btnUnencrypted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag1 = AES.decrypt("dnyJwQL8g+TUvdciiMsiaw==", "hQBSxjgLD8nf_TsB");
                String url = "http://stmsolutions.pl/login?username=user&password=" + flag1;
                String response = httpRequest(url);
                Toast.makeText(V5.this, response, Toast.LENGTH_LONG).show();
                 }
        });

        btnTLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                } };
                try {
                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, trustAllCerts, new java.security.SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                    String flag2 = AES.decrypt("aPbuDNuX7FFw/9itN2PuRg==", "hQBSxjgLD8nf_TsB");
                    URL url = new URL("https://stmsolutions.pl/login?username=user&password=" + flag2);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    int status = connection.getResponseCode();
                    String sStatus = Integer.toString(status);
                    Toast.makeText(V5.this, sStatus, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(V5.this, "Error", Toast.LENGTH_LONG).show();
                }
                }
        });

        btnPinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = httpPinnedRequest();
                Toast.makeText(V5.this, response, Toast.LENGTH_LONG).show();
                  }
        });
    }

    public String httpRequest(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                return response.body().string();
            } else {
                return "Error " + Integer.toString(response.code());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Network error";
        }
    }

    public String httpPinnedRequest() {
        String flag = AES.decrypt("me/Yxd5BlF+E4fc/XxcAEA==", "hQBSxjgLD8nf_TsB");
        String hostname = "stmsolutions.pl";
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(hostname, "sha256/G0WukDHSZa9d2Ck25ZHfcB7qZNObt8Kq38ZtSJ2T/J8=")
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build();

        Request request = new Request.Builder()
                .url("https://" + hostname + "/index.php?secret=" + flag)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return Integer.toString(response.code());
        } catch (Exception e) {
            e.printStackTrace();
            return "Network error";
        }
    }

}

