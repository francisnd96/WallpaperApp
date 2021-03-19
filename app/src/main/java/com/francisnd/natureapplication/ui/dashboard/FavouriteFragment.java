package com.francisnd.natureapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.francisnd.natureapplication.R;
import com.francisnd.natureapplication.Tabbed;
import com.francisnd.natureapplication.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_tabbed,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("*****Step3");
        System.out.println(getContext().toString());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), this.getChildFragmentManager());
        ViewPager viewPager = getView().findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = getView().findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}