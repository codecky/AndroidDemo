package com.example.chenkeyu.booadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by chenkeyu on 2018/4/24.
 */

public class LoginActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("LoginActivity","onCreate");
        setContentView(R.layout.activity_login);
        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            //将账号密码设置到文本框中
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("123456")){
                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("remember_password",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("LoginActivity","onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("LoginActivity","onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("LoginActivity","onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LoginActivity","onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("LoginActivity","onDestroy");
    }
}
