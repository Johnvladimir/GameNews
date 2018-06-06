package com.example.john.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john.gamenews.Object.Players;
import com.example.john.gamenews.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecycler_Player extends RecyclerView.Adapter<AdapterRecycler_Player.MyViewHolder> {

    ArrayList<Players> listPlayers;
    Context context;

    public AdapterRecycler_Player(ArrayList<Players> listPlayers, Context context) {
        this.listPlayers = listPlayers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_players, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler_Player.MyViewHolder holder, int position) {
        holder.textViewPlayer1.setText(listPlayers.get(position).getName());
        holder.textViewPlayer2.setText(listPlayers.get(position).getBiografia());
    }

    @Override
    public int getItemCount() {
        return listPlayers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textViewPlayer1, textViewPlayer2;

        public MyViewHolder(View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.idImagePlayer);
            textViewPlayer1 = itemView.findViewById(R.id.idTextPlayer);
            textViewPlayer2 = itemView.findViewById(R.id.idTextPlayer2);
        }
    }
}
