package com.example.find;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharePreferenceActivity extends AppCompatActivity {
    Button save,find;
    EditText save_key,save_value,find_key;
    TextView find_value;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepre);
        save = findViewById(R.id.save_share_data);
        find = findViewById(R.id.get_shareData);
        save_key = findViewById(R.id.store_key);
        save_value = findViewById(R.id.store_value);
        find_key = findViewById(R.id.SharePre_key);
        find_value = findViewById(R.id.sharePre_data);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = save_key.getText().toString();
                String value = save_value.getText().toString();
                if(!TextUtils.isEmpty(key)&&!TextUtils.isEmpty(value)){
                    SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putString(key,value);
                    editor.apply();
                    Toast.makeText(SharePreferenceActivity.this,key+":"+value,Toast.LENGTH_SHORT).show();
                }
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retrieve_key = find_key.getText().toString();
                if(!TextUtils.isEmpty(retrieve_key)){
                    SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                    String result = preferences.getString(retrieve_key,"null");
                    find_value.setText(result);

                }
            }
        });
    }
}
