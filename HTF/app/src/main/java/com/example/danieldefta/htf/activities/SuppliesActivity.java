package com.example.danieldefta.htf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.danieldefta.htf.R;
import com.example.danieldefta.htf.fragments.SuppliesFragment;
import com.example.danieldefta.htf.models.Supply;

public class SuppliesActivity extends AppCompatActivity implements SuppliesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplies_activity);

        String accessToken = getIntent().getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SuppliesFragment.newInstance(1, accessToken))
                    .commitNow();
        }

    }

    @Override
    public void onListFragmentInteraction(Supply item) {

    }
}
