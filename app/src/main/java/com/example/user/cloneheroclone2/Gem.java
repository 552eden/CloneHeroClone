package com.example.user.cloneheroclone2;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by User on 03/12/2018.
 */

public class Gem {


    private int x;
    private int y;
    private int h;
    private Bitmap img;
    private Bitmap fire;
    private Context c;


    public Gem(String color, int x, int y, int h, Context context) {
        this.h = h;
        this.c = context;
        int width= context.getResources().getDisplayMetrics().widthPixels;
        //width = width - 20;
        int height= context.getResources().getDisplayMetrics().heightPixels;
        fire =   Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.c.getResources(), R.drawable.fire2), 250,
                250, false);

        if (color.equals("green")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.green), 250,
                    250, false);
            this.x = (width/5) ;

        } else if (color.equals("red")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.red), 250,
                    250, false);
            this.x = (width / 5) * 2 ;
        } else if (color.equals("yellow")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow), 250,
                    250, false);
            this.x = (width / 6) * 4;
        }

        //this.x = x;
        this.y = y;

    }
    public void explode()
    {
        this.img = fire;

    }

    public synchronized void draw(Canvas canvas, Paint paint) {

        if (y <= h)
            canvas.drawBitmap(this.img, this.x, this.y, paint);


    }

    public Bitmap getColor() {
        return this.img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }



    public void move(int i) {

        if(i==1) {
            //The + X dictates how fast it moves
            this.y = (y + 15);
            explode();
        }
        else
            this.y = (y + 15);




    }




}
