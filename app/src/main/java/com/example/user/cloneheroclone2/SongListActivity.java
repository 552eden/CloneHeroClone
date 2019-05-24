package com.example.user.cloneheroclone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SongListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        Button masterBtn= (Button) findViewById(R.id.btn_Master);
        Button symphonyBtn= (Button) findViewById(R.id.btn_Symphony);

        masterBtn.setOnClickListener(this);
        symphonyBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_Master :
                Intent i = new Intent(this, NameSetterActivity.class);
                i.putExtra("SongName", "Master Of Puppets - Metallica");
                startActivity(i);
                break;
            case R.id.btn_Symphony :
                Intent j = new Intent(this, NameSetterActivity.class);
                j.putExtra("SongName", "Symphony Of Destruction - Megadeth");
                startActivity(j);
                break;



        }
    }



}
