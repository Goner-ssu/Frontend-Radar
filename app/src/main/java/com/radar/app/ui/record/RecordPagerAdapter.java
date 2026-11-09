package com.radar.app.ui.record;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.radar.app.ui.record.past.PastAppointmentFragment;
import com.radar.app.ui.record.ranking.RankingFragment;
public class RecordPagerAdapter extends FragmentStateAdapter {

    public RecordPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new RankingFragment();
        } else {
            return new PastAppointmentFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}