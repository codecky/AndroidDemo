package com.example.chenkeyu.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button)findViewById(R.id.add_data);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.chenkeyu.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","静夜思");
                values.put("author","李白");
                values.put("pages",3);
                values.put("price",20);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryButton = (Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.chenkeyu.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","Book name is "+ name);
                        Log.d("MainActivity","Book author is "+ author);
                        Log.d("MainActivity","Book pages is "+ pages);
                        Log.d("MainActivity","Book price is "+ price);
                    }
                    cursor.close();
                }
            }
        });

        Button updateButton = (Button)findViewById(R.id.update_data);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.chenkeyu.databasetest.provider/book/"+ newId);
                ContentValues values = new ContentValues();
                values.put("author","李荣浩");
                values.put("price",30);
                getContentResolver().update(uri,values,null,null);
            }
        });

        Button deleteButton = (Button)findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri =  Uri.parse("content://com.example.chenkeyu.databasetest.provider/book/"+ newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
