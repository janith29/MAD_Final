package com.example.janith.manage.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janith.manage.R;
import com.example.janith.manage.database.DBhelper;
import com.example.janith.manage.database.UserProfile;

public class EditProfile extends AppCompatActivity {
    private RadioGroup radioGroup;
    String genderEditeProfile=null;
    Button Editeprofile,searchBtn,deleteuser;
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
        deleteuser=findViewById(R.id.Deleteuser);

        radioGroup =  findViewById(R.id.genderEdit);
        searchBtn=findViewById(R.id.serch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsearch=searchIDIN.getText().toString();
                if (idsearch.isEmpty()){
                    Toast.makeText(EditProfile.this,"Please enter a ID",Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor cursor=dBhelper.readAllInfor(idsearch);
                    if(cursor.getCount()==0){
                        usernameSET.setText(" ");
                        passwordSET.setText(" ");
                        bithdaySET.setText(" ");
                        radioGroup.clearCheck();
                        Toast.makeText(EditProfile.this,"Please enter a valid ID",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        while (cursor.moveToNext()) {
                            usernameSET.setText(cursor.getString(1));
                            passwordSET.setText(cursor.getString(2));
                            bithdaySET.setText(cursor.getString(3));
                            if (cursor.getString(4).equals("Male")) {
                                radioGroup.check(R.id.male);
                            } else if (cursor.getString(4).equals("Female"))
                                radioGroup.check(R.id.female);
                        }
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
                    radioGroup.clearCheck();
                    Toast.makeText(EditProfile.this, "Done Delete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
