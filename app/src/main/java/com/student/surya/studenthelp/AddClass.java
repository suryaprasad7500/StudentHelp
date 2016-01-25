package com.student.surya.studenthelp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.String;

import java.io.FileOutputStream;


/**
 * Created by Surya on 11/11/2015.
 */
public class AddClass extends Fragment {
    View view;
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String classDataName = "";
    String classDataProf = "";
    String classDataRoom = "";
    String classDataStart = "";
    String classDataEnd = "";
    String classDataDays = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_class, container, false);
        Button backBtn = (Button) view.findViewById(R.id.taskList);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new classFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fTran = fragmentManager.beginTransaction();
                fTran.replace(R.id.fragment, frag);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_out_right));
                fTran.commit();
            }
        });

        Button addBtn = (Button) view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classNameIn = (EditText) view.findViewById(R.id.classNameIn);
                EditText startTimeIn = (EditText) view.findViewById(R.id.startTimeIn);
                EditText endTimeIn = (EditText) view.findViewById(R.id.endTimeIn);
                EditText profNameIn = (EditText) view.findViewById(R.id.profNameIn);
                EditText roomNameIn = (EditText) view.findViewById(R.id.roomNameIn);
                classDataName = classNameIn.getText().toString();
                classDataProf = profNameIn.getText().toString();
                classDataRoom = roomNameIn.getText().toString();
                classDataStart = startTimeIn.getText().toString();
                classDataEnd = endTimeIn.getText().toString();
                writeClass();
            }
        });
        final CheckBox monToggle = (CheckBox) view.findViewById(R.id.mondayToggle);
        final CheckBox tueToggle = (CheckBox) view.findViewById(R.id.tuesdayToggle);
        final CheckBox wedToggle = (CheckBox) view.findViewById(R.id.wednesdayToggle);
        final CheckBox thuToggle = (CheckBox) view.findViewById(R.id.thursdayToggle);
        final CheckBox friToggle = (CheckBox) view.findViewById(R.id.fridayToggle);
        final CheckBox satToggle = (CheckBox) view.findViewById(R.id.saturdayToggle);
        final CheckBox sunToggle = (CheckBox) view.findViewById(R.id.sundayToggle);
        monToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monToggle.isChecked()){
                    classDataDays = classDataDays.concat("M");
                }
                else if(!monToggle.isChecked()){
                    classDataDays = classDataDays.replace("M", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        tueToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tueToggle.isChecked()){
                    classDataDays = classDataDays.concat("T");
                }
                else if(!tueToggle.isChecked()){
                    classDataDays = classDataDays.replace("T", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        wedToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wedToggle.isChecked()){
                    classDataDays = classDataDays.concat("W");
                }
                else if(!wedToggle.isChecked()){
                    classDataDays = classDataDays.replace("W", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        thuToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thuToggle.isChecked()){
                    classDataDays = classDataDays.concat("R");
                }
                else if(!thuToggle.isChecked()){
                    classDataDays = classDataDays.replace("R", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        friToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(friToggle.isChecked()){
                    classDataDays = classDataDays.concat("F");
                }
                else if(!friToggle.isChecked()){
                    classDataDays = classDataDays.replace("F", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        satToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satToggle.isChecked()){
                    classDataDays = classDataDays.concat("S");
                }
                else if(!satToggle.isChecked()){
                    classDataDays = classDataDays.replace("S", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        sunToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sunToggle.isChecked()){
                    classDataDays = classDataDays.concat("U");
                }
                else if(!sunToggle.isChecked()){
                    classDataDays = classDataDays.replace("U", "");
                }
                Toast toast = Toast.makeText(getActivity(), classDataDays, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }
    public void writeClass() {
            if (isExternalStorageWritable()) {
                File dir = new File(path);
                dir.mkdirs();
                File outClass = new File(path + "/classesTest.txt");
                try {
                    String nextLine = "\n";
                    FileOutputStream fos = new FileOutputStream(outClass, true);
                    fos.write(classDataName.getBytes());
                    fos.write(";".getBytes());
                    fos.write(classDataStart.getBytes());
                    fos.write("-".getBytes());
                    fos.write(classDataEnd.getBytes());
                    fos.write(";".getBytes());
                    fos.write(classDataProf.getBytes());
                    fos.write(";".getBytes());
                    fos.write(classDataRoom.getBytes());
                    fos.write(";".getBytes());
                    fos.write(classDataDays.getBytes());
                    fos.write(nextLine.getBytes());
                    fos.close();
                    Toast toast = Toast.makeText(getActivity().getApplication(), "File Written! Chill out!", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast toast = Toast.makeText(getActivity().getApplication(), "No storage detected! from Main Activity", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    }
