package com.example.john.gamenews.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    List<News> listaNews;
    Context ctx;
    Dialog MyDialog;

    public AdapterRecycler(List<News> listaNews, Context ctx) {
        this.listaNews = listaNews;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, null, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        MyDialog = new Dialog(ctx);
        MyDialog.setContentView(R.layout.item_list_info_news);

        myViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imagen_info = MyDialog.findViewById(R.id.idImage_info);
                TextView titulo_info = MyDialog.findViewById(R.id.idTitulo_info);
                TextView juego_info = MyDialog.findViewById(R.id.idJuego_info);
                TextView Descripcion_info = MyDialog.findViewById(R.id.idDescripcion_info);
                TextView Cuerpo_info = MyDialog.findViewById(R.id.idCuerpo_info);
                TextView Fecha_info = MyDialog.findViewById(R.id.idFecha_info);

                Picasso.with(ctx).load(listaNews.get(myViewHolder.getAdapterPosition()).getCoverImage()).fit().into(imagen_info);
                titulo_info.setText(listaNews.get(myViewHolder.getAdapterPosition()).getTitle());
                juego_info.setText(listaNews.get(myViewHolder.getAdapterPosition()).getGame());
                Descripcion_info.setText(listaNews.get(myViewHolder.getAdapterPosition()).getDescription());
                Cuerpo_info.setText(listaNews.get(myViewHolder.getAdapterPosition()).getBody());
                Fecha_info.setText(listaNews.get(myViewHolder.getAdapterPosition()).getCreatedDate());

                MyDialog.show();
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(listaNews.get(position).getTitle());
        holder.textView2.setText(listaNews.get(position).getGame());

        Picasso.with(ctx).load(listaNews.get(position).getCoverImage()).fit().into(holder.imageView1);
    }

    @Override
    public int getItemCount() {
        return listaNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView textView, textView2;
        LinearLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.idImage1);
            textView = itemView.findViewById(R.id.idText1);
            textView2 = itemView.findViewById(R.id.idText2);
            item = itemView.findViewById(R.id.item_list_news);
        }
    }
}
