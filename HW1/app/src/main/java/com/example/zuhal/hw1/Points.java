package com.example.zuhal.hw1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Points extends AppCompatActivity {
LinearLayout linearLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        linearLayout1=(LinearLayout)findViewById(R.id.linear2);
        linearLayout1.getBackground().setAlpha(75);

        TextView textResult = (TextView) findViewById(R.id.textResult);

        Bundle b = getIntent().getExtras();

        double score = b.getDouble("score");
if(score<0){
    AlertDialog.Builder builderInner = new AlertDialog.Builder(
            Points.this);
    builderInner.setMessage("Oops,you failed this quiz,your point is "+score+" and you should return the training part !!");
    builderInner.setIcon(R.drawable.q);
    builderInner.setTitle("Your Points");
    builderInner.setPositiveButton("OK",
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(
                        DialogInterface dialog,
                        int which) {
                    dialog.dismiss();
                    Intent i=new Intent(Points.this,Vocabulary.class);
                    startActivity(i);
                    finish();
                }
            });
    builderInner.show();
}
    else{
    textResult.setText("Your score is " + " " + score + ". Congratulations for your success :)");
    }

    }

    public void playagain(View o) {

        Intent intent = new Intent(this, Quizzes.class);

        startActivity(intent);


    }
    public void homepage(View view){
        Intent i=new Intent(Points.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}

