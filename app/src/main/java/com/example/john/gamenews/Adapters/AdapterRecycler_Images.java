package com.example.john.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecycler_Images extends RecyclerView.Adapter<AdapterRecycler_Images.MyViewHolder> {
    List<News> listImage;
    Context context;

    public AdapterRecycler_Images(List<News> listImage, Context context) {
        this.listImage = listImage;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRecycler_Images.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_images, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler_Images.MyViewHolder holder, int position) {
        Picasso.with(context).load(listImage.get(position).getCoverImage()).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.idImageTab);
        }
    }
}
