package com.example.zuhal.hw1;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddWords extends AppCompatActivity {
    LinearLayout linearLayout1;
    EditText english1;
    EditText turkish1;
    Button add1;
    public static ArrayList<String >englishadded;
    public static ArrayList<String>turkishadded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        linearLayout1=(LinearLayout)findViewById(R.id.linear2);
        linearLayout1.getBackground().setAlpha(65);
        english1=(EditText)findViewById(R.id.engWords);
        turkish1=(EditText)findViewById(R.id.turWords);
        add1=(Button)findViewById(R.id.addWord);


        String MY_FILE_NAME = "mytextfile.txt";
// Create a new output file stream
        try {
            FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// Create a new file input stream.
        try {
            FileInputStream fileis = openFileInput(MY_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToSDFile();
                readSDcard();
                // writeToFile();
            }
        });
    }
    public AddWords(){
        englishadded=new ArrayList<String>();
        turkishadded=new ArrayList<String >();
        readSDcard();

    }
    private void writeToSDFile() {

        File root = android.os.Environment.getExternalStorageDirectory();

        File dir = new File(root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "myDictionary1.txt");
        if (englishadded.contains(english1.getText().toString())) {
            Toast.makeText(getApplicationContext(), english1.getText().toString() + " is already exist", Toast.LENGTH_SHORT).show();
        } else {
            try {
                FileOutputStream f = new FileOutputStream(file, true);
                PrintWriter pw = new PrintWriter(f);
                pw.println(english1.getText().toString() + " " + turkish1.getText().toString());
                pw.flush();
                pw.close();
                f.close();
                Toast.makeText(getApplicationContext(), english1.getText().toString() + "added succesfully", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i("uyarı", "******* File not found. Did you" +
                        " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "\n\nFile written to ", Toast.LENGTH_SHORT).show();
        }
    }
    public void readSDcard(){
        File sdcard = Environment.getExternalStorageDirectory();

//Get the text file
        File dir = new File (sdcard.getAbsolutePath() + "/download");
        dir.mkdir();
        File file = new File(dir,"myDictionary1.txt");

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                String[]lines=line.split(" ");
                englishadded.add(lines[0]);
                turkishadded.add(lines[1]);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
    }

}
