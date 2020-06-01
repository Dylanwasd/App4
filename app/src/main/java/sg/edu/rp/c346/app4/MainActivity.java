package sg.edu.rp.c346.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnLogin);
        username = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassword);
        login.setEnabled(false);
        username.addTextChangedListener(check);
        password.addTextChangedListener(check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, home.class);
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
