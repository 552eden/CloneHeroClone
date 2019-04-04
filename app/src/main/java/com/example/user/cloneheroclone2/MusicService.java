package com.example.user.cloneheroclone2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.content.Intent;

public class MusicService extends Service {

    String songName;
    MediaPlayer player;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        songName=(String) intent.getExtras().get("songName");
        if(songName.equals("Master Of Puppets - Metallica"))
        {
            player = MediaPlayer.create(this, R.raw.masterofpuppets);
        }
        else if(songName.equals("Symphony Of Destruction - Megadeth"))
        {
            player = MediaPlayer.create(this, R.raw.symphony);
        }
        player.setLooping(false);
        player.setVolume(100, 100);
        player.start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy()
    {
        player.stop();
    }



    @Override
    public boolean stopService(Intent name) {
        player.pause();
        return super.stopService(name);
    }


    public MusicService() {
    }


}
