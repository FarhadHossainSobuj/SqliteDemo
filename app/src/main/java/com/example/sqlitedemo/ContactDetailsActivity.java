package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView tvId, tvName, tvNumber, tvEmail;
    private Button btnUpdate;
    private ImageView imgCall, imgSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        //tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvEmail);
        btnUpdate = findViewById(R.id.btnEdit);
        imgCall = findViewById(R.id.imgCall);
        imgSendMessage = findViewById(R.id.imgMessage);

        final String id = getIntent().getStringExtra("id");
        String name = "";
        String number = "";
        String email = "";

        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor data = dbHelper.getSingleData(id);

        while (data.moveToNext()) {

            name = data.getString(1);
            number = data.getString(2);
            email = data.getString(3);
        }

        //tvId.setText("" + id);
        tvName.setText("" + name);
        tvNumber.setText("" + number);
        tvEmail.setText("" + email);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        final String finalNumber = number;
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + finalNumber));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });



    }
}
