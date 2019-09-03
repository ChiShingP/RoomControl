/*
    PROGRAMMER  :   CHI SHING POON
    PROJECT     :   Junior Design EE3140
    PURPOSE     :   To create an application which will control various settings of a room control system.
    ----------------------------------------------------
    This page contains the code for a submenu to control brightness settings. 10/16/2018

    ----------------------------------------------------
    DEVELOPMENT NOTE:
    DATE:       10/16/2018
    PURPOSE:    Activity Created, added titles, added light 1, light 2, and blind 1; as well as a floating button.
    NOTE:       Needs to implement buttons to add blinds/lights
                    - which will initialize new settings for said blind/light and display them on this page
                Need to add current brightness stats, learn Scroll Layout
                Saving values even after app exits

    TO DO
    Values need to be saved
        As well as import/export
    Attributes show up as more components are connected
    Copy this template as more rooms are created
        -- Each room have their own values passed
           Same activity everytime but values passed through and stored differently

 */
//Need to ask MSP for how many lights or blinds there are
package com.example.chishingpoon.try1;


import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.OutputStreamWriter;


public class brightness_sub extends AppCompatActivity {
    private String fileName = "brightness.txt";
    private int[] lightValues;
    private int[] blindValues;
    public static int lightNum, blindNum;
    //Layout View
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brightness);

        lightNum = 2;
        blindNum = 1;

        //Room Title
        final TextView room = findViewById(R.id.roomTitle);
        room.setText(Menu.room);

        //bValue display; Attempt to get value from menu.java
        TextView brightnessNow = findViewById(R.id.bValue);
        //Bundle bundle = getIntent().getExtras();
        //String currentBrightness = bundle.getString("currBrightness");  //Calling data from Menu.java
        //brightnessNow.setText(currentBrightness); // Set 'bValue' brightnessNow to data called from menu.java
        brightnessNow.setText("60" + "\u0025");

        TextView bTitle = findViewById(R.id.bottomTitle);
        bTitle.setText("Current Brightness");



        //TextView Values for Titles
        final TextView leftTitle = findViewById(R.id.leftTitle);
        leftTitle.setText("Lights and Brightness");
        final TextView rightTitle = findViewById(R.id.rightTitle);
        rightTitle.setText("Blinds and Closures");

        //-------------------------------------------------------------
        //For Showing Values of Light (User Input)
        final TextView light1 = (TextView)  findViewById(R.id.light1);
        light1.setText("Light 1");
        final TextView lOneVal = (TextView) findViewById(R.id.L1);
        final SeekBar lOneBar = (SeekBar) findViewById(R.id.L1bar);
        barSync(lOneBar, lOneVal);

        final TextView light2 = (TextView)  findViewById(R.id.Light2);
        light2.setText("Light 2");
        final TextView l2Val = (TextView) findViewById(R.id.L2);
        final SeekBar l2Bar = (SeekBar) findViewById(R.id.L2bar);
        barSync(l2Bar, l2Val);

        final TextView light3 = (TextView)  findViewById(R.id.light3);
        light3.setText("Light 3");
        final TextView l3Val = (TextView) findViewById(R.id.l3);
        final SeekBar l3Bar = (SeekBar) findViewById(R.id.L3Bar);
        barSync(l3Bar, l3Val);

        final TextView light4 = (TextView)  findViewById(R.id.light4);
        light4.setText("Light 4");
        final TextView l4Val = (TextView) findViewById(R.id.l4);
        final SeekBar l4Bar = (SeekBar) findViewById(R.id.L4Bar);
        barSync(l4Bar, l4Val);

        final TextView light5 = (TextView)  findViewById(R.id.light5);
        light5.setText("Light 5");
        final TextView l5Val = (TextView) findViewById(R.id.l5);
        final SeekBar l5Bar = (SeekBar) findViewById(R.id.L5Bar);
        barSync(l5Bar, l5Val);

        final TextView light6 = (TextView)  findViewById(R.id.light6);
        light6.setText("Light 6");
        final TextView l6Val = (TextView) findViewById(R.id.l6);
        final SeekBar l6Bar = (SeekBar) findViewById(R.id.L6Bar);
        barSync(l6Bar, l6Val);

        final TextView light7 = (TextView)  findViewById(R.id.light7);
        light7.setText("Light 7");
        final TextView l7Val = (TextView) findViewById(R.id.l7);
        final SeekBar l7Bar = (SeekBar) findViewById(R.id.L7Bar);
        barSync(l7Bar, l7Val);
        light7.setVisibility(TextView.INVISIBLE);
        light7.setEnabled(false); //GRAYS OUT ITEM

        //-------------------------------------------------------------
        //For Showing Values of blinds(User Input)
        final TextView blind1 = (TextView)  findViewById(R.id.blind1);
        blind1.setText("Blind 1");
        final TextView bOneVal = (TextView) findViewById(R.id.b1);
        final SeekBar bOneBar = (SeekBar) findViewById(R.id.b1Bar);
        barSync(bOneBar, bOneVal);

        final TextView blind2 = (TextView)  findViewById(R.id.blind2);
        blind2.setText("Blind 2");
        final TextView b2Val = (TextView) findViewById(R.id.b2);
        final SeekBar b2Bar = (SeekBar) findViewById(R.id.b2Bar);
        barSync(b2Bar, b2Val);

        final TextView blind3 = findViewById(R.id.blind3);
        blind3.setText("Blind 3");
        final TextView b3Val = findViewById(R.id.b3);
        final SeekBar b3Bar = findViewById(R.id.b3Bar);
        barSync( b3Bar, b3Val);

        final TextView blind4 = findViewById(R.id.blind4);
        blind4.setText("Blind 4");
        final TextView b4Val = findViewById(R.id.b4);
        final SeekBar b4Bar = findViewById(R.id.b4Bar);
        barSync( b4Bar, b4Val);

        final TextView blind5 = findViewById(R.id.blind5);
        blind5.setText("Blind 5");
        final TextView b5Val = findViewById(R.id.b5);
        final SeekBar b5Bar = findViewById(R.id.b5Bar);
        barSync( b5Bar, b5Val);

        final TextView blind6 = findViewById(R.id.blind6);
        blind6.setText("Blind 6");
        final TextView b6Val = findViewById(R.id.b6);
        final SeekBar b6Bar = findViewById(R.id.b6Bar);
        barSync( b6Bar, b6Val);

        final TextView blind7 = findViewById(R.id.blind7);
        blind7.setText("Blind 7");
        final TextView b7Val = findViewById(R.id.b7);
        final SeekBar b7Bar = findViewById(R.id.b7Bar);
        barSync( b7Bar, b7Val);


        switch(lightNum){
            case 7:
                light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);

                light3.setVisibility(TextView.VISIBLE);
                l3Bar.setVisibility(SeekBar.VISIBLE);
                l3Val.setVisibility(TextView.VISIBLE);


                light4.setVisibility(TextView.VISIBLE);
                l4Bar.setVisibility(SeekBar.VISIBLE);
                l4Val.setVisibility(TextView.VISIBLE);

                light5.setVisibility(TextView.VISIBLE);
                l5Bar.setVisibility(SeekBar.VISIBLE);
                l5Val.setVisibility(TextView.VISIBLE);

                light6.setVisibility(TextView.VISIBLE);
                l6Bar.setVisibility(SeekBar.VISIBLE);
                l7Val.setVisibility(TextView.VISIBLE);

                light7.setVisibility(TextView.VISIBLE);
                l7Bar.setVisibility(SeekBar.VISIBLE);
                l7Val.setVisibility(TextView.VISIBLE);
                break;
            case 6:
                light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);

                light3.setVisibility(TextView.VISIBLE);
                l3Bar.setVisibility(SeekBar.VISIBLE);
                l3Val.setVisibility(TextView.VISIBLE);


                light4.setVisibility(TextView.VISIBLE);
                l4Bar.setVisibility(SeekBar.VISIBLE);
                l4Val.setVisibility(TextView.VISIBLE);

                light5.setVisibility(TextView.VISIBLE);
                l5Bar.setVisibility(SeekBar.VISIBLE);
                l5Val.setVisibility(TextView.VISIBLE);

                light6.setVisibility(TextView.VISIBLE);
                l6Bar.setVisibility(SeekBar.VISIBLE);
                l7Val.setVisibility(TextView.VISIBLE);
                break;
            case 5: light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);

                light3.setVisibility(TextView.VISIBLE);
                l3Bar.setVisibility(SeekBar.VISIBLE);
                l3Val.setVisibility(TextView.VISIBLE);


                light4.setVisibility(TextView.VISIBLE);
                l4Bar.setVisibility(SeekBar.VISIBLE);
                l4Val.setVisibility(TextView.VISIBLE);

                light5.setVisibility(TextView.VISIBLE);
                l5Bar.setVisibility(SeekBar.VISIBLE);
                l5Val.setVisibility(TextView.VISIBLE);
                break;
            case 4: light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);

                light3.setVisibility(TextView.VISIBLE);
                l3Bar.setVisibility(SeekBar.VISIBLE);
                l3Val.setVisibility(TextView.VISIBLE);


                light4.setVisibility(TextView.VISIBLE);
                l4Bar.setVisibility(SeekBar.VISIBLE);
                l4Val.setVisibility(TextView.VISIBLE);

                break;
            case 3: light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);

                light3.setVisibility(TextView.VISIBLE);
                l3Bar.setVisibility(SeekBar.VISIBLE);
                l3Val.setVisibility(TextView.VISIBLE);

                break;
            case 2: light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);

                light2.setVisibility(TextView.VISIBLE);
                l2Bar.setVisibility(SeekBar.VISIBLE);
                l2Val.setVisibility(TextView.VISIBLE);
                break;
            case 1:
                light1.setVisibility(TextView.VISIBLE);
                lOneBar.setVisibility(SeekBar.VISIBLE);
                lOneVal.setVisibility(TextView.VISIBLE);
                break;
        }

        switch (blindNum){
            case 7:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);

                blind3.setVisibility(TextView.VISIBLE);
                b3Bar.setVisibility(SeekBar.VISIBLE);
                b3Val.setVisibility(TextView.VISIBLE);


                blind4.setVisibility(TextView.VISIBLE);
                b4Bar.setVisibility(SeekBar.VISIBLE);
                b4Val.setVisibility(TextView.VISIBLE);

                blind5.setVisibility(TextView.VISIBLE);
                b5Bar.setVisibility(SeekBar.VISIBLE);
                b5Val.setVisibility(TextView.VISIBLE);

                blind6.setVisibility(TextView.VISIBLE);
                b6Bar.setVisibility(SeekBar.VISIBLE);
                b7Val.setVisibility(TextView.VISIBLE);

                blind7.setVisibility(TextView.VISIBLE);
                b7Bar.setVisibility(SeekBar.VISIBLE);
                b7Val.setVisibility(TextView.VISIBLE);
                break;
            case 6:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);

                blind3.setVisibility(TextView.VISIBLE);
                b3Bar.setVisibility(SeekBar.VISIBLE);
                b3Val.setVisibility(TextView.VISIBLE);


                blind4.setVisibility(TextView.VISIBLE);
                b4Bar.setVisibility(SeekBar.VISIBLE);
                b4Val.setVisibility(TextView.VISIBLE);

                blind5.setVisibility(TextView.VISIBLE);
                b5Bar.setVisibility(SeekBar.VISIBLE);
                b5Val.setVisibility(TextView.VISIBLE);

                blind6.setVisibility(TextView.VISIBLE);
                b6Bar.setVisibility(SeekBar.VISIBLE);
                b7Val.setVisibility(TextView.VISIBLE);
                break;
            case 5:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);

                blind3.setVisibility(TextView.VISIBLE);
                b3Bar.setVisibility(SeekBar.VISIBLE);
                b3Val.setVisibility(TextView.VISIBLE);


                blind4.setVisibility(TextView.VISIBLE);
                b4Bar.setVisibility(SeekBar.VISIBLE);
                b4Val.setVisibility(TextView.VISIBLE);

                blind5.setVisibility(TextView.VISIBLE);
                b5Bar.setVisibility(SeekBar.VISIBLE);
                b5Val.setVisibility(TextView.VISIBLE);
                break;
            case 4:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);

                blind3.setVisibility(TextView.VISIBLE);
                b3Bar.setVisibility(SeekBar.VISIBLE);
                b3Val.setVisibility(TextView.VISIBLE);


                blind4.setVisibility(TextView.VISIBLE);
                b4Bar.setVisibility(SeekBar.VISIBLE);
                b4Val.setVisibility(TextView.VISIBLE);
                break;
            case 3:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);

                blind3.setVisibility(TextView.VISIBLE);
                b3Bar.setVisibility(SeekBar.VISIBLE);
                b3Val.setVisibility(TextView.VISIBLE);

                break;
            case 2:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                blind2.setVisibility(TextView.VISIBLE);
                b2Bar.setVisibility(SeekBar.VISIBLE);
                b2Val.setVisibility(TextView.VISIBLE);
                break;
            case 1:
                blind1.setVisibility(TextView.VISIBLE);
                bOneBar.setVisibility(SeekBar.VISIBLE);
                bOneVal.setVisibility(TextView.VISIBLE);

                break;
        }
    }

    //Function to sync seekbar values and display for said values
    public void barSync (SeekBar bar, final TextView view){
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //When seekBar value is changed (moved), the value of the TextView is changed to the same value as SeekBar
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                view.setText(String.valueOf(i) + "\u0025");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void writePresetFile(int[] light, int[] blind ){
        try {
            //Opening the file
            OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.close();

            outputStream = new OutputStreamWriter(this.openFileOutput(fileName, Context.MODE_PRIVATE));

            //Writing values of Hours, minutes, daysChecked, temp, bright, humid
            outputStream.write("Hours\n");
            for(int i = 1; i <8; i++){
                outputStream.write(String.valueOf(light[i]) + " ");
            }
            outputStream.write("\n");

            outputStream.write("Minutes\n");
            for(int i = 1; i <8; i++){
                outputStream.write(String.valueOf(blind[i])+ " ");
            }
            outputStream.write("\n");


            //Closes the file
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            e.printStackTrace();
        }
    }


}
