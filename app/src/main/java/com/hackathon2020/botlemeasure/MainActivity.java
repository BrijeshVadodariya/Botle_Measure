package com.hackathon2020.botlemeasure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hackathon2020.botlemeasure.RecyclerViewAdapter.OnItemClickListerener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    BedInfo bedInfo;
    TextView textView;
    ProgressBar progressBar;
    Context context=MainActivity.this;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recylerview);
        ArrayList<BedInfo> bedInfos=new ArrayList<>();
        bedInfos.add(new BedInfo(1,70));
        bedInfos.add(new BedInfo(2,50));
        bedInfos.add(new BedInfo(3,90));
        bedInfos.add(new BedInfo(4,30));
        bedInfos.add(new BedInfo(5,45));

        adapter=new RecyclerViewAdapter(bedInfos);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListerner(new OnItemClickListerener() {
            @Override
            public void getData(int progressData,int bedno) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("bedno",bedno);
                bundle.putInt("fluid",progressData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }



}
