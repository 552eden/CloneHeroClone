package com.example.user.cloneheroclone2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Song
{

    private int songLength;
    private ArrayList<Gem> list;
    private String[] listS;

    public Song(int songLength, Context context, int h, String song, String difficulty) {
        this.songLength = songLength;
        this.list = new ArrayList<Gem>();
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
            if(difficulty.equals("med"))
            {
                listS = context.getResources().getStringArray(R.array.symphonyOfDestructionMed);
            }
            else if(difficulty.equals("hard"))
            {
                listS = context.getResources().getStringArray(R.array.symphonyOfDestructionHard);
            }
            else
            {
                listS = context.getResources().getStringArray(R.array.symphonyOfDestruction);
            }
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


    public ArrayList<Gem> getList()
    {
        return this.list;
    }

    public int getSongLength()
    {
        return this.songLength;
    }

}
