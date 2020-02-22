package com.hackathon2020.botlemeasure;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

public class DetailActivity extends AppCompatActivity {
    int fluid,bedno;
    TextView textView;
    WaveLoadingView waveLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        bedno=bundle.getInt("bedno");
        fluid=bundle.getInt("fluid");
        setTitle("Bed no. "+bedno+" details");
        waveLoadingView=findViewById(R.id.wvpgs);
        waveLoadingView.setProgressValue(fluid);
        textView=findViewById(R.id.txtper);
        textView.setText(String.valueOf(fluid)+"%");
    }

}
