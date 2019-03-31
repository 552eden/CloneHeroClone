package com.example.user.cloneheroclone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameSetterActivity extends AppCompatActivity implements View.OnClickListener {

    public String firstName;
    public String difficulity;
    public String songName;
    private EditText nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_setter);
        Intent intent = getIntent();
        songName = intent.getExtras().getString("SongName");
        Button easyBtn= (Button) findViewById(R.id.dif_easy);
        Button medBtn= (Button) findViewById(R.id.dif_medium);
        Button hardBtn= (Button) findViewById(R.id.dif_hard);

        easyBtn.setOnClickListener(this);
        medBtn.setOnClickListener(this);
        hardBtn.setOnClickListener(this);
        nameET = (EditText)findViewById(R.id.nameEditText);



    }

    @Override
    public void onClick(View v) {
        EditText ETFname = (EditText) findViewById(R.id.nameEditText);
        firstName = ETFname.getText().toString();
        switch(v.getId())
        {
            case R.id.dif_easy :
                Intent i = new Intent(this, RealGameActivity.class);
                i.putExtra("SongName", songName);
                i.putExtra("Difficulty", "easy");
                i.putExtra("FirstName", firstName);
                if(!nameET.getText().toString().isEmpty())
                    startActivity(i);
                else
                    Toast.makeText(this, "Input Name!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dif_medium :
                Intent j = new Intent(this, RealGameActivity.class);
                j.putExtra("SongName", songName);
                j.putExtra("Difficulty", "med");
                j.putExtra("FirstName", firstName);
                if(!nameET.getText().toString().isEmpty())
                    startActivity(j);
                else
                    Toast.makeText(this, "Input Name!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dif_hard :
                Intent f = new Intent(this, RealGameActivity.class);
                f.putExtra("SongName", songName);
                f.putExtra("Difficulty", "hard");
                f.putExtra("FirstName", firstName);
                if(!nameET.getText().toString().isEmpty())
                    startActivity(f);
                else
                    Toast.makeText(this, "Input Name!", Toast.LENGTH_SHORT).show();
                break;



        }
    }


}
