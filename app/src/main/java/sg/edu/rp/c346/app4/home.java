package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    LinearLayout taskView;
    LinearLayout rewardsView;
    LinearLayout settingsView;
    LinearLayout historyView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        taskView = findViewById(R.id.taskView);
        rewardsView = findViewById(R.id.ExchangeView);
        settingsView = findViewById(R.id.SettingsView);
        historyView = findViewById(R.id.HistoryView);

        taskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, task_list.class);
                startActivity(intent);
            }
        });
        rewardsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, rewards.class);
                startActivity(intent);
            }
        });



    }
}
