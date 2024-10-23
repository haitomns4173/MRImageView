package com.haitomns.mrimageviewer.ui.scan;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitomns.mrimageviewer.FullscreenImageActivity;
import com.haitomns.mrimageviewer.ImageAdapter;
import com.haitomns.mrimageviewer.PDFViewerActivity;
import com.haitomns.mrimageviewer.databinding.FragmentScanBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanFragment extends Fragment {

    private FragmentScanBinding binding;
    private List<String> imageNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        String folderPath = "imdslImages";
        loadImagesFromAssets(requireContext(), folderPath);

        RecyclerView recyclerView = binding.recyclerViewScan;
        int orientation = getResources().getConfiguration().orientation;
        int spanCount = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 4 : 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        ImageAdapter imageAdapter = new ImageAdapter(imageNames, requireContext(), folderPath, this::onImageClick);
        recyclerView.setAdapter(imageAdapter);

        return root;
    }

    private void loadImagesFromAssets(Context context, String folderPath) {
        imageNames.clear();

        String[] explicitImageNames = {
                "ENT.jpg",
                "PORCTOLOGY.jpg"
        };

        for (String imageName : explicitImageNames) {
            imageNames.add(imageName);
        }
    }

    private void onImageClick(String imageName) {
        String[] imageCollectionImages;

        if(imageName.equals("ENT.jpg")){
            imageCollectionImages = new String[]{"ENT_1.jpg", "ENT_2.jpg"};
        } else {
            imageCollectionImages = new String[]{"PORCTOLOGY_1.jpg", "PORCTOLOGY_2.jpg", "PORCTOLOGY_3.jpg", "PORCTOLOGY_4.jpg"};
        }

        Intent intent = new Intent(getActivity(), FullscreenImageActivity.class);
        intent.putExtra("imageName", imageName);
        intent.putExtra("imagesCollection", imageCollectionImages);
        intent.putExtra("folderPath", "imdslImages");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}