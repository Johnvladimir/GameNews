package com.example.john.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    ArrayList<News> listaNews;
    Context ctx;

    public AdapterRecycler(ArrayList<News> listaNews, Context ctx) {
        this.listaNews = listaNews;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.MyViewHolder holder, int position) {
        holder.textView.setText(listaNews.get(position).getTitle());
        holder.textView2.setText(listaNews.get(position).getDescription());

      /*  if (position == 0 || position % 3 == 0) {
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            sglp.setFullSpan(true);
            holder.itemView.setLayoutParams(sglp);
        }*/
    }

    @Override
    public int getItemCount() {
        return listaNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView textView, textView2;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.idImage1);
            textView = itemView.findViewById(R.id.idText1);
            textView2 = itemView.findViewById(R.id.idText2);
        }
    }
}