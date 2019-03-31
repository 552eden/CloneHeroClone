package com.example.user.cloneheroclone2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {



    private ListView ScoreslistView;
    private ArrayList<Score> scoreList;
    private ScoreAdapter scoreAdapter;
    private DbHelper scoreDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreList = new ArrayList<>();
        scoreDataBase = new DbHelper(this);
        scoreDataBase.createScore(new Score("695", "Master Of Puppets - Metallica", "Eden", "Hard"));
        scoreDataBase.createScore(new Score("420", "Symphony Of Destruction - Megadeth", "Beni", "Easy"));
        scoreList = scoreDataBase.getAllScores();
        ScoreslistView = (ListView) findViewById(R.id.lvplayer);
        scoreAdapter = new ScoreAdapter(this, scoreList);
        ScoreslistView.setAdapter(scoreAdapter);
        ScoreslistView.setOnItemClickListener(this);
        ScoreslistView.setOnItemLongClickListener(this);

    }

    @Override
    protected void onDestroy() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Score score = scoreList.get(position);
        scoreDataBase.deleteScoreByRow(score.id);
        scoreList = scoreDataBase.getAllScores();
        scoreAdapter = new ScoreAdapter(this, scoreList);
        ScoreslistView.setAdapter(scoreAdapter);
        ScoreslistView.setOnItemClickListener(this);
        scoreAdapter.notifyDataSetChanged();

    }

    public void mainmenu (View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       /* Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "552eden@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Look at my score!");*/
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "My score is: " + score.getScore());
        //startActivity(Intent.createChooser(emailIntent, "Brag!"));

        Score score = scoreList.get(position);

        String shareBody = "My score on 'Clone Hero Clone' on the song: '" + score.getSongName() + "'\n On difficulty: " + score.getDif() + "\n Is: " + score.getScore();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Look at my 'Clone Hero Clone' score!");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Via:"));

        return false;
    }
}

