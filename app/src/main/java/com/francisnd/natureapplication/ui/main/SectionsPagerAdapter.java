package com.francisnd.natureapplication.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.francisnd.natureapplication.All;
import com.francisnd.natureapplication.Fragment1;
import com.francisnd.natureapplication.Fragment2;
import com.francisnd.natureapplication.Fragment3;
import com.francisnd.natureapplication.Fragment4;
import com.francisnd.natureapplication.Fragment5;
import com.francisnd.natureapplication.Fragment6;
import com.francisnd.natureapplication.Fragment7;
import com.francisnd.natureapplication.Fragment8;
import com.francisnd.natureapplication.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_all,R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5,R.string.tab_text_6,R.string.tab_text_7,R.string.tab_text_8};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                System.out.println("******Step1");
                fragment = new All();
                break;
            case 1:
                System.out.println("******Step1");
                fragment = new Fragment1();
                break;
            case 2:
                System.out.println("******Step1");
                fragment = new Fragment2();
                break;
            case 3:
                System.out.println("******Step1");
                fragment = new Fragment3();
                break;
            case 4:
                System.out.println("******Step1");
                fragment = new Fragment4();
                break;
            case 5:
                System.out.println("******Step1");
                fragment = new Fragment5();
                break;
            case 6:
                System.out.println("******Step1");
                fragment = new Fragment6();
                break;
            case 7:
                System.out.println("******Step1");
                fragment = new Fragment7();
                break;
            case 8:
                System.out.println("******Step1");
                fragment = new Fragment8();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 9;
    }
}