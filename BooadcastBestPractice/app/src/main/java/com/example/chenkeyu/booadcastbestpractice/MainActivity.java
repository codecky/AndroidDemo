package com.example.chenkeyu.booadcastbestpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        Button forceOffline = (Button)findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.example.chenkeyu.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity","onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity","onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity","onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity","onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity","onDestroy");
    }

}
