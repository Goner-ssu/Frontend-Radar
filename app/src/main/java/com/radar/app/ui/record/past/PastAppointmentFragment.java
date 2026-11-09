package com.radar.app.ui.record.past;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.DummyData;

public class PastAppointmentFragment extends Fragment {

    public PastAppointmentFragment() {
        super(R.layout.fragment_past_appointment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPast = view.findViewById(R.id.rv_past);
        rvPast.setLayoutManager(new LinearLayoutManager(getContext()));

        PastAppointmentAdapter adapter = new PastAppointmentAdapter();
        rvPast.setAdapter(adapter);

        adapter.setAppointments(DummyData.getPastAppointments());
    }
}
