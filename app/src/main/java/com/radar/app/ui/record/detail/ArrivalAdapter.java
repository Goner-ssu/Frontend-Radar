package com.radar.app.ui.record.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.model.Participant;

import java.util.List;

public class ArrivalAdapter extends RecyclerView.Adapter<ArrivalAdapter.ViewHolder> {

    private final List<Participant> items;

    public ArrivalAdapter(List<Participant> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_arrival, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participant p = items.get(position);

        holder.tvOrder.setText(String.valueOf(position + 1));
        holder.tvName.setText(p.getName());
        holder.tvArrival.setText(p.getArrivalText());
        holder.tvResult.setText(p.isLate() ? "지각" : "정시 도착");

        if (p.isMe()) {
            holder.root.setBackgroundResource(R.drawable.bg_rank_row_me);
            holder.tvOrder.setTextColor(0xFFFFFFFF);
            holder.tvName.setTextColor(0xFFFFFFFF);
            holder.tvArrival.setTextColor(0xCCFFFFFF);
            holder.tvResult.setBackgroundResource(R.drawable.bg_chip_white);
            holder.tvResult.setTextColor(0xFFE65100);
        } else {
            holder.root.setBackgroundResource(R.drawable.bg_rank_row);
            holder.tvOrder.setTextColor(0xFF222222);
            holder.tvName.setTextColor(0xFF222222);
            holder.tvArrival.setTextColor(0xFF888888);
            if (p.isLate()) {
                holder.tvResult.setBackgroundResource(R.drawable.bg_chip_late);
                holder.tvResult.setTextColor(0xFFFFFFFF);
            } else {
                holder.tvResult.setBackgroundResource(R.drawable.bg_chip_ontime);
                holder.tvResult.setTextColor(0xFF222222);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView tvOrder, tvName, tvArrival, tvResult;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.row_root);
            tvOrder = itemView.findViewById(R.id.tv_order);
            tvName = itemView.findViewById(R.id.tv_name);
            tvArrival = itemView.findViewById(R.id.tv_arrival);
            tvResult = itemView.findViewById(R.id.tv_result);
        }
    }
}
