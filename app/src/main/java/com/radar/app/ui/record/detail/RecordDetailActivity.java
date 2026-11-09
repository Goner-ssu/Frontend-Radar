package com.radar.app.ui.record.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.DummyData;
import com.radar.app.data.model.Appointment;
import com.radar.app.data.model.Participant;

public class RecordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_APPOINTMENT_ID = "appointment_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        int id = getIntent().getIntExtra(EXTRA_APPOINTMENT_ID, -1);
        Appointment appointment = DummyData.findAppointmentById(id);
        if (appointment == null) {
            finish();
            return;
        }

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvDatePlace = findViewById(R.id.tv_date_place);
        TextView tvStatTotal = findViewById(R.id.tv_stat_total);
        TextView tvStatOntime = findViewById(R.id.tv_stat_ontime);
        TextView tvStatLate = findViewById(R.id.tv_stat_late);

        tvTitle.setText(appointment.getTitle());
        tvDatePlace.setText(appointment.getDateTimePlace());
        tvStatTotal.setText(String.valueOf(appointment.getParticipants().size()));
        tvStatOntime.setText(String.valueOf(appointment.getOnTimeCount()));
        tvStatLate.setText(String.valueOf(appointment.getLateCount()));

        LinearLayout layoutParticipants = findViewById(R.id.layout_participants);
        int size = dpToPx(40);
        int gap = dpToPx(10);
        for (Participant p : appointment.getParticipants()) {
            View dot = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMarginEnd(gap);
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.bg_avatar);
            layoutParticipants.addView(dot);
        }

        RecyclerView rvArrival = findViewById(R.id.rv_arrival);
        rvArrival.setLayoutManager(new LinearLayoutManager(this));
        rvArrival.setNestedScrollingEnabled(false);
        rvArrival.setAdapter(new ArrivalAdapter(appointment.getParticipants()));
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
