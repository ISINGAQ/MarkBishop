package com.example.schoolboard;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;
import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboard.ApiResponse.Achievements;
import com.example.schoolboard.ApiResponse.Event;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.MyViewHolder>{
    Context context;
    AchievementAdapter.OnItemClickListener onItemClickListener;
    ArrayList<Achievements> list;

    final String NEW_FORMAT = "dd.MM.yyyy";

    public AchievementAdapter(Context context, ArrayList<Achievements> list, AchievementAdapter.OnItemClickListener onItemClickListener){
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AchievementAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.achievement_item,parent,false);
        return new AchievementAdapter.MyViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementAdapter.MyViewHolder holder, int position){
        Achievements achievements = list.get(position);
        Locale locale = new Locale("ru", "RU");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date date = null;
        try {
            date = formatter.parse(achievements.getDateOfObtained());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        formatter.applyPattern(NEW_FORMAT);
        String formattedDateString = formatter.format(date);

        holder.title.setText(achievements.getTitle());
        holder.obtained.setText(formattedDateString);
        holder.grade.setText(achievements.achievementGrade.getTitle());
        holder.type.setText(achievements.achievementType.getTitle());
        switch (achievements.getDirection()){
            case "Научный":
                holder.cover.setBackgroundResource(R.drawable.science);
                break;
            case "Спортивные":
                holder.cover.setBackgroundResource(R.drawable.sport);
                break;
            case "Творческий":
                holder.cover.setBackgroundResource(R.drawable.art);
                break;
            case "Инженерно-техническое":
                holder.cover.setBackgroundResource(R.drawable.tech);
                break;
            case "Общественная деятельность и волонтёрство":
                holder.cover.setBackgroundResource(R.drawable.volunt);
                break;
            default:
                holder.cover.setBackgroundResource(R.drawable.volunt);
        }

        holder.cover.setColorFilter(holder.cover.getContext().getResources().getColor(R.color.white));

    }


    @Override
    public int getItemCount(){return list.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, obtained, direction, type, grade;
        ImageView cover;

        AchievementAdapter.OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, AchievementAdapter.OnItemClickListener onItemClickListener){
            super(itemView);


            title = itemView.findViewById(R.id.achievementTitleTextView);
            obtained = itemView.findViewById(R.id.dateOfObtainedAchievementTextView);
            type = itemView.findViewById(R.id.achievementType);
            grade = itemView.findViewById(R.id.achievementGrade);
            cover = itemView.findViewById(R.id.achievementImageView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){onItemClickListener.onItemClick(getAdapterPosition());}
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



}
