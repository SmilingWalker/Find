package com.example.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button to_File,to_SharePre,to_SqliteDB,to_Permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to_File = findViewById(R.id.to_file);
        to_SharePre = findViewById(R.id.to_SharePref);
        to_SqliteDB = findViewById(R.id.to_SQLite);
        to_Permission = findViewById(R.id.permission);
        to_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FileSaveActivity.class));
            }
        });
        to_SharePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SharePreferenceActivity.class));
            }
        });
        to_SqliteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        to_Permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PermissionActivity.class));
            }
        });
    }
}
