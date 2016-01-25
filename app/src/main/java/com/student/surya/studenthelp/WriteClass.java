package com.student.surya.studenthelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Surya on 11/14/2015.
 */
public class WriteClass extends Activity {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String classDataName;
    String classDataProf;
    String classDataRoom;
    String classDataStart;
    String classDataEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);
        TextView className = (TextView) findViewById(R.id.classNameOut);
        Bundle classIn = getIntent().getExtras();
        if (classIn == null) {
            className.setText("No data available");
        }
        classDataName = classIn.getString("className");
        classDataProf = classIn.getString("profName");
        classDataRoom = classIn.getString("roomName");
        classDataStart = classIn.getString("startTime");
        classDataEnd = classIn.getString("endTime");
        className.setText(classDataName);
        if(isExternalStorageWritable()){
            File dir = new File(path);
            dir.mkdirs();
            File outClass = new File(path + "/classesTest.txt");
            try{
                String nextLine = "\n";
                FileOutputStream fos = new FileOutputStream(outClass, true);
                fos.write(classDataName.getBytes());
                fos.write(";".getBytes());
                fos.write(classDataStart.getBytes());
                fos.write("-".getBytes());
                fos.write(classDataEnd.getBytes());
                fos.write(";".getBytes());
                //Insert Class Days Here
                fos.write(classDataProf.getBytes());
                fos.write(";".getBytes());
                fos.write(classDataRoom.getBytes());
                fos.write(nextLine.getBytes());
                fos.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            String flag = "File written!";
            Toast toast = Toast.makeText(getApplicationContext(), flag, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onClick(View view){
        Intent readInt = new Intent(this, ReadClass.class);
        startActivity(readInt);
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
