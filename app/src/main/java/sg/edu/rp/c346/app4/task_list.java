package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class task_list extends AppCompatActivity {

    TextView point;
    ListView lvTask;
    ArrayList<Task> tasks;
    ArrayList<Integer> points;
    ArrayList<Point> pointList;
    ArrayList<Integer> pass;
    TaskAdapter aa;
    Task selectedTask;
    String password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        lvTask = findViewById(R.id.taskList);
        point = findViewById(R.id.tvPoints);
        DBHelper dbh = new DBHelper(task_list.this);
        tasks = new ArrayList<>();
        points = new ArrayList<>();
        pointList = new ArrayList<>();
        pass = new ArrayList<>();

        tasks.addAll(dbh.getTask());
        aa = new TaskAdapter(this, R.layout.row,tasks);
        lvTask.setAdapter(aa);

        Intent intentReceived = getIntent();
        password = intentReceived.getStringExtra("password");
        id = intentReceived.getIntExtra("id",0);

        points.addAll(dbh.getPointContent());
        point.setText(Integer.toString(points.get(id-1)));
        dbh.close();
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTask = tasks.get(position);
                dialogCompleteTask();

            }
        });


        lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTask = tasks.get(position);
                showDeleteDialog();
                return true;
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Add:
                dialogAddTasks();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void showDeleteDialog(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to delete this task?")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBHelper dbh = new DBHelper(task_list.this);
                        dbh.deleteTask(selectedTask.getId());
                        tasks.clear();
                        tasks.addAll(dbh.getTask());
                        aa.notifyDataSetChanged();

                        dbh.close();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Delete Task");
        alert.show();

    }
    public void dialogAddTasks(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.text_entry, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.etDialogTask);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.etDialogPoint);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter the task and allocate the points:")
                .setView(textEntryView)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DBHelper dbh = new DBHelper(task_list.this);
                                String task = input1.getText().toString();
                                int point = Integer.parseInt(input2.getText().toString());
                                dbh.insertTask(task,point);
                                tasks.clear();
                                tasks.addAll(dbh.getTask());
                                aa.notifyDataSetChanged();
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
    public void dialogCompleteTask() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.complete_task, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.etDialogPassword);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter password to complete the task")
                .setView(textEntryView)
                .setPositiveButton("Complete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DBHelper dbh = new DBHelper(task_list.this);
                                if(input1.getText().toString().equals(password)){
                                    pointList.clear();
                                    pointList.addAll(dbh.getPoint());
                                    Point left = pointList.get(id-1);
                                    int current = Integer.parseInt(point.getText().toString());
                                    int total = current + selectedTask.getPoint();
                                    left.setPoints(total);
                                    left.setId(id);
                                    dbh.updatePoint(left);
                                    dbh.deleteTask(selectedTask.getId());

                                    points.clear();
                                    points.addAll(dbh.getPointContent());
                                    point.setText(Integer.toString(points.get(id-1)));
                                    tasks.clear();
                                    tasks.addAll(dbh.getTask());
                                    aa.notifyDataSetChanged();
                                    dbh.close();
                                }else{
                                    Toast.makeText(task_list.this,"Incorrect password", Toast.LENGTH_LONG).show();
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
