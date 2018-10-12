package com.example.janith.manage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    private RadioGroup radioGroup;
    String genderEditeProfile=null;
    Button Editeprofile,searchBtn,deleteuser;
    EditText searchIDIN,bithdaySET,passwordSET,usernameSET;
    private RadioButton radioButton;
    DBhelper dBhelper;
    TextView Ingenderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         dBhelper = new DBhelper(EditProfile.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        searchIDIN=findViewById(R.id.searchID);
        Ingenderview=findViewById(R.id.Ingender);
        usernameSET=findViewById(R.id.username);
        bithdaySET=findViewById(R.id.bithday);
        passwordSET=findViewById(R.id.password);
        deleteuser=findViewById(R.id.Deleteuser);

        searchBtn=findViewById(R.id.serch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsearch=searchIDIN.getText().toString();
                if (idsearch == null){
                    Toast.makeText(EditProfile.this,"Please enter a ID",Toast.LENGTH_SHORT).show();
                }
                else {
                    UserProfile.Users users_search = dBhelper.readAllInfor(idsearch);


                    if(users_search == null){
                        Toast.makeText(EditProfile.this,"Please enter a valid ID",Toast.LENGTH_SHORT).show();
                        usernameSET.setText(" ");
                        passwordSET.setText(" ");
                        bithdaySET.setText(" ");
                        Ingenderview.setText(" ");
                    }
                    else{
                        usernameSET.setText(users_search.getUsername());
                        passwordSET.setText(users_search.getPassword());
                        bithdaySET.setText(users_search.getDob());
                        Ingenderview.setText(users_search.getGender());
                    }
                }


                }
        });
        Editeprofile=findViewById(R.id.update);
        Editeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName,userpaass,userDBO,genderselect;

                String IDUPDATE=searchIDIN.getText().toString();
                UserName=usernameSET.getText().toString();
                userDBO=bithdaySET.getText().toString();
                userpaass=passwordSET.getText().toString();

                radioGroup =  findViewById(R.id.genderEdit);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton =findViewById(selectedId);
                genderselect=radioButton.getText().toString();

                DBhelper dBhelper = new DBhelper(EditProfile.this);
                boolean addn = dBhelper.updateInfor(UserName, userDBO, genderselect, userpaass,IDUPDATE);

                if(addn==true) {
                    Toast.makeText(EditProfile.this, "Update data", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(EditProfile.this, "Not update data", Toast.LENGTH_LONG).show();
                }

            }
        });
        deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delete=searchIDIN.getText().toString();
                DBhelper dBhelper = new DBhelper(EditProfile.this);
                int donedelete =dBhelper.deleteInfo(delete);
                if(donedelete==0){
                    Toast.makeText(EditProfile.this, "Not Delete", Toast.LENGTH_LONG).show();

                }
                else{
                    usernameSET.setText(" ");
                    passwordSET.setText(" ");
                    bithdaySET.setText(" ");
                    Ingenderview.setText(" ");
                    Toast.makeText(EditProfile.this, "Done Delete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
