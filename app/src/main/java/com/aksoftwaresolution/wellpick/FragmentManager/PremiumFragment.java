package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aksoftwaresolution.wellpick.R;

public class PremiumFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View premiumView=inflater.inflate(R.layout.fragment_premium, container, false);
        if (container!=null)container.removeAllViews();








        return premiumView;
    }
}
