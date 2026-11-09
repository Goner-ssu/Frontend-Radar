package com.radar.app.ui.record.ranking;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radar.app.R;
import com.radar.app.data.DummyData;
import com.radar.app.data.model.Ranking;

import java.util.Collections;
import java.util.List;

public class RankingFragment extends Fragment {

    private boolean sortByMinutes = true; // true = 지각 시간 기준, false = 지각 횟수 기준

    private TextView btnMinutes, btnCount, tvDescription;
    private TextView[] podiumNames = new TextView[3];  // [0]=1위, [1]=2위, [2]=3위
    private TextView[] podiumValues = new TextView[3];
    private RankingAdapter adapter;

    public RankingFragment() {
        super(R.layout.fragment_ranking);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. 화면에 있는 뷰들 찾기
        btnMinutes = view.findViewById(R.id.btn_minutes);
        btnCount = view.findViewById(R.id.btn_count);
        tvDescription = view.findViewById(R.id.tv_description);

        podiumNames[0] = view.findViewById(R.id.tv_podium_name_1);
        podiumNames[1] = view.findViewById(R.id.tv_podium_name_2);
        podiumNames[2] = view.findViewById(R.id.tv_podium_name_3);
        podiumValues[0] = view.findViewById(R.id.tv_podium_value_1);
        podiumValues[1] = view.findViewById(R.id.tv_podium_value_2);
        podiumValues[2] = view.findViewById(R.id.tv_podium_value_3);

        // 2. 리스트 준비
        RecyclerView rvRanking = view.findViewById(R.id.rv_ranking);
        rvRanking.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRanking.setNestedScrollingEnabled(false); // 바깥 ScrollView와 같이 스크롤되게
        adapter = new RankingAdapter();
        rvRanking.setAdapter(adapter);

        // 3. 토글 버튼 클릭
        btnMinutes.setOnClickListener(v -> {
            sortByMinutes = true;
            updateScreen();
        });
        btnCount.setOnClickListener(v -> {
            sortByMinutes = false;
            updateScreen();
        });

        // 4. 처음 화면 그리기
        updateScreen();
    }

    // 현재 기준(sortByMinutes)에 맞게 화면 전체를 다시 그림
    private void updateScreen() {
        // 토글 모양
        if (sortByMinutes) {
            selectToggle(btnMinutes, btnCount);
            tvDescription.setText("지각한 시간을 모두 더한 순위예요 · 전체 기간");
        } else {
            selectToggle(btnCount, btnMinutes);
            tvDescription.setText("지각한 횟수가 많은 순위예요 · 전체 기간");
        }

        // 데이터 정렬 (큰 값이 1위)
        List<Ranking> list = DummyData.getRankingList();
        if (sortByMinutes) {
            Collections.sort(list, (a, b) -> b.getTotalLateMinutes() - a.getTotalLateMinutes());
        } else {
            Collections.sort(list, (a, b) -> b.getLateCount() - a.getLateCount());
        }

        // 시상대 (1~3위)
        for (int i = 0; i < 3; i++) {
            Ranking r = list.get(i);
            podiumNames[i].setText(r.getName());
            podiumValues[i].setText(getValueText(r));
        }

        // 리스트 (4위부터)
        adapter.setItems(list.subList(3, list.size()), sortByMinutes);
    }

    // "87분" 또는 "6회"
    private String getValueText(Ranking r) {
        if (sortByMinutes) {
            return r.getTotalLateMinutes() + "분";
        } else {
            return r.getLateCount() + "회";
        }
    }

    // 선택된 버튼은 흰 배경 + 진한 굵은 글씨, 나머지는 투명 + 회색
    private void selectToggle(TextView selected, TextView unselected) {
        selected.setBackgroundResource(R.drawable.bg_toggle_selected);
        selected.setTextColor(0xFF222222);
        selected.setTypeface(null, Typeface.BOLD);

        unselected.setBackground(null);
        unselected.setTextColor(0xFF999999);
        unselected.setTypeface(null, Typeface.NORMAL);
    }
}
