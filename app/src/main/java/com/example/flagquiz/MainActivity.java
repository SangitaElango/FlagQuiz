package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textViewWelcome;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWelcome=findViewById(R.id.textViewWelcome);
        buttonStart=findViewById(R.id.buttonStart);

        createDatabase();

        buttonStart.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,QuizActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void createDatabase(){
        try {
            DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(MainActivity.this);
            databaseCopyHelper.createDataBase();
            databaseCopyHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}