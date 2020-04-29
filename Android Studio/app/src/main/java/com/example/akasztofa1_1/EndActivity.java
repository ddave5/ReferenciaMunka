package com.example.akasztofa1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class EndActivity extends AppCompatActivity {

    boolean musicFlag = true;
    String language = Locale.getDefault().getLanguage();
    boolean isWin = true;
    String word = "";

    TextView lastText;
    TextView correctWord;
    TextView goodanswer;

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

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    void doUnbindService() {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        lastText = findViewById(R.id.lastTextView);
        goodanswer = findViewById(R.id.endwordTextview);
        correctWord = findViewById(R.id.correctWord);
        if (getIntent().getExtras() != null) {
            musicFlag = getIntent().getExtras().getBoolean("sound");
            language = getIntent().getExtras().getString("language");
            isWin = getIntent().getExtras().getBoolean("win");
            word = getIntent().getExtras().getString("word");
        }

        if (isWin == true){
            lastText.setText(R.string.youwon);
            correctWord.setText("");
        }
        else{
            lastText.setText(R.string.youlose);
            correctWord.setText(R.string.goodAnswer);
            goodanswer.setText(word);
        }

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

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
    }

    public void backToMain(View view) {
        Intent i = new Intent();
        i.setClass(this,MainActivity.class);
        i.putExtra("sound",musicFlag);
        i.putExtra("language",language);
        startActivity(i);
        finish();
    }

    public void restartGame(View view) {
        Intent i = new Intent();
        i.setClass(this,GameActivity.class);
        i.putExtra("sound",musicFlag);
        i.putExtra("language",language);
        startActivity(i);
        finish();
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
}
