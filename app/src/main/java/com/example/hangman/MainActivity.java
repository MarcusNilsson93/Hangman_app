package com.example.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_new_game:
                newGame();
                //new game
                return true;

            case R.id.action_info:
                onClick();
                //Show info
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick() {
        //Start About activity
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    public void newGame() {
        //Starts a new game
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    public void changeLanguage(View view) {
        //show alert dialog to display languages
        showChangeLanguageDialog();
    }

    private void showChangeLanguageDialog() {
        //Array of languages to display in dialog
        final String[]listOfLang = {"English","Svenska"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listOfLang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //En(default)
                    setLocale("");
                    recreate();
                }
                else if(which == 1){
                    //Sv
                    setLocale("sv");
                    recreate();
                }
                //Dismiss dialog when language is selected
                dialog.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        //Save data
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    //Load language saved in sharedPref
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }
}
