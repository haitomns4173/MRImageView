package com.haitomns.mrimageviewer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private final String[] imageNames;
    private final String folderPath;
    private final Context context;
    private final OnThumbnailClickListener listener;

    public interface OnThumbnailClickListener {
        void onThumbnailClick(int position);
    }

    public ThumbnailAdapter(Context context, String[] imageNames, String folderPath, OnThumbnailClickListener listener) {
        this.context = context;
        this.imageNames = imageNames;
        this.folderPath = folderPath;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(folderPath + "/" + imageNames[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.thumbnailView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(v -> listener.onThumbnailClick(position));
    }

    @Override
    public int getItemCount() {
        return imageNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailView = itemView.findViewById(R.id.thumbnail);
        }
    }
}