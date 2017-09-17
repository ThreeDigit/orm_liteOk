package com.example.rubs.ormlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAddInformation, btnShowInformation, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddInformation = (Button) findViewById(R.id.btnAddInformation);
        btnShowInformation = (Button) findViewById(R.id.btnShowInformation);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnAddInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent
                        (MainActivity.this, AddDataActivity.class));
            }
        });

        btnShowInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent
                        (MainActivity.this, DisplayAllAndDeleteActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(MainActivity.this, ShowAllActivity.class));
            }
        });
    }
}
