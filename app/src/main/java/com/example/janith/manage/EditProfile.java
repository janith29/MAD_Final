package com.example.janith.manage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    private RadioGroup radioGroup;
    String genderEditeProfile=null;
    Button Editeprofile,searchBtn;
    EditText searchIDIN,bithdaySET,passwordSET,usernameSET;
    private RadioButton radioButton;
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         dBhelper = new DBhelper(EditProfile.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        searchIDIN=findViewById(R.id.searchID);
        usernameSET=findViewById(R.id.username);
        bithdaySET=findViewById(R.id.bithday);
        passwordSET=findViewById(R.id.password);

        searchBtn=findViewById(R.id.serch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsearch=searchIDIN.getText().toString();
                if (idsearch == null){
                    Toast.makeText(EditProfile.this,"Please enter a username",Toast.LENGTH_SHORT).show();
                }
                else {
                    UserProfile.Users users_search = dBhelper.readAllInfor(idsearch);


                    if(users_search == null){
                        Toast.makeText(EditProfile.this,"Please enter a valid username",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        usernameSET.setText(users_search.getUsername());
                        passwordSET.setText(users_search.getPassword());
                        bithdaySET.setText(users_search.getDob());

                    }
                }


                }
        });
        Editeprofile=findViewById(R.id.update);
        Editeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
