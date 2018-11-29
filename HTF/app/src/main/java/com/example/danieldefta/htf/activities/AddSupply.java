package com.example.danieldefta.htf.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;

import com.example.danieldefta.htf.R;

public class AddSupply extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supply);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
    }
}
