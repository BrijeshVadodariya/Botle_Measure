package com.hackathon2020.botlemeasure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathon2020.botlemeasure.RecyclerViewAdapter.OnItemClickListerener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    BedInfo bedInfo;
    TextView textView;
    ProgressBar progressBar;
    Context context=MainActivity.this;
    ListView listView;

    DatabaseReference ref;
    public static String chack;
    SharedPreferences sharedpreferences;
    SharedPreferences sh;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences.Editor editor;
    public static int flag=0;
    public static double finalans=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        
        Enter();
        



    }

    public void Enter(){
        ref = FirebaseDatabase.getInstance().getReference().child("beds");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String Value = dataSnapshot.child("1").getValue().toString();
                
                chack = Value;

                if(flag == 0){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            editor.putString("data",Value);
                            editor.commit();
                        }
                    }, 6000);
                    flag++;
                }
                else {

                    DataDiscovry(chack);
                }


                recyclerView=findViewById(R.id.recylerview);
                ArrayList<BedInfo> bedInfos=new ArrayList<>();
                bedInfos.add(new BedInfo(1, ((int) getFinalans())));


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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void DataDiscovry(String chk){
        sh = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String a = sharedpreferences.getString("data","no found");
        Double x = Double.valueOf(a);
        Double y = Double.valueOf(chk);
        Double z = (y*100)/x;
         finalans = BigDecimal.valueOf(z)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        getFinalans();
    }

    public static double getFinalans(){
        //Toast.makeText(this, "Value is"+ finalans, Toast.LENGTH_SHORT).show();
        return finalans;

    }


}
