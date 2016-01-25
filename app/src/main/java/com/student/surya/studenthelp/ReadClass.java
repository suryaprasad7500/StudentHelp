package com.student.surya.studenthelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Surya on 11/14/2015.
 */
public class ReadClass extends Activity {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String fileData;
    String[] classArray = {};
    String[] classNameIn = {}, classDays = {}, classTime = {}, classProf = {}, classRoom = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        TextView className = (TextView) findViewById(R.id.className);
        Bundle classIn = getIntent().getExtras();
        if (classIn == null) {
            className.setText("No data available");
        }
        //String classData = classIn.getString("className");
        String tempString;
        if(isExternalStorageWritable()){
            try{
                FileInputStream fis = new FileInputStream(path + "/classesTest.txt");
                InputStreamReader reader = new InputStreamReader(fis);
                BufferedReader buffer = new BufferedReader(reader);
                while((tempString = buffer.readLine())!=null){
                    Toast toast = Toast.makeText(this, "Filling buffer", Toast.LENGTH_SHORT);
                    toast.show();
                    fileData = tempString;
                    for (String temp: fileData.split(";")){
                        classArray = new String[0];
                        classArray[classArray.length + 1] = temp;
                    }
                    classNameIn[classNameIn.length + 1] = classArray[1];
                    classTime[classTime.length + 1] = classArray[2];
                    classProf[classProf.length + 1] = classArray[3];
                    classRoom[classRoom.length + 1] = classArray[4];
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "No storage", Toast.LENGTH_SHORT);
        }
        Intent sendToClass = new Intent(this, classFragment.class);
        Bundle sendClass = new Bundle();
        sendClass.putStringArray("className", classNameIn);
        sendClass.putStringArray("classTime", classTime);
        sendClass.putStringArray("classProf", classProf);
        sendClass.putStringArray("classRoom", classRoom);
        classFragment frag = new classFragment();
        frag.setArguments(sendClass);
        /*sendToClass.putExtra("className", classNameIn);
        sendToClass.putExtra("classTime", classTime);
        sendToClass.putExtra("classProf", classProf);
        sendToClass.putExtra("classRoom", classRoom);*/
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}