package com.haitomns.mrimageviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final List<String> imageNames;
    private final Context context;
    private final OnImageClickListener listener;
    private final String folderPath;

    public interface OnImageClickListener {
        void onImageClick(String imageName);
    }

    public ImageAdapter(List<String> imageNames, Context context, String folderPath, OnImageClickListener listener) {
        this.imageNames = imageNames;
        this.context = context;
        this.listener = listener;
        this.folderPath = folderPath;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageName = imageNames.get(position);
        String imageLabel = imageName.substring(0, imageName.lastIndexOf('.'));
        holder.textView.setText(imageLabel);

        // Load image from the specified folder
        try {
            InputStream is = context.getAssets().open(folderPath + "/" + imageName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(v -> listener.onImageClick(imageName));
    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}