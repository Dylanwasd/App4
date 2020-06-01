package sg.edu.rp.c346.app4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RewardAdapter extends ArrayAdapter<Reward> {
    private ArrayList<Reward> rewards;
    private Context context;
    private TextView reward;
    private TextView rewardPoint;
    public RewardAdapter(Context context, int resource, ArrayList<Reward> objects){
        super(context, resource, objects);
        rewards = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.reward_row, parent, false);

        reward = (TextView) rowView.findViewById(R.id.tvReward);
        rewardPoint = (TextView) rowView.findViewById(R.id.tvRewardPoints);

        Reward currentTask = rewards.get(position);
        // Set the TextView to show the food
        rewardPoint.setText(Integer.toString(currentTask.getPoints()) + " ");
        reward.setText(currentTask.getReward());
        // Set the image to star or nostar accordingly
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
