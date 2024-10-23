package com.haitomns.mrimageviewer.ui.home;

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
import com.haitomns.mrimageviewer.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<String> imageNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        String folderPath = "suturePlanetImages";
        loadImagesFromAssets(requireContext(), folderPath);

        RecyclerView recyclerView = binding.recyclerView;
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
                "ABSORBABLE.jpg",
                "NON-ABSORBABLE.jpg",
                "NETFIX.jpg",
                "V-FIX.jpg",
                "ULTRANET.jpg",
                "NETPTFE.jpg",
                "NETPLER.jpg",
                "C-MESH.jpg"
        };

        for (String imageName : explicitImageNames) {
            imageNames.add(imageName);
        }
    }

    private void onImageClick(String imageName) {
        String[] imageCollectionImages;

        if(imageName.equals("ABSORBABLE.jpg")){
            imageCollectionImages = new String[]{"ABSORBABLE.jpg"};
        }
        else if(imageName.equals("NON-ABSORBABLE.jpg")){
            imageCollectionImages = new String[]{"NON-ABSORBABLE.jpg"};
        }
        else if(imageName.equals("NETFIX.jpg")){
            imageCollectionImages = new String[]{"NETFIX.jpg"};
        }
        else if(imageName.equals("V-FIX.jpg")){
            imageCollectionImages = new String[]{"V-FIX.jpg", "V-FIX_1.jpg", "V-FIX_2.jpg"};
        }
        else if (imageName.equals("ULTRANET.jpg")) {
            imageCollectionImages = new String[]{"ULTRANET_1.jpg", "ULTRANET_2.jpg"};
        }
        else if(imageName.equals("NETPTFE.jpg")){
            imageCollectionImages = new String[]{"NETPTFE_1.jpg", "NETPTFE_2.jpg", "NETPTFE_3.jpg", "NETPTFE_4.jpg", "NETPTFE_5.jpg", "NETPTFE_6.jpg", "NETPTFE_7.jpg", "NETPTFE_8.jpg"};
        }
        else if(imageName.equals("NETPLER.jpg")){
            imageCollectionImages = new String[]{"NETPLER_1.jpg", "NETPLER_2.jpg"};
        }
        else if (imageName.equals("C-MESH.jpg")) {
            imageCollectionImages = new String[]{"C-MESH_1.jpg", "C-MESH_2.jpg", "C-MESH_3.jpg", "C-MESH_4.jpg", "C-MESH_5.jpg", "C-MESH_6.jpg", "C-MESH_7.jpg", "C-MESH_8.jpg", "C-MESH_9.jpg", "C-MESH_10.jpg", "C-MESH_11.jpg", "C-MESH_12.jpg", "C-MESH_13.jpg", "C-MESH_14.jpg", "C-MESH_15.jpg", "C-MESH_16.jpg"};
        }
        else {
            imageCollectionImages = new String[]{"ABSORBABLE.jpg", "NON-ABSORBABLE.jpg", "NETFIX.jpg"};
        }

        Intent intent = new Intent(getActivity(), FullscreenImageActivity.class);
        intent.putExtra("imageName", imageName);
        intent.putExtra("imagesCollection", imageCollectionImages);
        intent.putExtra("folderPath", "suturePlanetImages");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
