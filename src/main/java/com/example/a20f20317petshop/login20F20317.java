package com.example.a20f20317petshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login20F20317 extends AppCompatActivity {

    EditText inputid, inputpass;
    Button clicklog;
    RegisteringDB20F20317 RDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login20_f20317);

        clicklog = findViewById(R.id.Loginb);
        inputid = findViewById(R.id.id);
        inputpass = findViewById(R.id.password);
        RDB = new RegisteringDB20F20317(this);
        getpassword();
    }

    public void getpassword() {
        clicklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = inputid.getText().toString();
                String userpass = inputpass.getText().toString();

                // Check if fields are empty
                if (userid.isEmpty() || userpass.isEmpty()) {
                    Toast.makeText(login20F20317.this, "Please enter both ID and Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Admin credentials check
                if (userid.equals("ADMIN") && userpass.equals("1234")) {
                    Intent intent = new Intent(login20F20317.this, admin20F20317.class);
                    startActivity(intent);
                } else {
                    // customer login check
                    try {
                        String custip = RDB.getpassword(userid);

                        // Check if password is correct
                        if (custip != null && userpass.equals(custip)) {
                            Toast.makeText(login20F20317.this, "Welcome to Pet Shop", Toast.LENGTH_SHORT).show();
                            inputid.setText("");
                            inputpass.setText("");
                            Intent intent = new Intent(login20F20317.this, customerp.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login20F20317.this, "Wrong ID or Password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(login20F20317.this, "Cannot Login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

