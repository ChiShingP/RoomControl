package com.example.chishingpoon.try1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

final public class TimePickerFragment extends DialogFragment
         {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, false);
    }


}

/*
@Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //DO WHATEVER
       }
 */