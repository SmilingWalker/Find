package com.example.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.find.DB.BookDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileSaveActivity extends AppCompatActivity {
    Button SharePre;
    Button create_DB;
    private BookDB bookDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_out);
        create_DB = findViewById(R.id.create_SQLite);
        final Button fileWriter = (Button)findViewById(R.id.save_text);
        fileWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText)findViewById(R.id.text) ;
                String str = text.getText().toString();
                try {
                    FileOutputStream outputStream =openFileOutput("data.txt",MODE_PRIVATE);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    writer.write(str);
                    if(writer!=null)
                        writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button getData = (Button)findViewById(R.id.get_text);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText data = (EditText)findViewById(R.id.data);
                try {
                    FileInputStream fileOutputStream = openFileInput("data.text");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fileOutputStream));
                    String str = "";
                    StringBuilder builder = new StringBuilder();
                    while ((str=reader.readLine())!="")
                        builder.append(str);
                    data.setText(builder.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        SharePre = (Button)findViewById(R.id.to_sharePre);
        SharePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FileSaveActivity.this,SharePreferenceActivity.class));
            }
        });
        create_DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               bookDB = new BookDB(FileSaveActivity.this,"BookStore.db",null,1);
               bookDB.getWritableDatabase();
            }
        });
    }
}
