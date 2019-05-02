package com.example.user.cloneheroclone2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class CHView extends SurfaceView implements Runnable
{
    private int screenWidth;
    private int screenHeight;
    private Context context;
    private SurfaceHolder myHolder;
    private Paint paint;
    private Thread thread;
    private Canvas canvas;
    private boolean isRunning;
    private int interval = 0;
    private Song currentSong;
    private int counter;
    private ArrayList<Gem> leftList;
    private ArrayList<Gem> midList;
    private ArrayList<Gem> rightList;
    private Gem tmpGem;
    float x;
    float y;
    private Bitmap bg;
    private boolean touch = false;
    float diffX;
    float diffY;
    Context c;
    Bitmap bmp;
    private int score;
    private int streak;
    private int multiplier;
    private String dif;

    public CHView(Context context, int width, int height, String songName, String dif)
    {
        super(context);
        this.c = context;
        this.screenHeight = height;
        this.screenWidth = width;
        this.context = context;
        myHolder = getHolder();
        paint = new Paint();
        thread = new Thread(this);
        paint.setColor(Color.RED);
        this.score = 0;
        this.dif = dif;

        isRunning = true;

        currentSong = new Song(30, context, height, songName, this.dif);
        this.leftList = this.currentSong.getList();
        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.almostfinalhighway);

        thread.start();




    }
    public void setIsRunning()
    {
        this.isRunning=false;
    }


    public void draw() {
        if (isRunning) {
            if (myHolder.getSurface().isValid()) {
                canvas = myHolder.lockCanvas();
                canvas.drawBitmap(Bitmap.createScaledBitmap(bmp, canvas.getWidth(), canvas.getHeight(), false ), canvas.getMatrix(), paint);
                for (Gem gem : currentSong.getList())
                    gem.draw(canvas, paint);

                counter++;
                myHolder.unlockCanvasAndPost(canvas);

            }
        }
    }


    public int getSongLength()
    {
        return this.currentSong.getSongLength();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float xa = event.getX();
                float ya = event.getY();
                this.touch = true;
                getXY(xa, ya);
            }
        return true;
    }



    @Override
    public void run() {

        while(isRunning) {
            draw();
            for (Gem gem: currentSong.getList())
            {
                tmpGem = gem;
                tmpGem.move(0);
                if(this.touch)
                {
                    checkTouchGem(tmpGem);

                }
            }


            synchronized (this) {
                try {
                    wait(1);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }



    }

    public void pause()
    {
        isRunning = false;
    }

    public void unPause()
    {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public int getScore()
    {
        return this.score;
    }



   public void checkTouchGem(Gem gem)
   {
       diffX = this.x - gem.getX();
       diffY = this.y - gem.getY();

       if( (-180< diffX && diffX<180) &&( diffY < 180 && diffY > -180))
       {
           if (this.y > (screenHeight - 300) && this.y < (screenHeight - 100))
           {
               this.streak++;
               if (this.streak<2)
               {
                   this.multiplier = 1;
               }
               else if(this.streak < 4)
               {
                   this.multiplier = 2;
               }
               else if (this.streak < 7)
               {
                   this.multiplier = 3;
               }
               else
               {
                   this.multiplier = 4;
               }
               this.score = this.score + (20*this.multiplier);
               gem.move(1);
               this.touch = false;
           }
           else
           {
               this.streak = 0;
               this.multiplier = 1;
           }
       }
   }

    public void getXY(float x, float y)
    {
        this.x = x ;
        this.y = y ;

    }


}
