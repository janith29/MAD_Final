package com.example.janith.manage.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.janith.manage.R;
import com.example.janith.manage.database.DBhelper;

public class Home extends AppCompatActivity {

    EditText usernameChek,passwordChek;
    Button login1,register1;
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dBhelper = new DBhelper(Home.this);
        login1=findViewById(R.id.login);
        register1=findViewById(R.id.register);
        usernameChek=findViewById(R.id.Username);
        passwordChek=findViewById(R.id.Password);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameChekString,passwordChekString;
                usernameChekString=usernameChek.getText().toString();
                passwordChekString=passwordChek.getText().toString();
               long chek= dBhelper.checkUser(usernameChekString,passwordChekString);
                if(chek>-1)
                {
                    startActivity(new Intent(Home.this,EditProfile.class));
                }
                else
                {
                    Toast.makeText(Home.this, "Login not successful", Toast.LENGTH_SHORT).show();
                }


            }
        });

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,ProfileManagement.class);
                startActivity(intent);


            }
        });
    }
}
