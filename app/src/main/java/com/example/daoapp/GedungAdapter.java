package com.example.daoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daoapp.entity.Gedung;

import java.util.List;

public class GedungAdapter extends RecyclerView.Adapter<GedungAdapter.ViewHolder> {
    private List<Gedung> gedungs;

    public GedungAdapter(List<Gedung> gedungs) {
        this.gedungs = gedungs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View photoView = inflater.inflate(R.layout.item_gedung, parent, false);

        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String namaGedung = gedungs.get(position).getNamaGedung();
        String prodi = gedungs.get(position).getProdi();

        holder.tvGedung.setText(namaGedung);
        holder.tvProdi.setText(prodi);

    }

    @Override
    public int getItemCount() {
        return gedungs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGedung;
        private TextView tvProdi;
        private ImageView ivMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGedung = itemView.findViewById(R.id.tvGedung);
            tvProdi = itemView.findViewById(R.id.tvProdi);
            ivMore = itemView.findViewById(R.id.ivMore);
        }
    }
}
