package com.example.myapplication.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
    }
    public void onButtonBack(View v) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
    public void onButton1Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), learn01.class);
        startActivity(intent);
    }
    public void onButton2Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), learn02.class);
        startActivity(intent);
    }
    public void onButton3Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), learn03.class);
        startActivity(intent);
    }
    public void onButton4Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), learn04.class);
        startActivity(intent);
    }
}
