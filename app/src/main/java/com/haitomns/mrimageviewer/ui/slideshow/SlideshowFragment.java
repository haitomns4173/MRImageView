package com.haitomns.mrimageviewer.ui.slideshow;

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
import com.haitomns.mrimageviewer.PDFViewerActivity;
import com.haitomns.mrimageviewer.databinding.FragmentSlideshowBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private List<String> imageNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        String folderPath = "puristroImages";
        loadImagesFromAssets(requireContext(), folderPath);

        RecyclerView recyclerView = binding.recyclerViewSlideShow;
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
                "GYN.jpg",
                "ORTHO.jpg",
                "PAEDIATRIC.jpg",
                "PHYSICIAN.jpg"
        };

        for (String imageName : explicitImageNames) {
            imageNames.add(imageName);
        }
    }

    private void onImageClick(String imageName) {
        String[] imageCollectionImages;

        if(imageName.equals("GYN.jpg")){
            imageCollectionImages = new String[]{"GYN_1.jpg", "GYN_2.jpg", "GYN_3.jpg", "GYN_4.jpg", "GYN_5.jpg", "GYN_6.jpg", "GYN_7.jpg"};
        } else if (imageName.equals("ORTHO.jpg")) {
            imageCollectionImages = new String[]{"ORTHO_1.jpg", "ORTHO_2.jpg", "ORTHO_3.jpg"};
        } else if (imageName.equals("PAEDIATRIC.jpg")) {
            imageCollectionImages = new  String[]{"PAEDIATRIC_1.jpg", "PAEDIATRIC_2.jpg"};
        } else {
            imageCollectionImages = new String[]{"PHYSICIAN_1.jpg", "PHYSICIAN_2.jpg", "PHYSICIAN_3.jpg", "PHYSICIAN_4.jpg"};
        }

        Intent intent = new Intent(getActivity(), FullscreenImageActivity.class);
        intent.putExtra("imageName", imageName);
        intent.putExtra("imagesCollection", imageCollectionImages);
        intent.putExtra("folderPath", "puristroImages");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}