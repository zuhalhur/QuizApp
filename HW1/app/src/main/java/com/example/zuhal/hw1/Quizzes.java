package com.example.zuhal.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class Quizzes extends AppCompatActivity {
    List<Question> quesList;
    double score = 0;
    int qid = 0;
    RelativeLayout relativeLayout;


    Question currentQ;
    TextView txtQuestion, scored;
    Button button1, button2, button3,home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        QuizHelper db = new QuizHelper(this);  // my question bank class
        quesList = db.getAllQuestions();  // this will fetch all quetonall questions
        currentQ = quesList.get(qid); // the current question

        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        relativeLayout.getBackground().setAlpha(40);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        // the textview in which the question will be displayed

        // the three buttons,
        // the idea is to set the text of three buttons with the options from question bank
        button1 = (Button) findViewById(R.id.radio0);
        button2 = (Button) findViewById(R.id.radio1);
        button3 = (Button) findViewById(R.id.radio2);
        home=(Button)findViewById(R.id.home);
        // the textview in which score will be displayed
        scored = (TextView) findViewById(R.id.scored);

        // the timer


        // method which will set the things up for our game
        setQuestionView();

        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)


        // button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                getAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString());
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {

            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score=score+1;
            scored.setText("Score : " + score);
        } else {

            // if unlucky start activity and finish the game
            score=score-0.5;
            scored.setText("Score : " + score);
            //Intent intent = new Intent(Quizzes.this,
            //        Points.class);
//
            //// passing the int value
            //Bundle b = new Bundle();
            //b.putDouble("score", score); // Your score
            //intent.putExtras(b); // Put your score to your next
            //startActivity(intent);
            //finish();
        }
        if (qid < 5) {

            // if questions are not over then do this
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {

            // if over do this
            Intent intent = new Intent(Quizzes.this,
                    Points.class);
            Bundle b = new Bundle();
            b.putDouble("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }


    }



    private void setQuestionView() {

        // the method which will put all things together
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

        qid++;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

