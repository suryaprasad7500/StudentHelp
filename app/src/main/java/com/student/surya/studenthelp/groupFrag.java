package com.student.surya.studenthelp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Surya on 11/10/2015.
 */
public class groupFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_group, container, false);
        String[] groups = {"Physics group", "OS group", "Grad Proj"};
        ListAdapter groupAdapter = new GroupAdapter(view.getContext(), groups);
        ListView list = (ListView) view.findViewById(R.id.groupList);
        list.setAdapter(groupAdapter);
        list.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left));
        return view;
    }
}
