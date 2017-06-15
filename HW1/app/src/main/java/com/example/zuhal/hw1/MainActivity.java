package com.example.zuhal.hw1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button sign;
    private EditText username;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getSharedPreferences("userName", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user=sharedPreferences.getString("user","");

        if(user.equals("")){
            setContentView(R.layout.activity_main);
            username=(EditText)findViewById(R.id.username);
            sign=(Button)findViewById(R.id.signIn);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (username.getText().toString().trim().equals("")||username.getText().toString().trim().length()>8) {
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                MainActivity.this);
                        builderInner.setMessage("Please enter valid username !!!");
                        builderInner.setTitle("HATA");
                        builderInner.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builderInner.show();

                    } else {
                        user=username.getText().toString();
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        else{
            setContentView(R.layout.activity_main_page);
            Intent intent = new Intent(MainActivity.this, MainPage.class);
            intent.putExtra("user",user);
            startActivity(intent);
            finish();
        }





    }
    protected void onStart() {
        super.onStart();
        user=sharedPreferences.getString("user","");
    }
    protected void onStop() {
        super.onStop();
        editor.putString("user",user);
        editor.commit();
    }
}