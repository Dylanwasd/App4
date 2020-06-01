package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class settings extends AppCompatActivity {

    LinearLayout logout;
    LinearLayout change;
    ArrayList<Integer> password;
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logout = findViewById(R.id.signOutView);
        change = findViewById(R.id.changePasswordView);
        password = new ArrayList<>();
        people = new ArrayList<>();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, MainActivity.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

    }
    public void changePassword(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.password_entry, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.etDialogTask);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.etDialogPoint);
        final EditText input3 = (EditText) textEntryView.findViewById(R.id.etDialogPassword2);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter the task and allocate the points:")
                .setView(textEntryView)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DBHelper dbh = new DBHelper(settings.this);
                                password = dbh.getPassword();
                                int oldPass = Integer.parseInt(input1.getText().toString());
                                int newPass = Integer.parseInt(input2.getText().toString());
                                int confirmNewPass = Integer.parseInt(input3.getText().toString());
                                if(oldPass == password.get(0)){
                                    if(newPass == confirmNewPass){
                                        people.clear();
                                        people.addAll(dbh.getPerson());
                                        Person user = people.get(0);
                                        user.setPass(newPass);
                                        dbh.updatePassword(user);
                                        dbh.close();
                                        Toast.makeText(settings.this, "Password changed", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(settings.this, "New Password do not match", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(settings.this, "Old Password Incorrect", Toast.LENGTH_SHORT).show();
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
