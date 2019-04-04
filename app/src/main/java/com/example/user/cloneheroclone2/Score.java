package com.example.user.cloneheroclone2;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by User on 17/12/2018.
 */

public class Score {

    public String score;
    public String songName;
    public String name;
    public long id;
    public String dif;

    public Score(String score, String songName, String name, String dif) {
        this.score = score;
        this.songName = songName;
        this.name = name;
        this.dif = dif;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static final String COLUMN_FIRSTNAME = "Name";

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }
}
