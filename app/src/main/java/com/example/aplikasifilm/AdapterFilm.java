package com.example.aplikasifilm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasifilm.model.ResultsItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class AdapterFilm extends RecyclerView.Adapter<AdapterFilm.ViewHolderSaya> {

    public static final String DATA_MOVIE = "datafilm";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<ResultsItem> data = new ArrayList<>();

    public AdapterFilm(Context context, List<ResultsItem> data) {
        this.context = context;
        this.data = data;
    }

    //1. menghubungkan layout
    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_film,
                viewGroup, false);
        return new ViewHolderSaya(itemView);
    }

    //3. set data
    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya viewHolderSaya, final int i) {
        viewHolderSaya.tvJudul.setText(data.get(i).getTitle());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+data.get(i).getPosterPath())
                .into(viewHolderSaya.ivPoster);

        viewHolderSaya.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable( DATA_MOVIE, Parcels.wrap(data.get(i)));
                intent.putExtra(DATA_EXTRA, bundle);

                context.startActivity(intent);
            }
        });
    }

    //4. jumlah data
    @Override
    public int getItemCount() {
        return data.size();
    }

    //2. komponen item
    public class ViewHolderSaya extends RecyclerView.ViewHolder {
        TextView tvJudul;
        ImageView ivPoster;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            ivPoster = itemView.findViewById(R.id.iv_item);
        }
    }
}
