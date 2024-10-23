package com.haitomns.mrimageviewer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class FullscreenImageActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        // Get the image name, image collection, and folder path from Intent
        String imageName = getIntent().getStringExtra("imageName");
        String[] imageCollection = getIntent().getStringArrayExtra("imagesCollection");
        String folderPath = getIntent().getStringExtra("folderPath");

        // Set up ViewPager2 for image swiping
        viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(this, imageCollection, folderPath);
        viewPager.setAdapter(pagerAdapter);

        // Find the index of the passed imageName in the imageCollection
        int initialPosition = Arrays.asList(imageCollection).indexOf(imageName);

        // If the image is found, set the ViewPager to display that image initially
        if (initialPosition != -1) {
            viewPager.setCurrentItem(initialPosition, false); // Set the current item without animation
        }

        // Set up RecyclerView for thumbnails
        RecyclerView thumbnailRecyclerView = findViewById(R.id.recyclerViewThumbnails);
        thumbnailRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(this, imageCollection, folderPath, position -> {
            // Change ViewPager page when thumbnail is clicked
            viewPager.setCurrentItem(position);
        });

        thumbnailRecyclerView.setAdapter(thumbnailAdapter);
    }
}