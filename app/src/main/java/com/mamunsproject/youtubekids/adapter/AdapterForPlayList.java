package com.mamunsproject.youtubekids.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cartoontuberefinefinal.Model.PlayLIstModel;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.adapter.interfacew.OnRecyclerViewITemClickListener;

import java.util.List;

public class AdapterForPlayList extends RecyclerView.Adapter<AdapterForPlayList.AdapterHolder> {

    List<PlayLIstModel> list;
    private OnRecyclerViewITemClickListener onRecyclerViewITemClickListener;


    public AdapterForPlayList(List<PlayLIstModel> list, OnRecyclerViewITemClickListener onRecyclerViewITemClickListener) {
        this.list = list;
        this.onRecyclerViewITemClickListener = onRecyclerViewITemClickListener;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sa, parent, false), onRecyclerViewITemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImg());
        holder.title.setText(list.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title;
        OnRecyclerViewITemClickListener onRecyclerViewITemClickListener;


        public AdapterHolder(@NonNull View itemView, OnRecyclerViewITemClickListener onRecyclerViewITemClickListener) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imagePlaylist);
            title = itemView.findViewById(R.id.titlePlaylist);

            this.onRecyclerViewITemClickListener = onRecyclerViewITemClickListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            onRecyclerViewITemClickListener.onRecItemClick(getAdapterPosition());
        }

    }


}
