package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aksoftwaresolution.wellpick.R;


public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View aboutView=inflater.inflate(R.layout.fragment_about, container, false);
        if (container!=null)container.removeAllViews();




        return aboutView;
    }
}