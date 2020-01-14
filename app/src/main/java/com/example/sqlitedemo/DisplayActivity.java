package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper db;
    private List<String> listData;
    private EditText edtSearch;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        db = new DatabaseHelper(this);
        listData = new ArrayList<>();
        listView = findViewById(R.id.listView);

        edtSearch = findViewById(R.id.edtSearch);

        getAllData();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ContactDetailsActivity.class);
                intent.putExtra("id", ""+(position+1));
                startActivity(intent);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DisplayActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
