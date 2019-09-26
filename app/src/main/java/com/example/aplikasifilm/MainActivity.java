package com.example.aplikasifilm;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aplikasifilm.model.ResponseFilm;
import com.example.aplikasifilm.model.ResultsItem;
import com.example.aplikasifilm.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<ResultsItem> dataMovie = new ArrayList<>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerView);
        //1. bikin layout
        // model data
        /*ModelData film1 = new ModelData();
        film1.setNamaFilm("Nama Film");
        film1.setPosterFilm("https://image.tmdb.org/t/p/w600_and_h900_bestv2/lcq8dVxeeOqHvvgcte707K0KVx5.jpg");

        for (int i = 0; i < 20; i++) {
            dataMovie.add(film1);
        }*/

        getDataInternet();

        //adapter
        recycler.setAdapter(new AdapterFilm(MainActivity.this, dataMovie));

        //4. layout manager
        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    private void getDataInternet() {
        final ProgressDialog loading = new ProgressDialog(MainActivity.this);
        loading.setMessage("menunggu...");
        loading.show();

        Call<ResponseFilm> request = RetrofitConfig.getApiService()
                .ambilDataFilm("0c34c52cad76e85a3cfea9b4dd5d52f7");

        request.enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                loading.dismiss();

                if (response.isSuccessful()){
                    dataMovie = response.body().getResults();
                    recycler.setAdapter(new AdapterFilm(MainActivity.this, dataMovie));
                }else {
                    Toast.makeText(MainActivity.this, "Permintaan Tidak Berhasil",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Permintaan Gagal "
                +t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
