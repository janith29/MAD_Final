package com.example.janith.manage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {

     EditText userName,DOB,userpassword;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
     Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        Register=findViewById(R.id.Register);
        userName=findViewById(R.id.InUsernamePro);
        DOB=findViewById(R.id.InDBOPRo);
        userpassword=findViewById(R.id.InPasswordPro);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName,userpaass,userDBO,genderselect;

                UserName=userName.getText().toString();
                userDBO=DOB.getText().toString();
                userpaass=userpassword.getText().toString();

                radioGroup =  findViewById(R.id.genderPro);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton =findViewById(selectedId);
                genderselect=radioButton.getText().toString();

                DBhelper dBhelper = new DBhelper(ProfileManagement.this);
                long addn = dBhelper.addinfo(UserName, userDBO, genderselect, userpaass);

                if(addn>0) {
                    Toast.makeText(ProfileManagement.this, "Add data", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ProfileManagement.this, "Not insert data", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
