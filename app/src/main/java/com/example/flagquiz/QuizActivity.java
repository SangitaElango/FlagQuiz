package com.example.flagquiz;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewWrong,textViewCorrect,textViewQuestion;
    private Button buttonOption1,buttonOption2,buttonOption3,buttonOption4;
    private ImageView imageViewFlag,imageViewNext;


    private FlagDatabaseHelper flagDatabaseHelper;
    private ArrayList<FlagData> listFlagData=new ArrayList<>();
    private ArrayList<FlagData> listFlagDataChoice=new ArrayList<>();
    private FlagDataGenerator flagDataGenerator=new FlagDataGenerator();
    private FlagData flagData;
    private HashSet<FlagData> mixChoice=new HashSet<>();
    private ArrayList<FlagData> flagChoice=new ArrayList<>();
    private int question=0;
    private int wrong=0;
    private int correct=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewWrong=findViewById(R.id.textViewWrong);
        textViewCorrect=findViewById(R.id.textViewCorrect);
        textViewQuestion=findViewById(R.id.textViewQuestion);
        buttonOption1=findViewById(R.id.buttonOption1);
        buttonOption2=findViewById(R.id.buttonOption2);
        buttonOption3=findViewById(R.id.buttonOption3);
        buttonOption4=findViewById(R.id.buttonOption4);
        imageViewFlag=findViewById(R.id.imageViewFlag);
        imageViewNext=findViewById(R.id.imageViewNext);



        flagDatabaseHelper=new FlagDatabaseHelper(QuizActivity.this);
        listFlagData=flagDataGenerator.generateFlagData(flagDatabaseHelper);
        loadQuestion();

        buttonOption1.setOnClickListener(view -> {
            if(flagData.getFlag_name().equals(flagChoice.get(0).getFlag_name())){
                buttonOption1.setBackgroundColor(Color.GREEN);
                correct++;
            }
            else{
                buttonOption1.setBackgroundColor(Color.RED);
                checkAnswer();
                wrong++;
            }
        });
        buttonOption2.setOnClickListener(view -> {
            if(flagData.getFlag_name().equals(flagChoice.get(1).getFlag_name())){
                buttonOption2.setBackgroundColor(Color.GREEN);
                correct++;
            }
            else{
                buttonOption2.setBackgroundColor(Color.RED);
                checkAnswer();
                wrong++;
            }
        });
        buttonOption3.setOnClickListener(view -> {
            if(flagData.getFlag_name().equals(flagChoice.get(2).getFlag_name())){
                buttonOption3.setBackgroundColor(Color.GREEN);
                correct++;
            }
            else{
                buttonOption3.setBackgroundColor(Color.RED);
                checkAnswer();
                wrong++;
            }
        });
        buttonOption4.setOnClickListener(view -> {
            if(flagData.getFlag_name().equals(flagChoice.get(3).getFlag_name())){
                buttonOption4.setBackgroundColor(Color.GREEN);
                correct++;
            }
            else{
                buttonOption4.setBackgroundColor(Color.RED);
                checkAnswer();
                wrong++;
            }
        });
        imageViewNext.setOnClickListener(view -> {
            loadQuestion();
        });

    }

    public void loadQuestion(){
        if(question<=9) {
            textViewWrong.setText("Wrong: " + wrong);
            textViewCorrect.setText("Correct " + correct);
            flagData = listFlagData.get(question);
            textViewQuestion.setText("Question: " + ((question++)+1));
            imageViewFlag.setImageResource(getResources().getIdentifier(flagData.getFlag_img(), "drawable", getPackageName()));

            setButtonBackgroundToOriginal();
            listFlagDataChoice = flagDataGenerator.generateFlagDataChoices(flagDatabaseHelper, flagData.getFlag_id());


            mixChoice.clear();
            mixChoice.add(flagData);
            mixChoice.add(listFlagDataChoice.get(0));
            mixChoice.add(listFlagDataChoice.get(1));
            mixChoice.add(listFlagDataChoice.get(2));

            flagChoice.clear();
            for (FlagData flg : mixChoice) {
                flagChoice.add(flg);
            }

            buttonOption1.setText(flagChoice.get(0).getFlag_name());
            buttonOption2.setText(flagChoice.get(1).getFlag_name());
            buttonOption3.setText(flagChoice.get(2).getFlag_name());
            buttonOption4.setText(flagChoice.get(3).getFlag_name());
        }
        else{
            Intent intent=new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("correct",correct);
            intent.putExtra("wrong",wrong);
            startActivity(intent);
            finish();
        }

    }

    public void checkAnswer(){
        if(flagData.getFlag_name().equals(flagChoice.get(0).getFlag_name())){
            buttonOption1.setBackgroundColor(Color.GREEN);
        }
        else if(flagData.getFlag_name().equals(flagChoice.get(1).getFlag_name())){
            buttonOption2.setBackgroundColor(Color.GREEN);
        }
        else if(flagData.getFlag_name().equals(flagChoice.get(2).getFlag_name())){
            buttonOption3.setBackgroundColor(Color.GREEN);
        }
        else if(flagData.getFlag_name().equals(flagChoice.get(3).getFlag_name())){
            buttonOption4.setBackgroundColor(Color.GREEN);
        }
    }

    public void setButtonBackgroundToOriginal(){
        buttonOption1.setBackgroundColor(getResources().getColor(R.color.dark_teal));
        buttonOption2.setBackgroundColor(getResources().getColor(R.color.dark_teal));
        buttonOption3.setBackgroundColor(getResources().getColor(R.color.dark_teal));
        buttonOption4.setBackgroundColor(getResources().getColor(R.color.dark_teal));
    }

}