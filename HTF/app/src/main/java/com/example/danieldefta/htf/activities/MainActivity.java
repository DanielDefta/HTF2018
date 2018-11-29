package com.example.danieldefta.htf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.danieldefta.htf.R;
import com.example.danieldefta.htf.fragments.SuppliesFragment;

import java.io.Console;
import java.util.logging.ConsoleHandler;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView credentialsView = (TextView) findViewById(R.id.credentials);
        Button logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        Button suppliesButton = findViewById(R.id.supplies);
        suppliesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supplies();
            }
        });

        //Obtain the token from the Intent's extras
        String accessToken = getIntent().getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN);
        if(accessToken == null || accessToken.equals("")){
            logout();
        }
        else {
            this.accessToken = accessToken;
            supplies();
        }
        credentialsView.setText(accessToken);

    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.KEY_CLEAR_CREDENTIALS, true);
        startActivity(intent);
        finish();
    }

    private void supplies(){
        Intent intent = new Intent(this, SuppliesActivity.class);
        intent.putExtra(LoginActivity.EXTRA_ACCESS_TOKEN, accessToken);
        startActivity(intent);
        finish();
    }
}