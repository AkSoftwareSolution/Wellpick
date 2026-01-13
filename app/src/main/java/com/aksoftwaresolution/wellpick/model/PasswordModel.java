package com.aksoftwaresolution.wellpick.model;

import android.content.Context;

import com.aksoftwaresolution.wellpick.contract.ConformPasswordContract;

public class PasswordModel implements ConformPasswordContract.Model{
    private final Context context;

    public PasswordModel(Context context) {
        this.context = context;
    }

    @Override
    public void chackingPassword(chackingPasswordFinishedListener passwordFinishedListener) {

    }
}
