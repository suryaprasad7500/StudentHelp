package com.student.surya.studenthelp;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Surya on 10/21/2015.
 */
public class classFragment extends Fragment{
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    View view;
    String[] className = {"", ""};
    String[] classTime = {"", ""};
    String[] classProf = {"", ""};
    String[] classRoom = {"", ""};
    String[] classDays = {"", ""};
    String[] classArray = new String[100];
    String tempString, fileData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int countClass = 0;
        view = inflater.inflate(R.layout.frag_class, container, false);
        String[] classes = {"OS", "CD", "CCN"};
        ListView list = (ListView) view.findViewById(R.id.taskList);
        list.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left));
        Button addClass = (Button) view.findViewById(R.id.addClassBtn);
        if(isExternalStorageWritable()){
            try{
                FileInputStream fis = new FileInputStream(path + "/classesTest.txt");
                InputStreamReader reader = new InputStreamReader(fis);
                BufferedReader buffer = new BufferedReader(reader);
                while((tempString = buffer.readLine())!=null){
                    int count = 1;
                    fileData = tempString;
                    for (String temp: fileData.split(";")){
                        classArray[count] = temp;
                        count++;
                    }
                    className[countClass] = classArray[1];
                    classTime[countClass] = classArray[2];
                    classProf[countClass] = classArray[3];
                    classRoom[countClass] = classArray[4];
                    classDays[countClass] = classArray[5];
                    countClass++;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            }
        ListAdapter classAdapter = new ClassAdapter(getActivity(), className, classTime, classProf, classRoom, classDays);
        list.setAdapter(classAdapter);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new AddClass();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fTran = fragmentManager.beginTransaction();
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_out_right));
                fTran.replace(R.id.fragment, frag);
                fTran.commit();
            }
        });
        return view;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
