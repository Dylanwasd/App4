package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    ArrayList<Person> person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnLogin);
        username = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassword);
        person = new ArrayList<>();
        login.setEnabled(false);
        username.addTextChangedListener(check);
        password.addTextChangedListener(check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                person = dbh.getPerson();
                if(username.getText().toString().equals(person.get(0).getName()) && Integer.parseInt(password.getText().toString()) == person.get(0).getPass()){
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Incorrect Password/Username", Toast.LENGTH_LONG).show();
                }
                dbh.close();

            }
        });


    }
    private TextWatcher check = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String testUsername = username.getText().toString().trim();
            String testPassword = password.getText().toString().trim();

            login.setEnabled(!testUsername.isEmpty() && !testPassword.isEmpty()) ;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
