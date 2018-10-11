package com.example.janith.manage;

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
    EditText searchIDIN,usernameS;
    private RadioButton radioButton;
    List data= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final DBhelper dbHe=null;
        searchIDIN=findViewById(R.id.searchID);
        searchBtn=findViewById(R.id.serch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsearch=searchIDIN.getText().toString();

                data=dbHe.readAllInfo(idsearch);
                usernameS.setText((CharSequence) data);


            }
        });
        Editeprofile=findViewById(R.id.update);
        Editeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListenerOnButton();
            }
        });
    }
    public void addListenerOnButton() {


        Editeprofile = (Button) findViewById(R.id.update);

        Editeprofile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                radioGroup =  findViewById(R.id.genderSd);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                genderEditeProfile=radioButton.getText().toString();

                Toast.makeText(EditProfile.this,
                        genderEditeProfile, Toast.LENGTH_SHORT).show();

            }

        });

    }


}
