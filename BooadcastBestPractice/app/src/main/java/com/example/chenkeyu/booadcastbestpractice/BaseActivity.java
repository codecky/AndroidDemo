package com.example.chenkeyu.booadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by chenkeyu on 2018/4/24.
 */

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d("BaseActivity","onCreate");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("BaseActivity","onResume");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.chenkeyu.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("BaseActivity","onPause");
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("BaseActivity","onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("BaseActivity","onDestroy");
        ActivityCollector.removeActivity(this);
    }
    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context,Intent intent){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline . Please try to login again .");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent i = new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
            });
            builder.show();
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("BaseActivity","onStart");
    }
}
