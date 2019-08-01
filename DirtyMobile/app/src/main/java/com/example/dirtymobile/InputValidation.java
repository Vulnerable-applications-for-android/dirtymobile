package com.example.dirtymobile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputValidation extends AppCompatActivity {

    EditText etName1;
    Button btnName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_validation);

        etName1 = findViewById(R.id.etName1);
        btnName1 = findViewById(R.id.btnName1);


        btnName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqlitedb = openOrCreateDatabase("secretdb",MODE_PRIVATE,null);
                String user = etName1.getText().toString();
                String sql = "SELECT * FROM Credentials WHERE username = '" +  user + "'";
                try {
                    Cursor cursor = sqlitedb.rawQuery(sql, null);
                    if (cursor.moveToFirst()) {
                        String str = cursor.getString(cursor.getColumnIndex("Password"));
                        Toast.makeText(InputValidation.this, str, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InputValidation.this, "No such user", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(InputValidation.this, "SQL Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
