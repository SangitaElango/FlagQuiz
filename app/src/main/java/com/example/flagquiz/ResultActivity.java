package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewCorrect,textViewWrong,textViewSuccess;
    private Button buttonPlay,buttonExit;
    Intent intent;
    private int correct;
    private int wrong;
    private String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewCorrect=findViewById(R.id.textViewResultCorrect);
        textViewWrong=findViewById(R.id.textViewResultWrong);
        textViewSuccess=findViewById(R.id.textViewSuccess);
        buttonPlay=findViewById(R.id.buttonPlayAgain);
        buttonExit=findViewById(R.id.buttonQuit);

        intent=getIntent();
        correct=intent.getIntExtra("correct",0);
        wrong=intent.getIntExtra("wrong",0);

        textViewCorrect.setText("Total Correct Answers: "+correct);
        textViewWrong.setText("Total Wrong Answers: "+wrong);
        successRate();
        textViewSuccess.setText("Success Rate: "+success);

        buttonPlay.setOnClickListener(view -> {
            intent=new Intent(ResultActivity.this,QuizActivity.class);
            startActivity(intent);
        });
        buttonExit.setOnClickListener(view -> {
            finish();
        });
    }

    public void successRate(){
        int successPercent=(correct%10)*10;
        String tempSuccessPercent=String.valueOf(successPercent);
        success=tempSuccessPercent+"%";
    }
}