package com.example.akasztofa1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    boolean musicFlag = true;
    String language = Locale.getDefault().getLanguage();
    private boolean mIsBound = false;
    private MusicService mServ;
    HomeWatcher mHomeWatcher;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        if (getIntent().getExtras() != null){

        }
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

    public void createGame(View view) {
        Intent i = new Intent();
        i.setClass(this,GameActivity.class);
        i.putExtra("sound",musicFlag);
        i.putExtra("language",language);
        startActivity(i);
        finish();
    }
    

    public void createSettings(View view) {
        Intent i = new Intent();
        i.setClass(this,SettingActivity.class);
        i.putExtra("sound",musicFlag);
        i.putExtra("language",language);
        startActivity(i);
        finish();
    }

    void doBindService(){
        bindService(new Intent(this,MusicService.class),Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService(){
        if(mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }


    public void exitGame(View view) {
        finish();
        System.exit(1);
    }
}

