package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private TextView tvPhone;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String name = getIntent().getStringExtra("name");
        final String number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");


        tvDisplay = findViewById(R.id.tvDisplay);
        tvDisplay.setText("Name: " + name + "\nNumber: " + number + "\nEmail: " + email);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+ number));
                startActivity(intent);
            }
        });
    }
}
