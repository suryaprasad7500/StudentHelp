package com.student.surya.studenthelp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by Surya on 11/7/2015.
 */
public class TaskAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] taskName;
    private final String[] taskDue;
    private final String[] taskSched;


    public TaskAdapter(Activity context, String[] taskName, String[] taskDue, String[] taskSched) {
        super(context, R.layout.tasklistlayout,taskName);
        this.context = context;
        this.taskName = taskName;
        this.taskDue = taskDue;
        this.taskSched = taskSched;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater classListInflater = LayoutInflater.from(getContext());
        View listView = classListInflater.inflate(R.layout.tasklistlayout, parent, false);
        ImageView listImage = (ImageView)  listView.findViewById(R.id.imageView2);
        TextView taskNameView = (TextView) listView.findViewById(R.id.taskNameView);
        TextView taskDueView = (TextView) listView.findViewById(R.id.taskDueView);
        TextView taskSchedView = (TextView) listView.findViewById(R.id.taskSchedView);
        taskNameView.setText(taskName[position]);
        taskDueView.setText(taskDue[position]);
        taskSchedView.setText(taskSched[position]);
        listImage.setImageResource(R.drawable.listitemcolor);
        return listView;
    }

}
