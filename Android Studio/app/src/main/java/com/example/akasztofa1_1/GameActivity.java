package com.example.akasztofa1_1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {

    TextView counterTextView;
    TextView wordTextView;
    TextView letterListTextView;
    EditText inputEditText;
    ImageView hangman;

    int counter = 8;
    String wordToBeGuessed = "";
    String wordDisplayedString = "";
    char[] wordDisplayedCharArray ;
    String triedLetter;
    ArrayList<String> myListOfWords;

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };
    HomeWatcher mHomeWatcher;
    boolean musicFlag = true;
    String language = Locale.getDefault().getLanguage();

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService(){
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myListOfWords = new ArrayList<>();
        counterTextView = (TextView) findViewById(R.id.counter);
        wordTextView = (TextView) findViewById(R.id.word);
        letterListTextView = (TextView) findViewById(R.id.letterList);
        inputEditText = (EditText) findViewById(R.id.inputLetter);
        hangman = (ImageView) findViewById(R.id.hangman);

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        if (getIntent().getExtras() != null){
            musicFlag = getIntent().getExtras().getBoolean("sound");
            language = getIntent().getExtras().getString("language");
            if(musicFlag == true){
                startService(music);
            }
        }
        else{
            startService(music);
        }
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();


        InputStream is = null;
        Scanner in = null;
        try {
            if (language.equals("hu")){
                is = getAssets().open("database_hu.txt");
            }
            else{
                is = getAssets().open("database_en.txt");
            }
            in = new Scanner(is);
            while(in.hasNext()){
                myListOfWords.add(in.nextLine());
            }
        } catch (IOException e) {
            Toast.makeText(this,e.getClass().getSimpleName() + ": "+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            if (in != null){
                in.close();
            }
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                Toast.makeText(this,e.getClass().getSimpleName() + ": "+ e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        initializeGame();
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    checkIfLetterIsInWord(s.charAt(0));
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
        mHomeWatcher.stopWatch();

    }
    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    private void initializeGame() {
        Collections.shuffle(myListOfWords);
        wordToBeGuessed = myListOfWords.get(0);
        wordDisplayedCharArray = wordToBeGuessed.toCharArray();

        for(int i = 0; i < wordDisplayedCharArray.length; i++){
            wordDisplayedCharArray[i] = '_';
        }

        wordDisplayedString = String.valueOf(wordDisplayedCharArray);
        displayWordOnScreen();
        triedLetter = " ";
        letterListTextView.setText(triedLetter);
        counterTextView.setText(Integer.toString(counter));
    }
    private void displayWordOnScreen() {
        String formattedString = "";
        for (char c : wordDisplayedCharArray){
            formattedString += c + " ";
        }
        wordTextView.setText(formattedString);
    }
    private void checkIfLetterIsInWord(char letter) {
        if (wordToBeGuessed.indexOf(letter) >= 0){
            if(wordDisplayedString.indexOf(letter) < 0){
                revealLetterInWord(letter);
                displayWordOnScreen();
                if(!wordDisplayedString.contains("_")){
                    Intent i = new Intent();
                    i.setClass(this,EndActivity.class);
                    i.putExtra("sound",musicFlag);
                    i.putExtra("language",language);
                    i.putExtra("win",true);
                    i.putExtra("word",wordToBeGuessed);
                    startActivity(i);
                    finish();
                }
            }
        }
        else if (wordToBeGuessed.indexOf(letter) < 0 && triedLetter.indexOf(letter) >=0 ){
            Toast.makeText(this,"You tried that letter", Toast.LENGTH_SHORT);
        }
        else{
            decreaseAndDisplayTriesLeft();

            if (counter == 0){
                Intent i = new Intent();
                i.setClass(this,EndActivity.class);
                i.putExtra("sound",musicFlag);
                i.putExtra("language",language);
                i.putExtra("win",false);
                i.putExtra("word",wordToBeGuessed);
                startActivity(i);
                finish();
            }
        }

        if(triedLetter.indexOf(letter) < 0 && wordToBeGuessed.indexOf(letter) < 0){
            triedLetter += letter + ", ";
            letterListTextView.setText(triedLetter);
        }

    }
    private void decreaseAndDisplayTriesLeft() {
        if(counter != 0){
            counter--;
            switch (counter){
                case 7: hangman.setImageResource(R.drawable.hang1); break;
                case 6: hangman.setImageResource(R.drawable.hang2); break;
                case 5: hangman.setImageResource(R.drawable.hang3); break;
                case 4: hangman.setImageResource(R.drawable.hang4); break;
                case 3: hangman.setImageResource(R.drawable.hang5); break;
                case 2: hangman.setImageResource(R.drawable.hang6); break;
                case 1: hangman.setImageResource(R.drawable.hang7); break;
                case 0: hangman.setImageResource(R.drawable.hang8); break;
            }

            counterTextView.setText(Integer.toString(counter));
        }
    }
    private void revealLetterInWord(char letter) {
        int indexOfLetter = wordToBeGuessed.indexOf(letter);

        while (indexOfLetter >= 0){
            wordDisplayedCharArray[indexOfLetter] = wordToBeGuessed.charAt(indexOfLetter);
            indexOfLetter = wordToBeGuessed.indexOf(letter,indexOfLetter + 1);
        }

        wordDisplayedString = String.valueOf(wordDisplayedCharArray);
    }

    public void gameBackToMain(View view) {
        Intent i = new Intent();
        i.setClass(this,MainActivity.class);
        i.putExtra("sound",musicFlag);
        i.putExtra("language",language);
        startActivity(i);
        finish();
    }
}
