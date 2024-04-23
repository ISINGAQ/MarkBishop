package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboard.ApiResponse.Event;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import kotlinx.coroutines.ObsoleteCoroutinesApi;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    OnItemClickListener onItemClickListener;
    ArrayList<Event> list;

    final String NEW_FORMAT = "dd.MM.yyyy HH:mm";



    public EventAdapter(Context context, ArrayList<Event> list, OnItemClickListener onItemClickListener){
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){

        Event event = list.get(position);
        Locale locale = new Locale("ru", "RU");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", locale);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Yakutsk"));

        Date date = null;
        try {
            date = formatter.parse(event.getDateOfEvent());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        formatter.applyPattern(NEW_FORMAT);
        String formattedDateString = formatter.format(date);
        holder.title.setText(event.getTitle());
        holder.content.setText(event.getContent());
        holder.organization.setText("Организатор " + event.getOrganization());
        System.out.println(formattedDateString);
        holder.date.setText("Дата и время проведения мероприятия: " + formattedDateString);

        Picasso.with(context.getApplicationContext()).load(BASE_URL+event.getImage()).into(holder.cover);
    }


    @Override
    public int getItemCount(){return list.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, content, date, organization;
        ImageView cover;

        OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener){
            super(itemView);

            cover = itemView.findViewById(R.id.eventImageView);

            title = itemView.findViewById(R.id.textViewTitle);
            content = itemView.findViewById(R.id.textViewContent);
            date = itemView.findViewById(R.id.textViewDate);
            organization = itemView.findViewById(R.id.textViewOrgan);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        public void onClick(View view){onItemClickListener.onItemClick(getAdapterPosition());}
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


}
