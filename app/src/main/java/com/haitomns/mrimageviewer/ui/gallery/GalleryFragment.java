package com.haitomns.mrimageviewer.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitomns.mrimageviewer.FullscreenImageActivity;
import com.haitomns.mrimageviewer.ImageAdapter;
import com.haitomns.mrimageviewer.databinding.FragmentGalleryBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private List<String> imageNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        String folderPath = "bafanaImages";
        loadImagesFromAssets(requireContext(), folderPath);

        RecyclerView recyclerView = binding.recyclerViewGallery;
        int orientation = getResources().getConfiguration().orientation;
        int spanCount = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 4 : 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        ImageAdapter imageAdapter = new ImageAdapter(imageNames, requireContext(), folderPath, this::onImageClick);
        recyclerView.setAdapter(imageAdapter);

        return root;
    }

    private void loadImagesFromAssets(Context context, String folderPath) {
        imageNames.clear();
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(folderPath);
            if (files != null) {
                for (String filename : files) {
                    imageNames.add(filename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onImageClick(String imageName) {
        Intent intent = new Intent(getActivity(), FullscreenImageActivity.class);
        intent.putExtra("imageName", imageName);
        intent.putExtra("folderPath", "bafanaImages");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}