package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private int attemptsLeft;
    private String keyWord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        int res = intent.getIntExtra("RES",0);
        attemptsLeft = intent.getIntExtra("ATTEMPTS",0);
        keyWord = intent.getStringExtra("KEY_WORD");
        System.out.println(res);



        result(res);

    }
    private void result(int res){
        TextView textView = findViewById(R.id.result_text);
        TextView attempts = findViewById(R.id.counter_trys);
        TextView key_Word = findViewById(R.id.key_word);
        String attemptsToString = String.valueOf(attemptsLeft);


        System.out.println(attemptsLeft);

        if(res == 1){
            textView.setText(R.string.lost);
            attempts.setText(" "+attemptsToString);
            key_Word.setText(" "+keyWord);
        }
        else if(res == 2){
            textView.setText(R.string.won);
            attempts.setText(" "+attemptsToString);
            key_Word.setText(" "+keyWord);
        }

    }

    public void playAgain(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
    public void mainMenu(View view){
        Intent intent = new Intent(this,MainActivity.class);
       //aktivitet förstör act i backstack fram till 1a intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
