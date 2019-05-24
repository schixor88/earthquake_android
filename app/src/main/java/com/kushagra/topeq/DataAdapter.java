package com.kushagra.topeq;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Feature> features;

    public DataAdapter(List<Feature> features1) {
        this.features = features1;
    }


    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.earthquake_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.earthquakeMag.setText(features.get(i).getProperties().getMag().toString());
        viewHolder.earthquakePlace.setText(features.get(i).getProperties().getPlace());
        viewHolder.earthquakeUrl.setText(features.get(i).getProperties().getUrl());
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView earthquakeMag,earthquakePlace,earthquakeUrl;
        public ViewHolder(View view) {
            super(view);

            earthquakeMag = view.findViewById(R.id.earthquake_mag);
            earthquakePlace = view.findViewById(R.id.earthquake_place);
            earthquakeUrl = view.findViewById(R.id.earthquake_url);
        }
    }
}
