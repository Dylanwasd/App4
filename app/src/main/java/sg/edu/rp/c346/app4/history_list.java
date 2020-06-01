package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class history_list extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<History> history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        lv = (ListView) this.findViewById(R.id.lvHistory);

        // Create a few food objects in Food array
        DBHelper dbh = new DBHelper(history_list.this);

        history = new ArrayList<History>();
        history.addAll(dbh.getHistory());


        // Link this Activity object, the row.xml layout for
        //  each row and the food String array together
        aa = new HistoryAdapter(this, R.layout.row, history);
        lv.setAdapter(aa);

    }
}
