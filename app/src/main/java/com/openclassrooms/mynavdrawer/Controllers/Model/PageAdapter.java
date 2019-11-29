package com.openclassrooms.mynavdrawer.Controllers.Model;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.mynavdrawer.Controllers.Fragments.Similaire_annonce;
import com.openclassrooms.mynavdrawer.Controllers.Fragments.UneAnnonce;
import com.openclassrooms.mynavdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageAdapter extends FragmentPagerAdapter {
    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return UneAnnonce.newInstance();
            case 1: //Page number 2
                return Similaire_annonce.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Detail";
            case 1: //Page number 2
                return "Similaires";
            default:
                return null;
        }
    }
}