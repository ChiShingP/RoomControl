/*
    PROGRAMMER  :   CHI SHING POON
    PROJECT     :   Junior Design EE3140
    PURPOSE     :   To create an application which will control various settings of a room control system.
    ----------------------------------------------------
    This page contains the code for the main menu/screen of the application. 10/10/2018

    ----------------------------------------------------
    DEV NOTE:
        DATE    :   10/10/2018
        PURPOSE :   Created Button to Open New Activity
        NOTE    :   N/A

        10/17/2018
        thisDay and thisDate now shows current day of week and current date
        Need to fix unit within tEMP(f) Title, research global variable, how to get a value from nonlocal memory.


        TO DO
            Values stored
            -Local
            -Online
            Pass Room Name across all activities
 */



package com.example.chishingpoon.try1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

public class Menu extends AppCompatActivity {
    public static String room;
    //BluetoothChatFragment BCF = new BluetoothChatFragment();
    //Layout View



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //BluetoothChatFragment.mChatService.stop();





        //Text displayed for title of Current Brightness
        TextView overB = findViewById(R.id.bright);
        overB.setText("Overall Brightness");

        //Displaying values for current brightness (brightValue)
        TextView brightVal = findViewById(R.id.brightValue);
        setBright(brightVal);

        //Text Displayed on editText for roomName
        final EditText roomName = findViewById(R.id.roomName);
        roomName.setText("INSERT ROOM NAME");
        roomName.setCursorVisible(false);
        //Allows User to input text
        roomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomName.setCursorVisible(true);
                        //When ENTER is pressed, keyboard is hidden
                        roomName.setOnKeyListener(new View.OnKeyListener() {
                            @Override
                            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                                if (i == keyEvent.KEYCODE_ENTER){
                                    InputMethodManager imm = (InputMethodManager)getSystemService(Menu.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(roomName.getWindowToken(), 0);
                                    roomName.setCursorVisible(false);
                                } return false;
                    }
                });
                //Stores that text value in a global variable
                Menu.room = String.valueOf(roomName.getText());
                ledControl.btn1.setText(String.valueOf(roomName.getText()));

            }
        });


        //Displaying current Temp. Value
        TextView tempVal = findViewById(R.id.tempValue);
        setTemp(tempVal);

        //Displaying current humidity value
        TextView humVal = findViewById(R.id.humidValue);
        setHum((humVal));

        //Text displayed on button for temp and humidity
        TextView thButton = findViewById(R.id.openTempMenu);
        thButton.setText("Temperature and Humidity");

        //Text displayed on button id;preset
        TextView presetButton = findViewById(R.id.preset);
        presetButton.setText("Presets");

        //Text displayed on button id;settings
        TextView settingButton = findViewById(R.id.setting);
        settingButton.setText("Settings");

        //thisDate will display today's date
        TextView thisDate = findViewById(R.id.thisDate);
        setDate(thisDate);

        //thisDay will display today's day (Mon/Tues/Wed/ETC)
        TextView thisDay = findViewById(R.id.thisDay);
        setDay(thisDay);

        //Button will open new Activity 10/08/2018 (PM)
        {
            //Button opens temp submenu
            Button tempMenu = (Button) findViewById(R.id.openTempMenu);
            tempMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Menu.this, tempMenu.class));
                    //Opens new Activity; Sub Menu for Temp+Humid
                }
            });

            // Button opens brightness submenu
            Button brightMenu = (Button) findViewById(R.id.brightMenu);
            brightMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Menu.this, brightness_sub.class));
                }
            });

            //Button opens preset submenu
            presetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Menu.this, presetSub.class));
                }
            });
            settingButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(Menu.this, setting.class));
                }
            });
        }

        /*To pass values
        Intent menuIntent= new Intent(getApplicationContext(), mainMenu.class);
        menuIntent.putExtra("room", room);
        startActivity(menuIntent);
        finish();
        */


    }




    //Functions to obtain current date
    public void setDate(TextView view) {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatVar = new SimpleDateFormat("MM-dd-yyyy");
        String date = formatVar.format(today);
        view.setText(date);



    }

    //Function to obtain the day of the week
    public void setDay(TextView view) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        String weekDay = dayFormat.format(c.getTime());
        view.setText(weekDay);
    }

    //Functions to obtain temperature value from MSP430
    public void setTemp(TextView view) {
        int tempV = 75;
        view.setText(String.valueOf(tempV)  + "\u00B0");
    }

    //Functino to obtain humidity value from MSP430
    public void setHum(TextView view){
        int humV = 20;
        view.setText(String.valueOf(humV) + "\u0025");
    }

    //Functinos to obtain brightness value from MSP430
    public void setBright(TextView view){
        int brightV = 65;
        view.setText(String.valueOf(brightV) + "\u0025");
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