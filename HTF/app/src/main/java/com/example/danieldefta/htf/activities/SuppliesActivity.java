package com.example.danieldefta.htf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.danieldefta.htf.R;
import com.example.danieldefta.htf.fragments.SuppliesFragment;
import com.example.danieldefta.htf.models.Supply;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SuppliesActivity extends AppCompatActivity implements SuppliesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplies_activity);

        final String accessToken = getIntent().getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SuppliesFragment.newInstance(1, accessToken))
                    .commitNow();
        }

        FloatingActionButton btnAddSupply = findViewById(R.id.btnAddSupply);
        btnAddSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSupply(accessToken);
            }
        });
    }

    private void addSupply(String accessToken){
        Intent intent = new Intent(this, AddSupply.class);
        intent.putExtra(LoginActivity.EXTRA_ACCESS_TOKEN, accessToken);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(Supply item) {

    }
}
