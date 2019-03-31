package com.example.user.cloneheroclone2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "players.db";
    public static final String TABLE_PLAYER = "tblplayer";
    public static final int DATABASEVERSION = 1;
    public static final String COLUMN_FIRSTNAME = "Name";
    public static final String COLUMN_SCORE = "Score";
    public static final String COLUMN_SONG_NAME = "SongName";
    public static final String COLUMN_DIF = "Dif";
    public static final String COLUMN_ID = "Id";
    public static final String[] allColumns = {COLUMN_ID, COLUMN_FIRSTNAME,  COLUMN_SONG_NAME,COLUMN_DIF ,COLUMN_SCORE};
    public static final String sortOrder = COLUMN_SCORE + " DESC";

    SQLiteDatabase database;

    public static final String CREATE_TABLE_PLAYER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_PLAYER + "(" +
            COLUMN_ID+" INTEGER PRIMARY KEY, "+
            COLUMN_FIRSTNAME + " TEXT," +
            COLUMN_SONG_NAME + " TEXT," +
            COLUMN_DIF + " TEXT," +
            COLUMN_SCORE + " INTEGER );";


    public void createScore(Score score)
    {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, score.getName());
        values.put(COLUMN_SCORE, score.getScore());
        values.put(COLUMN_SONG_NAME, score.getSongName());
        values.put(COLUMN_DIF, score.getDif());
        long id = database.insert(TABLE_PLAYER, null, values);
        score.setId(id);
        database.close();
    }

    public void deleteAll()
    {
        database = getWritableDatabase();
        database.delete(TABLE_PLAYER, null, null);
        database.close();
    }

    public void deleteScoreByRow(long id)
    {
        database = getWritableDatabase();
        database.delete(TABLE_PLAYER, COLUMN_ID+"="+id, null);
        database.close();
    }




    public ArrayList<Score> getAllScores()
    {
        database = getReadableDatabase();
        ArrayList<Score> list = new ArrayList<>();
        Cursor cursor = database.query(TABLE_PLAYER, allColumns, null, null, null, null, sortOrder);
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext() )
            {
                String fname = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME));
                String songname = cursor.getString(cursor.getColumnIndex(COLUMN_SONG_NAME));
                String score = cursor.getString(cursor.getColumnIndex(COLUMN_SCORE));
                String dif = cursor.getString(cursor.getColumnIndex(COLUMN_DIF));
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                Score c = new Score(score, songname, fname, dif);
                c.setId(id);
                list.add(c);
            }
        }

        database.close();
        return list;
    }

    public long updateById (Score score)
    {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, score.getId());
        values.put(COLUMN_FIRSTNAME, score.getName());
        values.put(COLUMN_SCORE, score.getScore());
        values.put(COLUMN_SONG_NAME, score.getSongName());
        values.put(COLUMN_DIF, score.getDif());
        long id = database.update(TABLE_PLAYER, values, COLUMN_ID + "=" + score.getId(), null);
        database.close();
        return id;
    }


    public DbHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }
}
