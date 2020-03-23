package com.napoleao.youtubeclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.napoleao.youtubeclone.R;
import com.napoleao.youtubeclone.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.MyViewHolder> {

    private List<Item> videoList = new ArrayList<>();
    private Context context;

    public AdapterVideos(List<Item> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        return new AdapterVideos.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item video = videoList.get(position);
        holder.titulo.setText(video.snippet.title);

        //Utilizando a biblioteca Picasso para obter uma foto por URL.
        String url = video.snippet.thumbnails.high.url;
        Picasso.get().load(url).into(holder.capa);

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        ImageView capa;

        public MyViewHolder(View view){
            super(view);

            titulo = view.findViewById(R.id.textTitulo);
            capa = view.findViewById(R.id.imageCapa);
        }
    }
}
