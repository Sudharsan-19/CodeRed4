package com.example.codered4;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
    private ArrayList<CourseRVModal> courseRVModalArrayList;
    private Context context;
    int lastPos=-1;
    private CourseClickInterface courseClickInterface;
    private ObjectInputStream.GetField Picasso;

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CourseRVModal courseRVModal = courseRVModalArrayList.get(position);
        holder.courseNameTV.setText("Recipient Name: "+courseRVModal.getNameOfRecipient());
        holder.coursePriceTV.setText("Blood Group: "+courseRVModal.getBloodGroup());
        holder.UnitNeeded.setText("Units Needed "+courseRVModal.getUnitNeeded());
        holder.hospAdd.setText("Hospital Link: "+courseRVModal.getHospitalAddress());
        //Picasso.get().load(courseRVModal.getIMG()).into(holder.courseIV);
        setaAimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });

    }
    private void setaAimation(View itemView, int position){
        if(position>lastPos)
        {
            Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos=position;
        }
    }
    @Override
    public int getItemCount() {

        return courseRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView courseNameTV,coursePriceTV,UnitNeeded,hospAdd;
        private ImageView courseIV;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            courseNameTV=itemView.findViewById(R.id.idTVCourseName);
            coursePriceTV=itemView.findViewById(R.id.idTVCousePrice);
            UnitNeeded=itemView.findViewById(R.id.idTVneed);
            hospAdd=itemView.findViewById(R.id.idTVhospAdd);
            //courseIV=itemView.findViewById(R.id.idIVCourse);
        }
    }
    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}
