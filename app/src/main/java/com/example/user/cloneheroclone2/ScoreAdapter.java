package com.example.user.cloneheroclone2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2018.
 */

public class ScoreAdapter extends ArrayAdapter<Score> {

    private Context context;
    private ArrayList<Score> scores;
    private String newName;
    public ScoreAdapter(@NonNull Context context, ArrayList<Score> list)
    {

        super(context, R.layout.score_item, list);

        //super(list.get(R.layout.score_item).getScore(), list.get(R.layout.score_item).getSongName(), list.get(R.layout.score_item).getSongPic());
        this.context = context;
        this.scores = list;

    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.score_item, parent, false);
        TextView songName = (TextView) rowView.findViewById(R.id.songNameTextView) ;
        songName.setText(scores.get(position).getSongName());
        ImageView songImage = (ImageView) rowView.findViewById(R.id.songImage);

        if (scores.get(position).getSongName().equals("Master Of Puppets - Metallica") )
        {
            newName = "metallica";
        }
        else if(scores.get(position).getSongName().equals("Symphony Of Destruction - Megadeth"))
        {
            newName = "megadeth";
        }

        int id = context.getResources().getIdentifier(newName.toString(), "drawable", context.getPackageName());
        songImage.setImageResource(id);
        TextView name = (TextView) rowView.findViewById(R.id.playerNameTextView);
        name.setText(scores.get(position).getName());
        TextView score = (TextView) rowView.findViewById(R.id.scoreTextView);
        score.setText(scores.get(position).getScore());
        return rowView;

    }
}
