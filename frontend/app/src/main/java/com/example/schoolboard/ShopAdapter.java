package com.example.schoolboard;

import static com.example.schoolboard.NetworkService.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboard.ApiResponse.ShopList;
import com.example.schoolboard.ApiResponse.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>{
    Context context;
    ShopAdapter.OnItemClickListener onItemClickListener;
    ArrayList<ShopList> list;

    public ShopAdapter(Context context, ArrayList<ShopList> list, ShopAdapter.OnItemClickListener onItemClickListener){
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.shop_item,parent,false);
        return new ShopAdapter.MyViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.MyViewHolder holder, int position){
        ShopList shop = list.get(position);
        holder.title.setText(shop.getTitle());
        holder.content.setText(shop.getContent());
        holder.cost.setText(String.valueOf("Цена: " + shop.getCost()));
        if(shop.getCost() > User.getCurrentPoints()){
            holder.purchase.setEnabled(false);
        }else{
            holder.purchase.setBackgroundResource(R.drawable.gradient);
            holder.purchase.setEnabled(true);
            holder.purchase.setText("Приобрести");
        }
        Picasso.with(context.getApplicationContext()).load(BASE_URL+shop.getImage()).into(holder.cover);
    }


    @Override
    public int getItemCount(){return list.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, content, cost;
        ImageView cover;
        Button purchase;

        ShopAdapter.OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, ShopAdapter.OnItemClickListener onItemClickListener){
            super(itemView);

            cover = itemView.findViewById(R.id.shopImageView);
            cost = itemView.findViewById(R.id.shopCostTextView);
            title = itemView.findViewById(R.id.shopTitleTextView);
            content = itemView.findViewById(R.id.shopContentTextView);
            purchase = itemView.findViewById(R.id.shopPurchase);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        public void onClick(View view){onItemClickListener.onItemClick(getAdapterPosition());}
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
