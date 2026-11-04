package com.radar.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PromiseNameActivity extends AppCompatActivity {

    EditText etName;
    Button btnNext;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promise_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), systemBars.top, v.getPaddingRight(), systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        // 1단계라서 첫번째 칸만 주황색
        findViewById(R.id.bar1).setBackgroundResource(R.drawable.bar_on);

        // 수정하러 다시 들어왔을 때 원래 이름 보여주기
        etName.setText(PromiseData.name);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(PromiseNameActivity.this, "약속 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                PromiseData.name = name;

                // TODO 시간 화면 만들면 넘어가게 하기
                Toast.makeText(PromiseNameActivity.this, "저장됨: " + PromiseData.name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
