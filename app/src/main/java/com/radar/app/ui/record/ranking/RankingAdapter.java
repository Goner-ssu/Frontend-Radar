package com.radar.app.ui.record.ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.model.Ranking;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<Ranking> items = new ArrayList<>();
    private boolean showMinutes = true; // true면 "분", false면 "회"로 보여줌
    private int startRank = 4;          // 리스트는 4위부터 시작

    // 새 데이터로 리스트를 다시 그림
    public void setItems(List<Ranking> items, boolean showMinutes) {
        this.items = items;
        this.showMinutes = showMinutes;
        notifyDataSetChanged();
    }

    // 한 줄 화면(item_ranking.xml)을 만들어줌
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    // position번째 줄에 데이터를 채워줌
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ranking item = items.get(position);

        holder.tvRank.setText(String.valueOf(startRank + position));
        holder.tvName.setText(item.getName());

        if (showMinutes) {
            holder.tvValue.setText(item.getTotalLateMinutes() + "분");
        } else {
            holder.tvValue.setText(item.getLateCount() + "회");
        }

        // 내 줄이면 주황 배경 + 흰 글씨, 아니면 기본
        // (줄이 재사용되기 때문에 else도 꼭 써줘야 색이 꼬이지 않음)
        int textColor;
        if (item.isMe()) {
            holder.root.setBackgroundResource(R.drawable.bg_rank_row_me);
            textColor = 0xFFFFFFFF;
        } else {
            holder.root.setBackgroundResource(R.drawable.bg_rank_row);
            textColor = 0xFF222222;
        }
        holder.tvRank.setTextColor(textColor);
        holder.tvName.setTextColor(textColor);
        holder.tvValue.setTextColor(textColor);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 한 줄 안에 있는 뷰들을 담아두는 상자
    static class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView tvRank, tvName, tvValue;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.row_root);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvValue = itemView.findViewById(R.id.tv_value);
        }
    }
}
