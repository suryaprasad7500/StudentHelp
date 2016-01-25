package com.student.surya.studenthelp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Surya on 11/8/2015.
 */
public class examFragment extends Fragment {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    View view;
    String[] examName = {""};
    String[] examTime = {""};
    String[] examDate = {""};
    String[] examRoom = {""};
    String[] classArray = new String[100];
    String tempString, fileData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int countClass = 0;
        view = inflater.inflate(R.layout.frag_exams, container, false);
        String[] exams = {"Mid-Term", "Final", "Exam 2"};
        ListView examList = (ListView) view.findViewById(R.id.examList);
        examList.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left));
        Button addBtn = (Button) view.findViewById(R.id.addExamBtn);
        if(isExternalStorageWritable()){
            try{
                FileInputStream fis = new FileInputStream(path + "/examsTest.txt");
                InputStreamReader reader = new InputStreamReader(fis);
                BufferedReader buffer = new BufferedReader(reader);
                while((tempString = buffer.readLine())!=null){
                    int count = 1;
                    //Toast toast = Toast.makeText(getActivity().getApplication(), "Filling buffer", Toast.LENGTH_SHORT);
                    //toast.show();
                    fileData = tempString;
                    for (String temp: fileData.split(";")){
                        classArray[count] = temp;
                        count++;
                    }
                    examName[countClass] = classArray[1];
                    examTime[countClass] = classArray[2];
                    examDate[countClass] = classArray[3];
                    examRoom[countClass] = classArray[4];
                    countClass++;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        ListAdapter examAdapter = new ExamAdapter(getActivity(), examName, examTime, examDate, examRoom);
        examList.setAdapter(examAdapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new AddExam();
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
