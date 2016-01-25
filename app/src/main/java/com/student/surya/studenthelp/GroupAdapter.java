package com.student.surya.studenthelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Surya on 11/10/2015.
 */
public class GroupAdapter extends ArrayAdapter<String> {

    public GroupAdapter(Context context, String[] list) {
        super(context, R.layout.grouplistlayout, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater groupInflater = LayoutInflater.from(getContext());
        View listView = groupInflater.inflate(R.layout.grouplistlayout, parent, false);
        String oneGroup = getItem(position);
        ImageView listImage = (ImageView) listView.findViewById(R.id.imageView2);
        TextView groupName = (TextView) listView.findViewById(R.id.groupNameView);
        groupName.setText(oneGroup);
        listImage.setImageResource(R.drawable.listitemcolor);
        return listView;
    }
}
