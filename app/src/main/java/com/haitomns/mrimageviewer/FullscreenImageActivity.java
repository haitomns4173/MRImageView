package com.haitomns.mrimageviewer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.io.InputStream;

public class FullscreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PhotoView photoView = findViewById(R.id.fullscreenImageView);

        String imageName = getIntent().getStringExtra("imageName");
        String folderPath = getIntent().getStringExtra("folderPath");

        if (imageName != null) {
            getSupportActionBar().setTitle(imageName.replace(".jpg", ""));
        }

        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open(folderPath + "/" + imageName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            photoView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}