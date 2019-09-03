/*
TO DO
    Show the linear layout as more rooms are added/Dropped::::
    :::Instead we are going to have a new main menu displaying all the rooms
    Label each room as set by user
    Advance button should open the bright menu
    Allow user to input text from valuedisplay instead of seekbar


 */
package com.example.chishingpoon.try1;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;



public class presetSub extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    //Creating Variables to use for all functions within this class.
    //Might be transferred to MSP
    private int temperatureP, humidityP, brightnessP;
    private int curSet;     //Variable used to keep track on which time is being set out of days of week; 1: Monday, 2: Tuesday, etc...
    private int[] hour = {0,0,0,0,0,0,0,0,0};     //Array to keep user inputted values of hour        ;1: Monday, 2: Tuesday, etc...
    private int[] minutes = {0,0,0,0,0,0,0,0,0};  //Array to keep user inputted values of minutes     ;1: Monday, 2: Tuesday, etc...
    //Used to store if checkboxes are checked
    private int[] daysChecked = {0,0,0,0,0,0,0,0,0};


    //TextView variables to store user scheduled time
    private TextView mTime; //Monday
    private TextView tTime; //Tuesday
    private TextView wTime; //Wednesday
    private TextView rTime; //Thursday
    private TextView fTime; //Friday
    private TextView sTime; //Saturday
    private TextView lTime; //Sunday

    //For writing and reading files
    //private Context context;
    private String fileName = "presetFile.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presets);


//-------------------------------------------------- TOP HALF---------------------------------------

            final TextView roomName = findViewById(R.id.roomTitle);                                     //Current Room Title
            roomName.setText(Menu.room);


            final Button asP = findViewById(R.id.adP);                                                  //Text value for button
            asP.setText("ADVANCE SETTINGS");


            final SeekBar currentTempBar = findViewById(R.id.tBarP);                                    //Text values for temperature, values of temp, and seekbar
            final TextView tempName = findViewById(R.id.tempTop);
            final TextView tempp = findViewById(R.id.Temp);
            tempp.setText("PLACEHLDER" + "\u00B0");
            tempName.setText("Temperature:");
            currentTempBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    tempp.setText(String.valueOf(i) + "\u00B0");
                    temperatureP = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });     //Syncs Seekbar and value display


            final SeekBar currentHumBar = findViewById(R.id.hBarP);                                     //Text value for humidity, values of humidity, and seekbar
            final TextView humidName = findViewById(R.id.humidTop);
            final TextView humid = findViewById(R.id.Humid);
            humid.setText("XXX" + "\u0025");
            humidName.setText("Humidity:");
            currentHumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    humid.setText(String.valueOf(i) + "\u0025");
                    humidityP = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });      //Syncs Seekbar and value display


            final TextView brightTitleP = findViewById(R.id.brightAll);                                 //text values for brightness and its seekbar/values
            final TextView brightValueP = findViewById(R.id.brightP);
            final SeekBar brightBarP = findViewById(R.id.bBarP);

            brightTitleP.setText("Overall Brightness:");
            brightBarP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    brightValueP.setText(String.valueOf(i) + "\u0025");
                    brightnessP = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

