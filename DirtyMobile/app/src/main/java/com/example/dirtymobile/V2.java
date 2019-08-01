package com.example.dirtymobile;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class V2 extends AppCompatActivity {

    Button btnShared;
    Button btnSqlite;
    Button btnInternal;
    Button btnExternal;
    Button btnLogs;
    Button btnKeyboard;
    Button btnIPC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Request permissions to write to external storage.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v2);

        btnShared = findViewById(R.id.btnShared);
        btnSqlite = findViewById(R.id.btnSqlite);
        btnInternal = findViewById(R.id.btnInternal);
        btnExternal = findViewById(R.id.btnExternal);
        btnLogs = findViewById(R.id.btnLogs);
        btnKeyboard = findViewById(R.id.btnKeyboard);
        btnIPC = findViewById(R.id.btnIPC);


        btnShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = getSharedPreferences("credentials", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                String flag = AES.decrypt("BIu9CUkNvAdZRrvY7l4o6g==", "hQBSxjgLD8nf_TsB");
                editor.putString("username", "secret");
                editor.putString("password", flag);
                editor.commit();
                Toast.makeText(V2.this, "Data saved to shared preferences", Toast.LENGTH_LONG).show();
            }
        });

        btnSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqlitedb = openOrCreateDatabase("secretdb",MODE_PRIVATE,null);
                String flag = AES.decrypt("F/cOLFq9RPfeNyw+FcuPqQ==", "hQBSxjgLD8nf_TsB");
                String query = "INSERT INTO Credentials VALUES('secret', '"+flag+"');";
                sqlitedb.execSQL(getString(R.string.create_db));
                sqlitedb.execSQL(query);
                sqlitedb.close();
                Toast.makeText(V2.this, "Data saved to database", Toast.LENGTH_LONG).show();
            }
        });


        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = AES.decrypt("96b5Uf5l2GhFcw+Sch6Cxw==", "hQBSxjgLD8nf_TsB");
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput("secretfilein", MODE_PRIVATE);
                    fos.write(flag.getBytes());
                    fos.close();
                    Toast.makeText(V2.this, "File saved to internal storage", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(V2.this, "File save error", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(V2.this, "File save error", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = Environment.getExternalStorageState();

                File file = new File (Environment.getExternalStorageDirectory(), "secretfileex");
                String flag = AES.decrypt("F4ycHQnQrHmv71dVDxu92w==", "hQBSxjgLD8nf_TsB");
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(file);
                    fos.write(flag.getBytes());
                    fos.close();
                    Toast.makeText(V2.this, "File saved to external storage", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(V2.this, "File save error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(V2.this, "File save error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            };
        });

        btnLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = AES.decrypt("ELtZHprWKpkKAz+yU98XHw==", "hQBSxjgLD8nf_TsB");
                Log.v("secret", flag);
                Toast.makeText(V2.this, "Data logged", Toast.LENGTH_LONG).show();
            }
        });

        btnKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(V2.this, KeyboardCache.class);
                startActivity(intent);
            }
        });

        btnIPC.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(V2.this, URL, Toast.LENGTH_LONG).show();
                        } while (c.moveToNext());
                    }
                }

            }
        });
    }
}
