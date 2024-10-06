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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PDFView pdfView = findViewById(R.id.pdfView);

        Intent intent = getIntent();
        String pdfName = intent.getStringExtra("pdfName");
        String folderPath = intent.getStringExtra("folderPath");

        if (pdfName != null) {
            getSupportActionBar().setTitle(pdfName.replace(".pdf", ""));
        }
        try {
            InputStream inputStream = getAssets().open( folderPath + "/" + pdfName);
            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .enableDoubletap(true)
                    .load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
