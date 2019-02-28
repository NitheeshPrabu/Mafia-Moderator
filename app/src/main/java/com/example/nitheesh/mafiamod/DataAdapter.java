package com.example.nitheesh.mafiamod;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.PlayerViewHolder> {

    private ArrayList<String> players;

    public DataAdapter(ArrayList<String> players) {
        this.players = players;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView tv_player;

        public PlayerViewHolder(View view) {
            super(view);
            tv_player = (TextView)view.findViewById(R.id.tv_player);
        }
    }

    @Override
    public DataAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.PlayerViewHolder viewHolder, int i) {
        viewHolder.tv_player.setText(players.get(i));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void addItem(String player) {
        players.add(player);
        notifyItemInserted(players.size());
    }

    public void removeItem(int position) {
        players.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, players.size());
    }

    public ArrayList<String> getAllPlayers() {
        return this.players;
    }
}
