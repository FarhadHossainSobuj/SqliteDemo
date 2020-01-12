package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper db;
    private List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        db = new DatabaseHelper(this);
        listData = new ArrayList<>();
        listView = findViewById(R.id.listView);

        getAllData();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowDetailsActivity.class);
                intent.putExtra("id", ""+(position+1));
                startActivity(intent);
            }
        });



    }
    private void getAllData(){
        Cursor data = db.getAllData();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            Log.d("name", data.getString(1));
        }


    }
}
