package com.example.chenkeyu.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button)findViewById(R.id.create_database);
        Button addData = (Button)findViewById(R.id.add_data);
        Button queryData = (Button)findViewById(R.id.query_data);

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(559);
                book.setPrice(75);
                book.setPress("人民邮电出版社");
                book.save();
                Book book1 = new Book();
                book1.setName("Java");
                book1.setAuthor("");
                book1.setPages(859);
                book1.setPrice(95);
                book1.setPress("机械工业出版社");
                book1.save();
            }
        });

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for(Book book : books){
                    Log.d("MainActivity","book id is "+ book.getId());
                    Log.d("MainActivity","book name is "+ book.getName());
                    Log.d("MainActivity","book author is "+ book.getAuthor());
                    Log.d("MainActivity","book price is "+ book.getPrice());
                    Log.d("MainActivity","book pages is "+ book.getPages());
                    Log.d("MainActivity","book press is "+ book.getPress());
                }
            }
        });
    }
}
