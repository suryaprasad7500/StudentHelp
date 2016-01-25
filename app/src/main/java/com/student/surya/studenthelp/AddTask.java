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
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Surya on 11/11/2015.
 */
public class AddTask extends Fragment {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String taskDataName, taskDataDue, taskDataSched, taskDataClass;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_task, container, false);
        final EditText taskNameIn = (EditText) view.findViewById(R.id.taskNameIn);
        final EditText dueDateIn = (EditText) view.findViewById(R.id.dueDateIn);
        final EditText schedDateIn = (EditText) view.findViewById(R.id.schedDateIn);
        Button backBtn = (Button) view.findViewById(R.id.taskList);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new taskFragment();
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
                taskDataName = taskNameIn.getText().toString();
                taskDataDue = dueDateIn.getText().toString();
                taskDataSched = schedDateIn.getText().toString();
                writeTask();
            }
        });
        return view;
    }
    public void writeTask(){
        if (isExternalStorageWritable()){
            File dir = new File(path);
            dir.mkdirs();
            File outTask = new File(path + "/tasksTest.txt");
            try{
                String nextLine = "\n";
                FileOutputStream fos = new FileOutputStream(outTask, true);
                fos.write(taskDataName.getBytes());
                fos.write(";".getBytes());
                fos.write(taskDataDue.getBytes());
                fos.write(";".getBytes());
                fos.write(taskDataSched.getBytes());
                fos.write(nextLine.getBytes());
                fos.close();
                Toast toast = Toast.makeText(getActivity(), "File written!", Toast.LENGTH_SHORT);
                toast.show();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            Toast toast = Toast.makeText(getActivity(), "No storage!", Toast.LENGTH_SHORT);
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
