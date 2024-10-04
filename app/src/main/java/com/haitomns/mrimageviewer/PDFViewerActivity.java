package com.haitomns.mrimageviewer;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.IOException;
import java.io.InputStream;

public class PDFViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        PDFView pdfView = findViewById(R.id.pdfView);

        Intent intent = getIntent();
        String pdfName = intent.getStringExtra("pdfName");

        try {
            // Load the PDF from the assets folder
            InputStream inputStream = getAssets().open("suturePlanetPdf/" + pdfName);
            pdfView.fromStream(inputStream)
                    .enableSwipe(true)  // allows users to swipe between pages
                    .enableDoubletap(true)  // allows zooming with double-tap
                    .load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
