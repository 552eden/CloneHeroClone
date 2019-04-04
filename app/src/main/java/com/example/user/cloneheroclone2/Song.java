package com.example.user.cloneheroclone2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Song
{

    private int songLength;
    private Gem tempGem;
    private ArrayList<Gem> list;
    private ArrayList<Gem> gemList;
    private int counter;
    private  Context context;
    private String[] listS;
    private String song;
    private String difficulty;


    public Song(int songLength, Context context, int h, String song, String difficulty) {
        this.songLength = songLength;
        this.context = context;
        this.song = song;
        this.difficulty = difficulty;
        this.gemList = new ArrayList<Gem>();
        this.list = new ArrayList<Gem>();
        //listS = context.getResources().getStringArray(R.array.masterOfPuppets);
        if(song.equals("Master Of Puppets - Metallica"))
        {
            if(difficulty.equals("med"))
            {
                listS = context.getResources().getStringArray(R.array.masterOfPuppetsMED);
            }
            else if(difficulty.equals("hard"))
            {
                listS = context.getResources().getStringArray(R.array.masterOfPuppetsHARD);
            }
            else
            {
                listS = context.getResources().getStringArray(R.array.masterOfPuppets);
            }

        }
        else if(song.equals("Symphony Of Destruction - Megadeth"))
        {
            listS = context.getResources().getStringArray(R.array.symphonyOfDestruction);
        }
        String[] arr;
        int x;
        for (int i=0;i<listS.length;i++)
        {
            arr  = listS[i].split("-");
            x = Integer.parseInt(arr[1]);
            if(arr[0].equals("0")) {
                this.list.add(new Gem("green", 220, -x * 150, h, context));
            }            if(arr[0].equals("1")) {
                this.list.add(new Gem("red", 590, -x * 150, h, context));
            }
            if(arr[0].equals("2")) {
                this.list.add(new Gem("yellow", 960, -x * 150, h, context));
            }

        }

    }

   /* public void createLeftList()
    {
        //temp
        for (int i = 0; i<9999999; i++)
        {
            gemList.add(greenGem);
        }

    }*/

    public ArrayList<Gem> getGemList()
    {
        return this.gemList;
    }


    public ArrayList<Gem> getList()
    {
        return this.list;
    }

    public int getSongLength()
    {
        return this.songLength;
    }

    public synchronized void draw(Canvas canvas, Paint paint)
    {
        counter++;
        this.tempGem = this.gemList.get(counter);
        canvas.drawBitmap(this.tempGem.getColor(), 10, 2, paint);
    }

}
