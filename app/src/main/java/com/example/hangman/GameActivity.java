package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public int counter = 0;
    protected int attempts = 10;
    private ArrayList<Character> myGussedWord = new ArrayList<>();
    private String keyWord;
    private ArrayList<Character>wrongCharsGuess = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

            initializeGame();

    }
    private void initializeGame() {


        String[] words = getResources().getStringArray(R.array.words_to_guess);
        Random rnd = new Random();

        keyWord = words[rnd.nextInt(words.length)];
        for(int i = 0; i<keyWord.length();i++){
            myGussedWord.add('-');
        }

    }
    public void guess(View view) {


        EditText playerGuess = findViewById(R.id.edit_guess);

        ImageView im = findViewById(R.id.image);

        String guess = playerGuess.getText().toString();
        guess = guess.toUpperCase();

        char myGuess = guess.charAt(0);

        String wor = keyWord;

        int[] pictures = {
                R.drawable.hangman_0,
                R.drawable.hangman_1,
                R.drawable.hangman_2,
                R.drawable.hangman_3,
                R.drawable.hangman_4,
                R.drawable.hangman_5,
                R.drawable.hangman_6,
                R.drawable.hangman_7,
                R.drawable.hangman_8,
                R.drawable.hangman_9,
                R.drawable.hangman_10

        };

        ArrayList<Character>charsWord = new ArrayList<>();

        //puts player input in char array
        for(char ch: wor.toCharArray()) {
            charsWord.add(ch);

        }
        //handles stuff if char don´t match and player can´t guess on the same char twise
        if(!charsWord.contains(myGuess)) {
            if(!wrongCharsGuess.contains(myGuess)){

            wrongCharsGuess.add(myGuess);

            attempts --;
            counter ++;
            TextView attemptsL = findViewById(R.id.object_tries_left);
            String aL = Integer.toString(attempts);
            im.setImageResource(pictures[counter]);
            attemptsL.setText(aL);

            } else {
                Toast.makeText(getApplicationContext(),R.string.toast
                        ,Toast.LENGTH_SHORT).show();

            }
            if(counter == 10|| attempts == 0){
                // result activity(lose)
                result(1);
            }

        }else if(charsWord.contains(myGuess)){
                revialLetterInWord(myGuess);
        }

        displayText();
    }

    private void revialLetterInWord(char letter){

        for(int i = 0; i< myGussedWord.size();i++){
            if(letter == keyWord.charAt(i)){
                myGussedWord.remove(i);
                myGussedWord.add(i,letter);
                System.out.println("letter added to rightguess");
            }

        }

        displayText();

    }
    //displays text
    private void displayText(){
        String word ="";
        String wChars ="";


        for(int i = 0; i< myGussedWord.size();i++){
            word += myGussedWord.get(i);
            System.out.println("sets right chars");
        }
        TextView finalText =findViewById(R.id.final_text);
        finalText.setText(word);

        for(int i = 0; i< wrongCharsGuess.size();i++){
            wChars += wrongCharsGuess.get(i);

        }
        TextView wrongChars = findViewById(R.id.display_guessed_chars);
        wrongChars.setText(wChars);

        //result activity win
        if(word.equals(keyWord)){
            result(2);
        }
    }

    private void result(int res){
        //runs when player looses
        if(res == 1){
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("RES",1);
            intent.putExtra("ATTEMPTS",attempts);
            intent.putExtra("KEY_WORD",keyWord);
            startActivity(intent);
        }
        //Runs when player got the right word
        else if(res == 2){
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("RES",2);
            intent.putExtra("ATTEMPTS",attempts);
            intent.putExtra("KEY_WORD",keyWord);
            startActivity(intent);
        }

    }

}
