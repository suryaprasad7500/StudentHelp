package com.student.surya.studenthelp;

import android.app.Fragment;
import android.app.backup.BackupManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Surya on 10/21/2015.
 */
public class dashFrag extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dashboard, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto.ttf");
        Typeface boldFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RobotoCondensed-Bold.ttf");
        TextView dayClass = (TextView) view.findViewById(R.id.dayClassText);
        TextView weekClass = (TextView) view.findViewById(R.id.weekClassText);
        TextView dayTasks = (TextView) view.findViewById(R.id.dayTasksText);
        TextView weekTasks = (TextView) view.findViewById(R.id.weekTasksText);
        dayClass.setText("3 Classes\n2 Done\n1 Left");
        dayTasks.setText("2 Tasks\n0 Overdue\n0 Exams");
        weekClass.setText("6 Classes\n2 Done\n4 Left");
        weekTasks.setText("6 Tasks\n0 Overdue\n0 Exams");
        dayClass.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in));
        dayTasks.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in));
        weekClass.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in));
        weekTasks.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in));
        TextView dayClassHead = (TextView) view.findViewById(R.id.dayClassHead);
        TextView dayTasksHead = (TextView) view.findViewById(R.id.dayTasksHead);
        TextView weekClassHead = (TextView) view.findViewById(R.id.weekClassHead);
        TextView weekTasksHead = (TextView) view.findViewById(R.id.weekTasksHead);
        dayClassHead.setTypeface(boldFont);
        dayTasksHead.setTypeface(boldFont);
        weekClassHead.setTypeface(boldFont);
        weekTasksHead.setTypeface(boldFont);
        Button sync = (Button) view.findViewById(R.id.requestBackup);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBackup();
            }
        });
        return view;
    }
    public void requestBackup(){
        BackupManager backMan = new BackupManager(getActivity());
        backMan.dataChanged();
    }
}
