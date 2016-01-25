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
import java.util.Date;

/**
 * Created by Surya on 11/7/2015.
 */
public class taskFragment extends Fragment{
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String[] taskDataName = {""}, taskDataDue = {""}, taskDataSched = {""};
    String[] taskArray = new String[100];
    String tempString, fileData;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int countTask = 0;
        view = inflater.inflate(R.layout.frag_tasks, container, false);
        ListView list = (ListView) view.findViewById(R.id.taskList);
        list.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left));
        if(isExternalStorageWritable()){
            try{
                FileInputStream fis = new FileInputStream(path + "/tasksTest.txt");
                InputStreamReader reader = new InputStreamReader(fis);
                BufferedReader buffer = new BufferedReader(reader);
                while((tempString = buffer.readLine())!=null){
                    int count = 1;
                    fileData = tempString;
                    for (String temp: fileData.split(";")){
                        taskArray[count] = temp;
                        count++;
                    }
                    taskDataName[countTask] = taskArray[1];
                    taskDataDue[countTask] = taskArray[2];
                    taskDataSched[countTask] = taskArray[3];
                    countTask++;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        ListAdapter taskAdapter = new TaskAdapter(getActivity(), taskDataName, taskDataDue, taskDataSched);
        list.setAdapter(taskAdapter);
        Button addBtn = (Button) view.findViewById(R.id.addTaskBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new AddTask();
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
