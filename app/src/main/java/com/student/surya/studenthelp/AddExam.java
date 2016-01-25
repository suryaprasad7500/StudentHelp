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
 * Created by Surya on 11/12/2015.
 */
public class AddExam extends Fragment {

    View view;
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/studenthelp";
    String examName = "";
    String examTime = "";
    String examRoom = "";
    String examDate = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_exam, container, false);
        Button backBtn = (Button) view.findViewById(R.id.taskList);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new examFragment();
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
                EditText examNameView = (EditText) view.findViewById(R.id.examNameIn);
                EditText examTimeView = (EditText) view.findViewById(R.id.examTimeIn);
                EditText examRoomView = (EditText) view.findViewById(R.id.roomNameIn);
                EditText examDateView = (EditText) view.findViewById(R.id.examDateIn);
                examName = examNameView.getText().toString();
                examTime = examTimeView.getText().toString();
                examRoom = examRoomView.getText().toString();
                examDate = examDateView.getText().toString();
                writeExam();
            }
        });
        return view;
    }
    public void writeExam() {
        if (isExternalStorageWritable()) {
            File dir = new File(path);
            dir.mkdirs();
            File outExam = new File(path + "/examsTest.txt");
            try {
                String nextLine = "\n";
                FileOutputStream fos = new FileOutputStream(outExam, true);
                fos.write(examName.getBytes());
                fos.write(";".getBytes());
                fos.write(examTime.getBytes());
                fos.write(";".getBytes());
                fos.write(examRoom.getBytes());
                fos.write(";".getBytes());
                fos.write(examDate.getBytes());
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
