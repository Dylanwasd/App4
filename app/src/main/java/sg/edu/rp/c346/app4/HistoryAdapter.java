package sg.edu.rp.c346.app4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<History> {
    private ArrayList<History> history;
    private Context context;
    private TextView tvHistory;
    private TextView historyPoints;
    private TextView date;
    public HistoryAdapter(Context context, int resource, ArrayList<History> objects){
        super(context, resource, objects);
        history = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.history_row, parent, false);

        tvHistory = (TextView) rowView.findViewById(R.id.tvHistory);
        historyPoints = (TextView) rowView.findViewById(R.id.tvHistoryPoints);
        date = (TextView) rowView.findViewById(R.id.date);

        History currentHistory = history.get(position);
        historyPoints.setText(Integer.toString(currentHistory.getHistoryPoint()) + " ");
        tvHistory.setText(currentHistory.getHistory());
        date.setText(currentHistory.getDate());
        return rowView;
    }
}


