package com.example.daoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daoapp.R;
import com.example.daoapp.entity.Ruangan;

import java.util.List;

public class RuanganAdapter extends RecyclerView.Adapter<RuanganAdapter.ViewHolder> {
    private List<Ruangan> ruangans;
    private RuanganListener editListener;
    private RuanganListener deleteListener;

    public RuanganAdapter(List<Ruangan> ruangans, RuanganListener editListener, RuanganListener deleteListener) {
        this.ruangans = ruangans;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View photoView = inflater.inflate(R.layout.item_ruangan, parent, false);

        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String namaRuangan = ruangans.get(position).getNamaRuangan();
        int kapasitas = ruangans.get(position).getKapasitas();

        holder.tvRuangan.setText(namaRuangan);
        holder.kapasitas.setText(String.valueOf(kapasitas));
        holder.ivEdit.setOnClickListener(v -> editListener.onClick(position,v));
        holder.ivDelete.setOnClickListener(v -> deleteListener.onClick(position,v));
    }

    @Override
    public int getItemCount() {
        return ruangans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRuangan, kapasitas;
        private ImageView ivEdit, ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvRuangan = itemView.findViewById(R.id.tvRuangan);
            kapasitas = itemView.findViewById(R.id.tvKapasitas);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface RuanganListener {
        void onClick(int position, View view);
    }
}

