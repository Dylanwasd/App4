package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class home extends AppCompatActivity {

    LinearLayout taskView;
    LinearLayout rewardsView;
    LinearLayout settingsView;
    LinearLayout historyView;
    TextView tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        taskView = findViewById(R.id.taskView);
        rewardsView = findViewById(R.id.ExchangeView);
        settingsView = findViewById(R.id.SettingsView);
        historyView = findViewById(R.id.HistoryView);
        tvName = findViewById(R.id.tvName);
        Intent intentReceived = getIntent();
        final String name = intentReceived.getStringExtra("name");
        tvName.setText(name);
        final String pass = intentReceived.getStringExtra("pass");
        final int id = intentReceived.getIntExtra("id",0);


        taskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, task_list.class);
                intent.putExtra("password",pass);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rewardsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, rewards.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(home.this, settings.class);
                intent.putExtra("settingsName",name);
                startActivity(intent);
            }
        });
        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, history_list.class);
                startActivity(intent);
            }
        });


        }
    }

