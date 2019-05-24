package com.kushagra.topeq;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Feature> featureList;
    private DataAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.earthquake_recycler_views);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //swipeRefreshLayout = findViewById(R.id.swipe_container);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                recyclerView.setAdapter(adapter);
//            }
//        });

        EarthquakeRequestInterface requestInterface = retrofit.create(EarthquakeRequestInterface.class);
        Call<EarthquakeResponse> responseCall =requestInterface.getJSON("geojson","earthquake","time",10,4.0);
        responseCall.enqueue(new Callback<EarthquakeResponse>() {
            @Override
            public void onResponse(Call<EarthquakeResponse> call, Response<EarthquakeResponse> response) {
                if (response.isSuccessful()){
                    EarthquakeResponse earthquakeResponse = response.body();
                    adapter = new DataAdapter(earthquakeResponse.getFeatures());
                    recyclerView.setAdapter(adapter);

                }
                else {
                    Toast.makeText(getApplicationContext(),"No data Found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EarthquakeResponse> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }
}
