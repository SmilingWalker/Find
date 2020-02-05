package com.example.find;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends AppCompatActivity {
    Button Makecall;
    Button contacts;
    ListView contact_list;
    List<String> stringList = new ArrayList<>();
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        contacts = findViewById(R.id.get_contacts);
        Makecall = findViewById(R.id.call);
        contact_list = findViewById(R.id.contacts);
        adapter = new ArrayAdapter<String>(this,R.layout.simple_list,stringList);
        contact_list.setAdapter(adapter);
        Makecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PermissionActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    call();
                }
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(PermissionActivity.this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PermissionActivity.this,new String[]{Manifest.permission.READ_CONTACTS},2);
                }
                else {
                    GetContacts();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call();
                }
                else {
                    Toast.makeText(PermissionActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    GetContacts();
                }
                else {
                    Toast.makeText(PermissionActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:1008611"));
            startActivity(intent);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
    private void GetContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    stringList.add(name+"\n"+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(cursor!=null)
                cursor.close();
        }
    }
}
