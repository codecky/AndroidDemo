package com.example.chenkeyu.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        final Button createDatebase = (Button)findViewById(R.id.create_database);
        Button addData = (Button)findViewById(R.id.add_data);
        Button queryData = (Button)findViewById(R.id.query_data);
        Button updateData = (Button)findViewById(R.id.update_data);
        Button deleteDate = (Button)findViewById(R.id.delete_data);

        createDatebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        //向Book表中添加数据
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)",new String[]{"哈哈哈","Daniel","666","20"});
                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)",new String[]{"啦啦啦","","24","180"});

                ContentValues values = new ContentValues();
                values.put("name","军师联盟");
                values.put("author","吴秀波");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();

                values.put("name","我爱伐伐");
                values.put("author","张昕宇");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
            }
        });

        //删除Book表中的数据
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //String sql="delete from Book where id in (select id from Book group by name having count(name)>1) and id not in (select min(id) from Book group by name having count(name)>1)";
                db.execSQL("delete from Book where id in (select id from Book group by name having count(name)>1) and id not in (select min(id) from Book group by name having count(name)>?)",new String[]{"1"});
                db.close();
            }
        });



        //查询Book表中的所有数据
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from Book ",null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","Book id is "+id);
                        Log.d("MainActivity","Book name is "+name);
                        Log.d("MainActivity","Book author is "+author);
                        Log.d("MainActivity","Book pages is "+pages);
                        Log.d("MainActivity","Book price is "+price);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
