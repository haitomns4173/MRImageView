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
        String pdfName = imageName.replace(".jpg", ".pdf"); // Replace image extension with pdf

        Intent intent = new Intent(getActivity(), PDFViewerActivity.class);
        intent.putExtra("pdfName", pdfName);
        intent.putExtra("folderPath", "imdslPdf");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}