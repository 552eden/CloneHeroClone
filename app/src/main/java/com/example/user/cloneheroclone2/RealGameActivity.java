package com.example.user.cloneheroclone2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RealGameActivity extends AppCompatActivity  {

    private static int musicStopper;
    FrameLayout frm;
    static CHView chView;
    TextView TVT;
    TextView TVS;
    Thread thread;
    private String name;
    private Score Sscore;
    private String difficulty;
    private String songName;
    private CallReciever reciever;
    private DbHelper scoreDataBase;
    private Intent music;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_game);
        frm = (FrameLayout) findViewById(R.id.frameCH);
        TVT = (TextView)findViewById(R.id.timerTV);
        TVS = (TextView) findViewById(R.id.scoreTV);
        Intent intent = getIntent();
        this.name = intent.getExtras().getString("FirstName");
        this.difficulty = intent.getExtras().getString("Difficulty");
        this.songName = intent.getExtras().getString("SongName");
        thread = new Thread(runnable);
        thread.start();
        //Sounds.setSound(this);
        reciever = new CallReciever();
        registerReceiver(reciever, new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));
        scoreDataBase = new DbHelper(this);
        musicStopper = 0;

    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus)
            return;

        int w = frm.getWidth();
        int h = frm.getHeight();
        chView = new CHView(this, h, this.songName, this.difficulty);
        frm.addView(chView);
        music = new Intent(this, MusicService.class);
        music.putExtra("songName", this.songName);
        startService(music);


    }






    boolean isRunning = true;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(musicStopper == 1)
            {
                stopMusic();
            }
            int t = msg.arg1;
            TVT.setText(String.format("%02d", t/60)+":" + String.format("%02d", t%60) );
            if (t%60 == chView.getSongLength())
            {
                goBack();
            }
            TVS.setText(String.valueOf(chView.getScore()));
        }
    };

    public void goBack()
    {
        stopService(music);
        unregisterReceiver(reciever);
        Sscore = new Score(String.valueOf(chView.getScore()), this.songName, this.name, this.difficulty);
        scoreDataBase.createScore(Sscore);
        chView.setIsRunning();
        isRunning = false;

        Intent i = new Intent(this, ScoreActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void pause(View v)
    {
        if(chView.getIsRunning())
        {
            musicStopper = 1;
            chView.pause();
        }
        else
        {
            musicStopper = 0;
            chView.unPause();
        }
    }

    public static void pause2()
    {
        if(chView.getIsRunning())
        {
            musicStopper = 1;
            chView.pause();
        }
        else
        {
            musicStopper = 0;

            chView.unPause();
        }
    }

    private void stopMusic()
    {
        stopService(music);
    }


    private int count = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (isRunning)
            {
                synchronized (this) {
                    try {
                        wait(1000);

                        count++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Message myMsg = handler.obtainMessage();
                myMsg.arg1 = count;
                handler.sendMessage(myMsg);

            }
            if(!chView.getIsRunning())
            {
                finish();
            }
        }
    };



}
