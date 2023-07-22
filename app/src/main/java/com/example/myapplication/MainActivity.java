package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Seller Selr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.cirLoginButton);
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        String username = email.getText().toString();
        String pass = password.getText().toString();
        final UsersDBHelper newUser = new UsersDBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View view) {
                String username = email.getText().toString();
                String pass = password.getText().toString();
                if (!(email.getText().toString().equals("") || email.getText().toString().equals(""))) {
                    Boolean check = newUser.checkPassword(username, pass);
                        if (check == true) {
                            Toast.makeText(MainActivity.this, "Login Successed", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(MainActivity.this, "Please renter your data", Toast.LENGTH_SHORT).show();
                        }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Login(View view) {

    }

    public void onLoginClickToRegister(View view) {

    }

    public void ForgotPass(View view) {

    }

    public void onLoginClick1(View view) {
        startActivity(new Intent(this,Seller.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }


}