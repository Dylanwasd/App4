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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    ArrayList<Person> person;
    TextView register;
    Boolean found = false;
    String name;
    String pass;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnLogin);
        username = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassword);
        register = findViewById(R.id.registerAccount);
        person = new ArrayList<>();
        login.setEnabled(false);
        username.addTextChangedListener(check);
        password.addTextChangedListener(check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                person = dbh.getPerson();
                found = false;
                for (int i = 0; i<person.size(); i++){
                    if(username.getText().toString().equals(person.get(i).getName()) && Integer.parseInt(password.getText().toString()) == person.get(i).getPass()){
                        found = true;
                        name = username.getText().toString();
                        pass = password.getText().toString();
                        id = person.get(i).getId();
                        Log.w("id", String.valueOf(id));
                    }

                }
                if (found == true){
                    Intent intent = new Intent(MainActivity.this, home.class);
                    intent.putExtra("name",name);
                    intent.putExtra("pass", pass);
                    intent.putExtra("id" , id);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Incorrect Password/Username", Toast.LENGTH_LONG).show();
                }


                dbh.close();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);

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
