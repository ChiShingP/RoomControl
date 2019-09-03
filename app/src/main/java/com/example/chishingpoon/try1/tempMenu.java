/*
    PROGRAMMER  :   CHI SHING POON
    PROJECT     :   Junior Design EE3140
    PURPOSE     :   To create an application which will control various settings of a room control system.
    ----------------------------------------------------
    This page contains the code for a submenu to control temperature and humidity settings. 10/10/2018

    ----------------------------------------------------
    DEVELOPMENT NOTE:
    DATE:       10/10/2018 1:17AM
    PURPOSE:    Implemented new activity, check XML for elements created. Created functions to show
                SeekBar Values
    NOTE:       Bar values do not show up before any user inputs.

    DATE:       10/11/2018 1:17AM
    PURPOSE:    Implemented new activity, check XML for elements created. Created functions to show
                SeekBar Values
    NOTE:       No unit displayed for currentVals. Need to make progressbar paused. Make desire
                values stay the same even after reopening app

    DATE:       10/16/2018 10/17/2018
    PURPOSE:    Implemented units for temp and humidity
    NOTE:       Need to make progressbar paused. Make desire values stay the same even after
                reopening app, Unit in Temp(X) needs to be able to change depending on User input.
                Research Global Variable

    10/30
    Added Switches,
    Should gray out desired conditions for whichever is turned off

    FINAL TO DO
        Switches Needs to Do Something
        Values need to be saved
        Values need to be set for actual control
        Bar needs to be paused
        RED if alerts
 */

package com.example.chishingpoon.try1;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class tempMenu extends AppCompatActivity {
    private String fileName = "tempHumidMenu.txt";
    private int tempTSet;
    private int humidTSet;
    private boolean humOn = true;
    private boolean tempOn = true;
    String tmp;
    ledControl Msg = new ledControl();

    public static int currentT, currentH;

    //Layout View
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_sub);

        currentT = 75;  //Mock Temp. Value
        currentH = 20;

        //Labeling the on/off switches
        final TextView roomName = findViewById(R.id.roomTitle);
        roomName.setText(Menu.room );
        final TextView tempSwitchTitle = findViewById(R.id.tempCon);
        tempSwitchTitle.setText("Temperature Control");

        final Switch tempSwitch = findViewById(R.id.tempSwitch);
        tempSwitch.setText("OFF/ON");
        tempSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tempOn = b;
                writeFile(tempTSet, humidTSet, tempOn, humOn);
                if (tempSwitch.isChecked()){
                    tmp = "Ton";
                } else {
                    tmp = "Toff";
                }

                byte[] bytes = tmp.getBytes();
                Msg.send(bytes);
                //test.setText(readFile(fileName));

            }
        });


        final TextView humSwitchTitle = findViewById(R.id.HumCon);
        humSwitchTitle.setText("Humidity Control");

        final Switch humSwitch = findViewById(R.id.humSwitch);
        humSwitch.setText("OFF/ON");
        humSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                humOn = b;

                writeFile(tempTSet, humidTSet, tempOn, humOn);

                if (humSwitch.isChecked()){
                    tmp = "Hon";
                } else {
                    tmp = "Hoff";
                }

                byte[] bytes = tmp.getBytes();
                Msg.send(bytes);
            }
        });


        //For Showing Values of setTempBar (User Input); 10/10/2018 (AM)
        final TextView tempVal = (TextView) findViewById(R.id.setTempVal);
        final SeekBar tempBar = (SeekBar) findViewById(R.id.setTempBar);
        //Actions when seekbar is changed

        tempBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar SBT) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar SBT) {

                }

                //When the progress of the seekbar is changed, the value displayed as progress also changes to that same value of progress.
                @Override
                public void onProgressChanged(SeekBar SBT, int val_t, boolean check) {
                    //val = tempBar.getProgress();
                    tempVal.setText(String.valueOf(val_t) + "\u00B0"); //int val_t represents progress
                    tempTSet = val_t;
                    tmp = "T"+String.valueOf(tempVal);
                    Msg.send(tmp.getBytes());
                    writeFile(tempTSet, humidTSet, tempOn, humOn);

                }
            });

        //For Showing Values of setHumBar (User Input); 10/10/2018 (AM)
        final TextView humVal = (TextView) findViewById(R.id.setHumVal);
        final SeekBar humBar = (SeekBar) findViewById(R.id.setHumBar);
        //Actions when seekbar is changed
        humBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

            @Override
            public void onStopTrackingTouch(SeekBar SBH){

            }

            @Override
            public void onStartTrackingTouch(SeekBar SBH){

            }

            //When the progress of the seekbar is changed, the value displayed as progress also changes to that same value of progress.
            @Override
            public void onProgressChanged(SeekBar SBH, int val_h, boolean check){
                //val = tempBar.getProgress();
                humVal.setText(String.valueOf(val_h) + "\u0025");  //int val_h represents progress
                humidTSet = val_h;
                tmp = "H"+String.valueOf(humVal);
                Msg.send(tmp.getBytes());
                writeFile(tempTSet, humidTSet, tempOn, humOn);


            }
        });

        //For Syncing ProgressBar and the values shown for current temperature
        final ProgressBar currentTempBar = (ProgressBar)    findViewById(R.id.currentTempBar);
        final TextView currentTemp = (TextView) findViewById(R.id.currentTemp);
        final TextView tempUnit = (TextView)    findViewById(R.id.tempUnit);


        currentTempBar.setProgress(currentT, false);
        currentTemp.setText(String.valueOf(currentT));
        tempUnit.setText("\u00B0");


        //For Syncing ProgressBar and the values shown for current humidity
        final ProgressBar currentHumBar = (ProgressBar)    findViewById(R.id.currentHumBar);
        final TextView currentHum = (TextView) findViewById(R.id.currentHum);
        final TextView humUnit = (TextView) findViewById(R.id.humUnit);

        currentHumBar.setProgress(currentH, false);
        currentHum.setText(String.valueOf(currentH));
        humUnit.setText("\u0025");





    }
    private void writeFile ( int tempSetValue, int humSetValue, boolean temp, boolean humOn){
        try {
            //Opening the file/Creating the file
            OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.close(); //Closing the file with new data, also clears all previously stored data.

            //Reopens the file, now blank.
            outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));
            //Writing values of Hours, minutes, daysChecked, temp, bright, humid


                    outputStream.write("Desired Temp: " + String.valueOf(tempSetValue));
                    outputStream.write("\n");

                    outputStream.write("Desired Humid: " + String.valueOf(humSetValue));
                    outputStream.write("\n");

                    outputStream.write("Temp On: " + String.valueOf(tempOn) + "\n");
                    outputStream.write("Humid On: " + String.valueOf(humOn) + "\n");



            //Closes the file
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            e.printStackTrace();
        }
    }
    //For reading the files which store the activity's data
    public String readFile(String file) {

        String ret = "";

        try {
            InputStream inputStream = this.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }


}
