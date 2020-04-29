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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    String language = Locale.getDefault().getLanguage();

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

    RadioGroup rg;
    RadioButton yesbutton;
    RadioButton nobutton;
    boolean originalMusicFlag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        yesbutton = (RadioButton) findViewById(R.id.yesButton);
        nobutton = (RadioButton) findViewById(R.id.noButton);
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        if (getIntent().getExtras() != null){
            originalMusicFlag = getIntent().getExtras().getBoolean("sound");
            language = getIntent().getExtras().getString("language");
            if(originalMusicFlag == true){
                startService(music);
                yesbutton.setChecked(true);
            }
            else{
                nobutton.setChecked(true);
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

        rg = (RadioGroup) findViewById(R.id.musicFlag);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yesButton){
                    mServ.resumeMusic();
                    originalMusicFlag = true;
                }
                if (checkedId == R.id.noButton){
                    mServ.pauseMusic();
                    originalMusicFlag = false;
                }
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


    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService(){
        if(mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    public void savePressed(View view) {
        Intent i = new Intent();
        i.setClass(this,MainActivity.class);
        i.putExtra("sound",originalMusicFlag);
        i.putExtra("language", language);
        startActivity(i);
        finish();
    }

    public void setHunLang(View view) {
        language = "hu";
        Toast.makeText(this,"Magyar kiválasztva",Toast.LENGTH_SHORT).show();
    }

    public void setEngLang(View view) {
        language = "en";
        Toast.makeText(this,"English registered",Toast.LENGTH_SHORT).show();
    }
}
