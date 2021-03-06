package com.example.user.cloneheroclone2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {
    private static final int CAM_ANSWER = 12;
    private static final int SELECT_PHOTO = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Sounds.setSound(this);
        ImageView cameraImageView = (ImageView) findViewById(R.id.cameraShower);
        cameraImageView.setImageBitmap(loadImageBitmap("pic.png"));
    }

    public void takeImage(View v) {
        Intent camInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camInent, CAM_ANSWER);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        ImageView cameraImageView = (ImageView) findViewById(R.id.cameraShower);

        if (requestCode == CAM_ANSWER && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) Data.getExtras().get("data");
            cameraImageView.setImageBitmap(imageBitmap);
            privateAddPic(imageBitmap);
            //Sounds.playSong(R.raw.win);
           // publicAddPic(imageBitmap);
        }
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
            Uri Seleced_Image_Uri = Data.getData();
            cameraImageView.setImageURI(Seleced_Image_Uri);

        }
    }


    public void onGalleryClick(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }



    private void privateAddPic(@NonNull Bitmap imageBitmap)
    {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("pic.png", Context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Bitmap loadImageBitmap(String name)
    {
        FileInputStream fis;
        Bitmap bitmap = null;
        try
        {
            fis = openFileInput(name);
            bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void RestoreImage(View v)
    {
        ImageView cameraImageView = (ImageView) findViewById(R.id.cameraShower);
        cameraImageView.setImageBitmap(loadImageBitmap("pic.png"));
    }


}



