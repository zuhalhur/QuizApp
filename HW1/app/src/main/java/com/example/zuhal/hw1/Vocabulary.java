package com.example.zuhal.hw1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary extends AppCompatActivity {
    LinearLayout linearLayout;
    TextView english;
    TextView turkish;
    Button next;
    final ArrayList<String> engWords = new ArrayList<String>();
    final ArrayList<String> turWords = new ArrayList<String>();
    final ArrayList<Integer> isSeen = new ArrayList<Integer>();
    Random random;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        linearLayout = (LinearLayout) findViewById(R.id.linear1);
        linearLayout.getBackground().setAlpha(75);
        english = (TextView) findViewById(R.id.engWord);
        turkish = (TextView) findViewById(R.id.turWord);
        next = (Button) findViewById(R.id.next);
        random = new Random(System.currentTimeMillis());

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.sozluk));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");
            engWords.add(words[0]);
            turWords.add(words[1]);

        }
        readFile();
        scan.close();
        btnClick();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick();
            }
        });

    }

    public void readFile() {
//        AddWords a = new AddWords();
        if(AddWords.englishadded != null){
            for(int i=0;i<AddWords.englishadded.size();i++){
                engWords.add(AddWords.englishadded.get(i));
                turWords.add(AddWords.turkishadded.get(i));
            }
        }

    }

    public Boolean isSeen(int i){
        for(int index=0;index<isSeen.size();index++){
            if(isSeen.get(index) == i){
                return true;
            }
        }
        return false;
    }

    public Boolean isEnd(){
        if(isSeen.size()>=engWords.size())
            return true;
        return false;
    }

    public void btnClick(){
        int index;
        if(!isEnd()) {
            do {
                index = random.nextInt(engWords.size());
            } while (isSeen(index));

            english.setText(engWords.get(index));
            turkish.setText(turWords.get(index));
            isSeen.add(index);
        }
        else {
            next.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builderInner = new AlertDialog.Builder(
                    Vocabulary.this);
            builderInner.setMessage("That's it,lets start the quiz !!");
            builderInner.setIcon(R.drawable.q);
            builderInner.setTitle("QUIZ TIME");
            builderInner.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(
                                DialogInterface dialog,
                                int which) {
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
            builderInner.show();
        }


    }
}
