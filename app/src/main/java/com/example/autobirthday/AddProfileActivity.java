package com.example.autobirthday;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AddProfileActivity extends AppCompatActivity {


    private EditText profileNameEdt, profilePhoneNumberEdt, profileMessageEdt;
    private DatePicker profileBirthdayDateDatePicker;
    private TimePicker profileTimeTimePicker;
    private Switch profileDefaultHourSwitch,profileDefaultMessageSwitch;
    private Button buttonCreate;
    private DBHandler dbHandler;

    private boolean is24HView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        getSupportActionBar().hide();

        // initializing all our variables.
        this.profileNameEdt = findViewById(R.id.idEdtName);
        this.profilePhoneNumberEdt = findViewById(R.id.idEdtPhoneNumber);
        this.profileBirthdayDateDatePicker = (DatePicker) findViewById(R.id.idDatePickerBirthday);
        this.profileTimeTimePicker = (TimePicker)findViewById(R.id.idTimePickerSendingHour);
        profileTimeTimePicker.setIs24HourView(this.is24HView);
        this.profileDefaultHourSwitch = findViewById(R.id.idSwitchRandomHour);
        this.profileDefaultMessageSwitch = findViewById(R.id.idSwitchMessage);
        this.profileMessageEdt = findViewById(R.id.idEdtMessage);


        buttonCreate = findViewById(R.id.btn_create_profile);

        dbHandler = new DBHandler(AddProfileActivity.this);


        profileDefaultMessageSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(profileDefaultMessageSwitch.isChecked()){
                    profileMessageEdt.setFocusable(true);
                    profileMessageEdt.setText("");
                }
                else{
                    profileMessageEdt.setFocusable(false);
                    profileMessageEdt.setText("Bon anniversaire \uD83C\uDF89");
                }

            }
        });



        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String profileName = profileNameEdt.getText().toString();
                String profilePhoneNumber = profilePhoneNumberEdt.getText().toString();
                String profileMesage = profileMessageEdt.getText().toString();

                int year = profileBirthdayDateDatePicker.getYear();
                int month = profileBirthdayDateDatePicker.getMonth(); // 0 - 11
                int day = profileBirthdayDateDatePicker.getDayOfMonth();
                int hour = profileTimeTimePicker.getHour();
                int minute = profileTimeTimePicker.getMinute();

                if(profileName.isEmpty()||profilePhoneNumber.isEmpty())
                Toast.makeText(AddProfileActivity.this, "Hour"+hour, Toast.LENGTH_SHORT).show();
                LocalDateTime Date = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Date = LocalDateTime.of(year,month,day,hour,minute);
                }


                dbHandler.addProfile(profileName,profilePhoneNumber,Date);
            }
        });
    }

    public boolean isFormValid(){
        boolean temp = false;




        return temp;
    }








    }


