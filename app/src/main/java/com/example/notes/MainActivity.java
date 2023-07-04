package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView Indus, Budh, Delhi, Revolt,Swadeshi,Independence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(2)
                .setRemindInterval(2)
                .setShowNeverButton(false)
                .monitor();


        AppRate.showRateDialogIfMeetsConditions(this);


        Indus = findViewById(R.id.indus);
        Budh = findViewById(R.id.budh);
        Delhi = findViewById(R.id.delhi);
        Revolt = findViewById(R.id.revolt);
        Swadeshi = findViewById(R.id.swadeshi);
        Independence = findViewById(R.id.modern);


        Indus.setOnClickListener(this);
        Budh.setOnClickListener(this);
        Delhi.setOnClickListener(this);
        Revolt.setOnClickListener(this);
        Swadeshi.setOnClickListener(this);
        Independence.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {



            case R.id.indus:
                intent = new Intent(MainActivity.this, IndusQuizActivity.class);
                startActivity(intent);
                break;



        }

    }


    }