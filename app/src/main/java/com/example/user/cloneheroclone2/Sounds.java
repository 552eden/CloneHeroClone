package com.example.user.cloneheroclone2;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.widget.Toast;

public class Sounds {

    private static Context context;
    private static int winId;
    private static int loseId;
    private static int masterOfPuppets;
    private static float volume;
    private static SoundPool soundpool;
    private static boolean isFirst = false;

    public static void setSound(Context c)
    {
        context = c;
        volume = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            soundpool = new SoundPool.Builder().setMaxStreams(1).build();
        }
        else
        {
            soundpool = new SoundPool(3, AudioManager.STREAM_MUSIC, 1);
        }
        winId = soundpool.load(context, R.raw.win, 1);
        loseId = soundpool.load(context, R.raw.lose, 1);
        masterOfPuppets = soundpool.load(context, R.raw.masterofpuppets, 1);

    }

    public static void playWinSound()
    {
        soundpool.play(winId, volume, volume, 1, 0, 1);
    }
    public static void playLoseSound()
    {
        soundpool.play(loseId, volume, volume, 1, 0, 1);
    }
    public static void playSong(int songNum) {soundpool.play(masterOfPuppets, volume, volume, 1, 0, 1); }

    //TODO: change song to match all songs


}
