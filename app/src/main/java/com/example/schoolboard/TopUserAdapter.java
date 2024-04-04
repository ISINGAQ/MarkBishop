package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboard.ApiResponse.Achievements;
import com.example.schoolboard.ApiResponse.TopUser;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TopUserAdapter extends RecyclerView.Adapter<TopUserAdapter.MyViewHolder>{
    Context context;
    TopUserAdapter.OnItemClickListener onItemClickListener;
    ArrayList<TopUser> list;


    public TopUserAdapter(Context context, ArrayList<TopUser> list, TopUserAdapter.OnItemClickListener onItemClickListener){
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TopUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.top_user_item,parent,false);
        return new TopUserAdapter.MyViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopUserAdapter.MyViewHolder holder, int position){
        TopUser topUser = list.get(position);

        holder.title.setText(topUser.getSurname()+ " " + topUser.getName() + " " + topUser.getPatronymic());
        holder.school.setText(topUser.getSchool());
        holder.grade.setText(String.valueOf(topUser.getGrade()));
        Picasso.with(context.getApplicationContext()).load(BASE_URL+topUser.getImage()).into(holder.cover);


    }


    @Override
    public int getItemCount(){return list.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, school, grade;
        ImageView cover;

        TopUserAdapter.OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, TopUserAdapter.OnItemClickListener onItemClickListener){
            super(itemView);


            title = itemView.findViewById(R.id.topUserTitleTextView);
            school = itemView.findViewById(R.id.topUserSchoolTextView);
            grade = itemView.findViewById(R.id.topUserGradeTextView);
            cover = itemView.findViewById(R.id.topUserImageView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){onItemClickListener.onItemClick(getAdapterPosition());}
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



}
