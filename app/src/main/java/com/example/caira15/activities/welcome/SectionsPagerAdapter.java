package com.example.caira15.activities.welcome;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    // EL NUMERO DE FRAGMENTS QUE SE MUESTRAN
    private final Integer NUMFRAGMENTS =3;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }


    // DEVOLVER EL NUMERO DE FRAGMENTS TOTALES
    @Override
    public int getCount() {
        return NUMFRAGMENTS;
    }
}