package com.example.zuhal.hw1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {
    Button vocabulary;
    Button quiz;
    Button addWords;
    TextView user;
    String getUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);
        linearLayout.getBackground().setAlpha(80);
        vocabulary = (Button) findViewById(R.id.learn);
        quiz = (Button) findViewById(R.id.quiz);
        addWords=(Button)findViewById(R.id.addWords);
        user=(TextView)findViewById(R.id.users);
        user.getBackground().setAlpha(95);
        Bundle bd = getIntent().getExtras();
        getUser = (String) bd.get("user");

        user.setText("Let the game begin "+getUser+ ":)");
        vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this, Vocabulary.class);
                startActivity(i);

            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this, Quizzes.class);
                startActivity(i);

            }
        });
        addWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainPage.this,AddWords.class);
                startActivity(i);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                getUser="";
                SharedPreferences sharedPreferences = this.getSharedPreferences("userName", MODE_PRIVATE);
                SharedPreferences.Editor edit;
                edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();
                Intent i=new Intent(MainPage.this,MainActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
