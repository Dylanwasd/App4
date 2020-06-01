package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class rewards extends AppCompatActivity {

    TextView rewardPoints;
    ListView lvReward;
    ArrayList<Integer> points;
    ArrayList<Point> pointList;
    ArrayList<Reward> rewards;
    RewardAdapter aar;
    Reward selectReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        lvReward = findViewById(R.id.rewardsList);
        rewardPoints = findViewById(R.id.tvRewardPoints);
        rewards = new ArrayList<>();
        points = new ArrayList<>();
        pointList = new ArrayList<>();
        DBHelper dbh = new DBHelper(rewards.this);
        rewards.addAll(dbh.getReward());
        aar = new RewardAdapter(this, R.layout.reward_row,rewards);
        lvReward.setAdapter(aar);

        points.addAll(dbh.getPointContent());
        rewardPoints.setText(Integer.toString(points.get(0)));
        dbh.close();

        lvReward.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectReward = rewards.get(position);
                dialogClaimRewards();

            }
        });


        lvReward.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectReward = rewards.get(position);
                showDeleteDialog();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addReward:
                dialogAddRewards();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showDeleteDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to remove this reward?")
                .setCancelable(false)
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBHelper dbh = new DBHelper(rewards.this);
                        dbh.deleteReward(selectReward.getId());
                        rewards.clear();
                        rewards.addAll(dbh.getReward());
                        aar.notifyDataSetChanged();

                        dbh.close();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Remove reward");
        alert.show();

    }

    public void dialogAddRewards() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.reward_entry, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.etDialogTask);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.etDialogPoint);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter the reward and allocate the points:")
                .setView(textEntryView)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DBHelper dbh = new DBHelper(rewards.this);
                                String reward = input1.getText().toString();
                                int point = Integer.parseInt(input2.getText().toString());
                                dbh.insertReward(reward,point);
                                rewards.clear();
                                rewards.addAll(dbh.getReward());
                                aar.notifyDataSetChanged();
                                dbh.close();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });
        alert.show();
    }

    public void dialogClaimRewards() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Claim " + selectReward.getReward() + "?")
                .setPositiveButton("Claim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int current = Integer.parseInt(rewardPoints.getText().toString());
                                int cost = selectReward.getPoints();
                                if (current >= cost) {
                                    DBHelper dbh = new DBHelper(rewards.this);
                                    pointList.clear();
                                    pointList.addAll(dbh.getPoint());
                                    Point left = pointList.get(0);
                                    int total = current - cost;
                                    left.setPoints(total);
                                    left.setId(1);
                                    dbh.updatePoint(left);
                                    points.clear();
                                    points.addAll(dbh.getPointContent());
                                    rewardPoints.setText(Integer.toString(points.get(0)));
                                    Date c = Calendar.getInstance().getTime();
                                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    String formattedDate = df.format(c);
                                    dbh.insertHistory(selectReward.getReward(), selectReward.getPoints(), formattedDate);
                                    dbh.close();
                                    Toast.makeText(rewards.this, "Reward claimed", Toast.LENGTH_LONG).show();
                                } else{
                                    Toast.makeText(rewards.this, "Not enough points", Toast.LENGTH_LONG).show();
                                }

                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });
        alert.show();
    }


}
