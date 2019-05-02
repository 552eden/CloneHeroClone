package com.example.user.cloneheroclone2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbHelper scoreDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();
        scoreDataBase = new DbHelper(this);


    }

    public void deleteAllScores(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Scores?");
        builder.setMessage("Really delete all scores?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { scoreDataBase.death();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "YAY ^_^", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.READ_PHONE_STATE
    };

    private void checkAndRequestPermissions()
    {
        List<String> neededPermissionsList = new ArrayList<>();
        for(String permission : permissions)
        {
            if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED);
                neededPermissionsList.add(permission);
        }
        if(!neededPermissionsList.isEmpty())
        {
            ActivityCompat.requestPermissions(this, neededPermissionsList.toArray(new String[neededPermissionsList.size()]), 1);
        }
    }



    public void screenChanger(View v)
    {
        Intent intent = new Intent(this,ScoreActivity.class);
        startActivity(intent);
    }

    public void screenChanger2(View v)
    {
        Intent intent = new Intent(this,CameraActivity.class);
        startActivity(intent);
    }

    public void screenChanger3(View v)
    {
        Intent intent = new Intent(this,HowToPlayActivity.class);
        startActivity(intent);
    }

    public void screenChanger4(View v)
    {
        Intent intent = new Intent(this,CreditsActivity.class);
        startActivity(intent);
    }

    public void startGame(View v)
    {
        Intent intent = new Intent(this, SongListActivity.class);
        startActivity(intent);
    }

    public void exitDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Really Quit?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "YAY ^_^", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
