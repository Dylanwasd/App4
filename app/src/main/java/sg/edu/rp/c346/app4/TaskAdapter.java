package sg.edu.rp.c346.app4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task> tasks;
    private Context context;
    private TextView task;
    private TextView point;
    public TaskAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        tasks = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        task = (TextView) rowView.findViewById(R.id.tvTask);
        point = (TextView) rowView.findViewById(R.id.tvPoints);

        Task currentTask = tasks.get(position);
        // Set the TextView to show the food
        point.setText(Integer.toString(currentTask.getPoint()) + " ");
        task.setText(currentTask.getTask());
        // Set the image to star or nostar accordingly
        // Return the nicely done up View to the ListView
        return rowView;
    }

}
