package com.student.surya.studenthelp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Surya on 10/25/2015.
 */
public class ClassAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] classNameIn;
    private final String[] classTime;
    private final String[] classProf;
    private final String[] classRoom;
    private final String[] classDays;

    public ClassAdapter(Activity context, String[] classNameIn, String[] classTime, String[] classProf, String[] classRoom, String[] classDays) {
        super(context, R.layout.classlistlayout,classNameIn);
        this.context = context;
        this.classNameIn = classNameIn;
        this.classProf = classProf;
        this.classRoom = classRoom;
        this.classTime = classTime;
        this.classDays = classDays;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater classListInflater = LayoutInflater.from(getContext());
        View listView = classListInflater.inflate(R.layout.classlistlayout, parent, false);
        ImageView listImage = (ImageView)  listView.findViewById(R.id.imageView2);
        TextView className = (TextView) listView.findViewById(R.id.classNameView);
        TextView classProfView = (TextView) listView.findViewById(R.id.classProfView);
        TextView classRoomView = (TextView) listView.findViewById(R.id.classRoomView);
        TextView classTimeView = (TextView) listView.findViewById(R.id.classStartView);
        TextView classDaysView  =(TextView) listView.findViewById(R.id.classDayView);
        if(classNameIn[position] != "" && classProf[position] != "" && classRoom[position] != "" && classTime[position] != "" && classDays[position] != ""){
            className.setText(classNameIn[position]);
            classProfView.setText(classProf[position]);
            classRoomView.setText(classRoom[position]);
            classTimeView.setText(classTime[position]);
            classDaysView.setText(classDays[position]);
            listImage.setImageResource(R.drawable.listitemcolor);
        }
        else{
            return listView;
        }
        return listView;
    }
}
