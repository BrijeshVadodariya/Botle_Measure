package com.hackathon2020.botlemeasure;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public interface OnItemClickListerener{
        public void getData(int progressData,int bedno);
    }

    public ArrayList<BedInfo> bedInfos;

    public RecyclerViewAdapter(ArrayList<BedInfo> bedInfos) {
        this.bedInfos = bedInfos;
    }

    OnItemClickListerener onClickListerener;

    public void setOnClickListerner(OnItemClickListerener onClickListerener){
        this.onClickListerener=onClickListerener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        return new ViewHolder(view,onClickListerener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        BedInfo cur_bedInfo=null;
        try{
            cur_bedInfo=bedInfos.get(position);
            int bedno=cur_bedInfo.getBedno();
            int fluid=cur_bedInfo.getFluid();

            holder.bedno.setText(String.valueOf(bedno));
            holder.progressBar.setProgress(fluid);

        }
        catch (Exception ex){
            Log.i("RVError",ex.toString());
        }
    }

    @Override
    public int getItemCount() {
        return bedInfos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView bedno;
        public ImageView bed_image;
        public ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView, final OnItemClickListerener onClickListerener) {
            super(itemView);
            this.bedno=(TextView) itemView.findViewById(R.id.txtbedno);
            this.bed_image=(ImageView) itemView.findViewById(R.id.imgbed);
            this.progressBar=(ProgressBar) itemView.findViewById(R.id.pbbottle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListerener!=null)
                        onClickListerener.getData(progressBar.getProgress(),Integer.parseInt(bedno.getText().toString()));
                }
            });
        }


    }


}