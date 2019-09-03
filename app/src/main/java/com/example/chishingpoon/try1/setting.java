package com.example.chishingpoon.try1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class setting extends AppCompatActivity {
    public static TextView connectedDevice;

    EditText lightAmount;
    EditText blindAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        connectedDevice = findViewById(R.id.btDevice);
        Button button = findViewById(R.id.BTC);
        lightAmount = findViewById(R.id.editText);
        lightAmount.setText("2");
        blindAmount = findViewById(R.id.editText2);
        blindAmount.setText("1");


        lightAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightAmount.setCursorVisible(true);
                //When ENTER is pressed, keyboard is hidden
                lightAmount.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == keyEvent.KEYCODE_ENTER) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Menu.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(lightAmount.getWindowToken(), 0);
                            lightAmount.setCursorVisible(false);
                        }
                        return false;
                    }});
                String lB = lightAmount.getText().toString();
                brightness_sub.lightNum = Integer.getInteger(lB);
            }
        });

        blindAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blindAmount.setCursorVisible(true);
                //When ENTER is pressed, keyboard is hidden
                blindAmount.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == keyEvent.KEYCODE_ENTER) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Menu.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(blindAmount.getWindowToken(), 0);
                            blindAmount.setCursorVisible(false);
                        }
                        return false;
                    }});
                String lB = blindAmount.getText().toString();
                brightness_sub.blindNum = Integer.getInteger(lB);
            }
        });
    }
}
