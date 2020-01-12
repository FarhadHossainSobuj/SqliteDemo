package com.example.sqlitedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtNumber, edtEmail;
    private Button btnSubmit, btnShow;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnShow = findViewById(R.id.btnShow);
        dbHelper = new DatabaseHelper(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String number = edtNumber.getText().toString();
                String email = edtEmail.getText().toString();

                if(dbHelper.insert( name, number, email)){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                startActivity(intent);
//                Cursor data = dbHelper.showData();
//                List<String> list = new ArrayList<>();
//                String datas;
//                StringBuilder b = new StringBuilder();
//                while(data.moveToNext()){
//                    b.append(data.getString(0) + " " + data.getString(1) + " " + data.getString(2) + "\n");
//                    datas = data.getString(0) + " " + data.getString(1) + " " + data.getString(2) + "\n";
//                    Log.d("data", datas);
//                    //list.add(datas);
//                }
//
//
//                showDialog(b.toString());
            }
        });
    }

}