//-------------------------------------------------- END -------------------------------------------


            //------------^^^^^OCT 30^^^^^----------------
            //Values for TextViews/CheckBoxes of Schedules
            //Initialize and set variables for schedule title and switch
            final TextView scheduleTitle = findViewById(R.id.schedule);
            scheduleTitle.setText("Schedule");

            //Initialize Checkbox variables
            final CheckBox mon = findViewById(R.id.mBox);
            final CheckBox tue = findViewById(R.id.tBox);
            final CheckBox wed = findViewById(R.id.wBox);
            final CheckBox thu = findViewById(R.id.rBox);
            final CheckBox fri = findViewById(R.id.fBox);
            final CheckBox sat = findViewById(R.id.sBox);
            final CheckBox sun = findViewById(R.id.lBox);


            //Text Displayed for checkboxes
            mon.setText("Monday");
            tue.setText("Tuesday");
            wed.setText("Wednesday");
            thu.setText("Thursday");
            fri.setText("Friday");
            sat.setText("Saturday");
            sun.setText("Sunday");

            //Set Default vales of checkboxes to 0
            mon.setChecked(false);
            tue.setChecked(false);
            wed.setChecked(false);
            thu.setChecked(false);
            fri.setChecked(false);
            sat.setChecked(false);
            sun.setChecked(false);

            //Applying functions for when box is checked
            setBoxTodo(mon, 1);
            setBoxTodo(tue, 2);
            setBoxTodo(wed, 3);
            setBoxTodo(thu, 4);
            setBoxTodo(fri, 5);
            setBoxTodo(sat, 6);
            setBoxTodo(sun, 7);


            //Checking Today's Day
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);

            //For
            mTime = findViewById(R.id.mondayTime);
            tTime = findViewById(R.id.tuesdayTime);
            wTime = findViewById(R.id.wednesdayTime);
            rTime = findViewById(R.id.thursdayTime);
            fTime = findViewById(R.id.fridayTime);
            sTime = findViewById(R.id.saturdayTime);
            lTime = findViewById(R.id.sundayTime);
            //IF statement to check if time is already set
            //If so,
            //Date sundayTime;
            //sTime.setText(String.valueOf(sundayTime);

            //If not
            //Calls setTimeInput
            setTimeInput(mTime, 1);
            setTimeInput(tTime, 2);
            setTimeInput(wTime, 3);
            setTimeInput(rTime, 4);
            setTimeInput(fTime, 5);
            setTimeInput(sTime, 6);
            setTimeInput(lTime, 7);

        //Button press = save the presets values user inputted, and goes back to Room Menu
        final Button SAVE = findViewById(R.id.saveButton);
        SAVE.setText("Save Settings" );
        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writePresetFile(hour, minutes, daysChecked, temperatureP, brightnessP, humidityP);
                startActivity(new Intent(presetSub.this, Menu.class));
                //SAVE.setText(readFile());
            }
        });
        }

        //Displays time after user set
        @Override
        public void onTimeSet (TimePicker view,int hourOfDay, int minute){
            String minuteStr;
            String hourStr;
            //If minute is 0, display "00" instead of a single 0
            if (minute == 0) {
                minuteStr = "00";
            } else if (minute < 10 && minute != 0) {   //If minute is single digit (Ex: 1, 2, 3...) display with 0 in front. (Ex. 01, 02, 03...)
                minuteStr = "0" + minute;
            } else {
                minuteStr = String.valueOf(minute);
            }

            //Allow time is be displayed as "12" if the value of hour is 0 or 12.
            if (hourOfDay == 0 || hourOfDay == 12) {
                hourStr = "12";
            } else if (hourOfDay > 12) {  //If hourOfDay is larger than 12, it has to be subtracted by 12 in order to show time in 12hour format
                hourStr = String.valueOf(hourOfDay - 12);
            } else {
                hourStr = String.valueOf(hourOfDay);
            }
            switch (curSet) {
                case 1:
                    hour[1] = hourOfDay;        //Stores hours and minutes for future usages
                    minutes[1] = minute;        //[1] for values related to monday

                    //making sure the time is formatted in 12 hour views, with AM or PM to clarify if its morning or afternoon
                    if (hourOfDay >= 12) {    //If value of hour[i] is larger than or equal to 12, then it would be subtracted by 12 to prevent 24h view, as well as shown with PM.
                        mTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {                 //If not larger than 12, display as is, and is shown with AM.
                        mTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 2:
                    hour[2] = hourOfDay;
                    minutes[2] = minute;
                    if (hourOfDay >= 12) {
                        tTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        tTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 3:
                    hour[3] = hourOfDay;
                    minutes[3] = minute;
                    if (hourOfDay >= 12) {
                        wTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        wTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 4:
                    hour[4] = hourOfDay;
                    minutes[4] = minute;
                    if (hourOfDay >= 12) {
                        rTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        rTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 5:
                    hour[5] = hourOfDay;
                    minutes[5] = minute;
                    if (hourOfDay >= 12) {
                        fTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        fTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 6:
                    hour[6] = hourOfDay;
                    minutes[6] = minute;
                    if (hourOfDay >= 12) {
                        sTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        sTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;
                case 7:
                    hour[7] = hourOfDay;
                    minutes[7] = minute;
                    if (hourOfDay >= 12) {
                        lTime.setText(hourStr + ":" + minuteStr + "PM");
                    } else {
                        lTime.setText(hourStr + ":" + minuteStr + "AM");
                    }
                    break;

            }

        }

        //Function to set preset all time with the same hint, and to run Time Picker when the time is clicked
        public void setTimeInput (TextView view,final int i){
            view.setText("HOUR: MINUTE");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "time picker");
                    curSet = i;
                }
            });

        }

        //Functions of onClickListeners for checkboxes, used to keep track on which day is checked. Days that are checked are stored in an array as 1;
        //With indexes: 0: null, 1: monday, 2:Tuesday, etc...
        public void setBoxTodo (final CheckBox box, final int i){
            //Default value of daysChecked = 0;
            daysChecked[i] = 0;
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if the box is checked, daysChecked[i] = 1;
                    if(box.isChecked()){
                        daysChecked[i] = 1;
                    }
                }
            });
        }


        //Function for writing the file that will store all the data from this activity
        private void writePresetFile(int[] hour, int[] minutes, int[] daysChecked, int temp, int bright, int humid){
            try {
                //Opening the file
                OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));
                outputStream.close();

                outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));

                //Writing values of Hours, minutes, daysChecked, temp, bright, humid
                outputStream.write("Hours\n");
                for(int i = 1; i <8; i++){
                        outputStream.write(String.valueOf(hour[i]) + " ");
                }
                outputStream.write("\n");

                outputStream.write("Minutes\n");
                for(int i = 1; i <8; i++){
                    outputStream.write(String.valueOf(minutes[i])+ " ");
                }
                outputStream.write("\n");

                outputStream.write("daysChecked\n");
                for(int i = 1; i <8; i++){
                    outputStream.write(String.valueOf(daysChecked[i])+" ");
                }
                outputStream.write("\n");

                outputStream.write("Temperature\n");
                outputStream.write(temp + "\n");

                outputStream.write("Brightness\n");
                outputStream.write((bright) + "\n");

                outputStream.write("Humidity\n");
                outputStream.write((humid) + "\n");

                //Closes the file
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
                e.printStackTrace();
            }
        }

        /*

        //Depending on what day today is, check if the box is checked on that day and what time is inputted.
            switch (day) {
                case Calendar.MONDAY:
                    //Check if box is checked
                    if (mon.isChecked()) {
                        //DO THIS... Send value to MSP
                    } else break;
                case Calendar.TUESDAY:
                    //Check if box is checked
                    if (tue.isChecked()) {
                        //DO THIS
                    } else break;
                case Calendar.WEDNESDAY:
                    //Check if box is checked
                    if (wed.isChecked()) {
                        //DO THIS
                    } else break;
                case Calendar.THURSDAY:
                    //Check if box is checked
                    if (thu.isChecked()) {
                        //DO THIS
                    } else break;
                case Calendar.FRIDAY:
                    //Check if box is checked
                    if (fri.isChecked()) {
                        //DO THIS
                    } else break;
                case Calendar.SATURDAY:
                    //Check if box is checked
                    if (sat.isChecked()) {
                        //DO THIS
                    } else break;
                case Calendar.SUNDAY:
                    //Check if box is checked
                    if (sun.isChecked()) {
                        //DO THIS
                    } else break;
            }
        //FUNC.
        private void writeFile ( int value, int day, String topic){
            try {
                OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_APPEND));
                //Writing values of Hours, minutes, daysChecked, temp, bright, humid
                switch (topic) {
                    case "Temp":
                        outputStream.write(String.valueOf(value));
                        break;
                    case "Humid":
                        outputStream.write(String.valueOf(value));
                        break;
                    case "Bright":
                        outputStream.write(String.valueOf(value));
                        break;
                    case "Hour":
                        outputStream.write(String.valueOf(value), day + 3, 3);
                        break;
                    case "Minutes":
                        outputStream.write(String.valueOf(value), day + 3, 3);
                        break;
                    case "days":
                        outputStream.write(String.valueOf(value), day + 3, 3);
                        break;
                }
                //Closes the file
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
                e.printStackTrace();
            }
        }
        */

    }


            /*


        */
