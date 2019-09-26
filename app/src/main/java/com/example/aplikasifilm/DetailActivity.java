package com.example.aplikasifilm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasifilm.model.ResultsItem;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    ResultsItem dataFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getBundleExtra(AdapterFilm.DATA_EXTRA);
        dataFilm = Parcels.unwrap(bundle.getParcelable(AdapterFilm.DATA_MOVIE));

        getSupportActionBar().setTitle(dataFilm.getTitle());

        ImageView ivDetail = findViewById(R.id.iv_detail_gambar);
        TextView tvDetail = findViewById(R.id.tv_detail);

        Glide.with(DetailActivity.this)
                .load("https://image.tmdb.org/t/p/w500"+dataFilm.getBackdropPath())
                .into(ivDetail);

        tvDetail.setText(dataFilm.getOverview());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
