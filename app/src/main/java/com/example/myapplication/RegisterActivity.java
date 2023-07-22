package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        changeStatusBarColor();
        CheckBox type;
        final UsersDBHelper newUser = new UsersDBHelper(this);
        Button Reg = (Button) findViewById(R.id.cirRegisterButton);
        final EditText name = (EditText) findViewById(R.id.editTextName);
        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText phone = (EditText) findViewById(R.id.editTextPhone);
        final EditText pass = (EditText) findViewById(R.id.editTextPassword);
        type=findViewById(R.id.checkBox);



        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checked ;
                if(type.isChecked()){
                    checked = "1";
                }
                else
                    checked = "0";
                if(!(name.getText().toString().equals("") || email.getText().toString().equals("")||phone.getText().toString().equals("")||pass.getText().toString().equals(""))){
                    newUser.createNewUser(name.getText().toString(),
                            email.getText().toString(),
                            pass.getText().toString(),
                            checked.toString(),
                            phone.getText().toString());
                    Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void changeStatusBarColor() {
        Context context = getBaseContext();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.TRANSPARENT);
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.register_bk_color));
    }


    public void onLoginClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
