package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDetailsActivity extends AppCompatActivity {
    private EditText tvId, tvName, tvNumber, tvEmail;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvEmail);
        btnUpdate = findViewById(R.id.btnUpdate);

        String id = getIntent().getStringExtra("id");
        String name = "";
        String number = "";
        String email = "";

        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor data = dbHelper.getSingleData(id);

        while (data.moveToNext()){

            name = data.getString(1);
            number = data.getString(2);
            email = data.getString(3);
        }

        tvId.setText("" + id);
        tvName.setText("" + name);
        tvNumber.setText("" + number);
        tvEmail.setText("" + email);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = tvId.getText().toString();
                String name = tvName.getText().toString();
                String number = tvNumber.getText().toString();
                String email = tvEmail.getText().toString();

                int ok = dbHelper.updateData(id, name, number, email);
                if(ok != -1){
                    Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                    startActivity(intent);
                }

                Log.d("datas", id+","+name + ","+ number + "" + email);


            }
        });



    }
}
