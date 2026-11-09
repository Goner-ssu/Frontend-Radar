package com.radar.app.ui.record.past;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.model.Appointment;
import com.radar.app.data.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class PastAppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CARD = 1;

    public static class MonthHeader {
        int month;
        int count;

        MonthHeader(int month) {
            this.month = month;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Appointment appointment);
    }

    private final List<Object> items = new ArrayList<>();
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setAppointments(List<Appointment> appointments) {
        items.clear();

        int currentMonth = -1;
        MonthHeader currentHeader = null;

        for (Appointment a : appointments) {
            if (a.getMonth() != currentMonth) {
                currentMonth = a.getMonth();
                currentHeader = new MonthHeader(currentMonth);
                items.add(currentHeader);
            }
            currentHeader.count++;
            items.add(a);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof MonthHeader) {
            return TYPE_HEADER;
        } else {
            return TYPE_CARD;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.item_month_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_past_appointment, parent, false);
            return new CardViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            bindHeader((HeaderViewHolder) holder, (MonthHeader) items.get(position));
        } else {
            bindCard((CardViewHolder) holder, (Appointment) items.get(position));
        }
    }

    private void bindHeader(HeaderViewHolder holder, MonthHeader header) {
        holder.tvMonth.setText(header.month + "월");
        holder.tvCount.setText("약속 " + header.count + "회");
    }

    private void bindCard(CardViewHolder holder, Appointment a) {
        holder.tvTitle.setText(a.getTitle());
        holder.tvDatePlace.setText(a.getDateTimePlace());

        int lateCount = a.getLateCount();
        if (lateCount > 0) {
            holder.tvResult.setText("지각 " + lateCount + "명");
            holder.tvResult.setBackgroundResource(R.drawable.bg_chip_late);
            holder.tvResult.setTextColor(0xFFFFFFFF);
        } else {
            holder.tvResult.setText("정시 도착");
            holder.tvResult.setBackgroundResource(R.drawable.bg_chip_ontime);
            holder.tvResult.setTextColor(0xFF222222);
        }

        holder.layoutParticipants.removeAllViews();
        Context context = holder.itemView.getContext();
        int size = dpToPx(context, 24);
        int gap = dpToPx(context, 4);

        for (Participant p : a.getParticipants()) {
            View dot = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMarginEnd(gap);
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.bg_avatar);
            holder.layoutParticipants.addView(dot);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(a);
            }
        });
    }

    private int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonth, tvCount;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonth = itemView.findViewById(R.id.tv_month);
            tvCount = itemView.findViewById(R.id.tv_count);
        }
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvResult, tvDatePlace;
        LinearLayout layoutParticipants;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvResult = itemView.findViewById(R.id.tv_result);
            tvDatePlace = itemView.findViewById(R.id.tv_date_place);
            layoutParticipants = itemView.findViewById(R.id.layout_participants);
        }
    }
}
