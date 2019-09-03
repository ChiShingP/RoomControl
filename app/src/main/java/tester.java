import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chishingpoon.try1.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class tester extends AppCompatActivity {
    private String fileName = "testing.txt";
    private FileOutputStream outputStream;
    String path = this.getFilesDir().getAbsolutePath();
    File file = new File(path + "/testing.txt");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
        private void writingTestI ( int value, int day, String topic){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                BufferedOutputStream bos = new BufferedOutputStream(outputStream);
                BufferedInputStream bis = new BufferedInputStream(inputStream);

                LineNumberReader lnr = new LineNumberReader(new FileReader(fileName));
                String Line = "";


                //Writing values of Hours, minutes, daysChecked, temp, bright, humid
                switch (topic) {
                    case "Temp":
                        outputStream.write(String.valueOf(value).getBytes());
                        outputStream.write("\n".getBytes());
                        break;
                    case "Humid":
                        outputStream.write(String.valueOf(value).getBytes());
                        outputStream.write("\n".getBytes());
                        break;
                    case "Bright":
                        outputStream.write(String.valueOf(value).getBytes());
                        outputStream.write("\n".getBytes());
                        break;
                    case "Hour":
                        outputStream.write(String.valueOf(value).getBytes(), day + 3, 3);
                        outputStream.write("\n".getBytes());
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
    }