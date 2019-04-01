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
    private int velY;
    private Bitmap img;
    private Bitmap fire;
    private String color;
    private Context c;

    public Gem(String color, int x, int y, int h, Context context) {
        this.h = h;
        this.c = context;
        fire =   Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.c.getResources(), R.drawable.fire2), 250,
                250, false);

        if (color.equals("green")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.green), 250,
                    250, false);

        } else if (color.equals("red")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.red), 250,
                    250, false);
        } else if (color.equals("yellow")) {
            this.img = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow), 250,
                    250, false);
        }
        this.x = x;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int i) {
        //dictates how fast it moves
        if(i==1) {
            this.y = (y + 15);
            explode();
        }
        else
            this.y = (y + 15);




    }




}
