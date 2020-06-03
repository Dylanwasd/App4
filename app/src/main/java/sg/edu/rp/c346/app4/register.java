package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class register extends AppCompatActivity {

    EditText name;
    EditText password;
    EditText confirmPassword;
    Button register;
    ArrayList<Person> person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        person = new ArrayList<>();
        name = findViewById(R.id.editTextName);
        password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editConfirmPassword);
        register = findViewById(R.id.btnRegister);
        register.setEnabled(false);
        name.addTextChangedListener(check);
        password.addTextChangedListener(check);
        confirmPassword.addTextChangedListener(check);
        DBHelper dbh = new DBHelper(register.this);
        person = dbh.getPerson();
        dbh.close();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check  = true;
                for(int i = 0; i<person.size(); i++){
                    if(name.getText().toString().equals(person.get(i).getName())){
                        check = false;
                    }
                }
                if(check == true){
                    int userPassword = Integer.parseInt(password.getText().toString());
                    int userConfirmPassword = Integer.parseInt(confirmPassword.getText().toString());
                    if (userPassword == userConfirmPassword){
                        DBHelper dbh = new DBHelper(register.this);
                        dbh.insertPerson(name.getText().toString(), userPassword);
                        dbh.insertPoint(0);
                        dbh.close();
                        Toast.makeText(register.this, "Account registered.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(register.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(register.this, "The passwords do not match.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(register.this, "Username is already used",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private TextWatcher check = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String testUsername = name.getText().toString().trim();
            String testPassword = String.valueOf(password.getText().toString().trim());
            String confirm = String.valueOf(confirmPassword.getText().toString().trim());

            register.setEnabled(!testUsername.isEmpty() && !testPassword.isEmpty() && !confirm.isEmpty()) ;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
