package com.student.surya.studenthelp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Surya on 11/8/2015.
 */
public class ExamAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] examName;
    private final String[] examRoom;
    private final String[] examDate;
    private final String[] examTime;
    public ExamAdapter(Activity context, String[] examName, String[] examRoom, String[] examDate, String[] examTime) {
        super(context, R.layout.examlistlayoul, examName);
        this.context = context;
        this.examDate = examDate;
        this.examName = examName;
        this.examRoom = examRoom;
        this.examTime = examTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater examListInflater = LayoutInflater.from(getContext());
        View examView = examListInflater.inflate(R.layout.examlistlayoul, parent, false);
        ImageView listImage = (ImageView) examView.findViewById(R.id.imageView2);
        TextView examNameView = (TextView) examView.findViewById(R.id.examNameView);
        TextView examRoomView = (TextView) examView.findViewById(R.id.examRoomView);
        TextView examDateView = (TextView) examView.findViewById(R.id.examDateView);
        TextView examClassView = (TextView) examView.findViewById(R.id.examClassView);
        TextView examTimeView = (TextView) examView.findViewById(R.id.examTimeView);
        examNameView.setText(examName[position]);
        examRoomView.setText(examRoom[position]);
        examTimeView.setText(examTime[position]);
        examDateView.setText(examDate[position]);
        listImage.setImageResource(R.drawable.listitemcolor);
        return examView;
    }
}
