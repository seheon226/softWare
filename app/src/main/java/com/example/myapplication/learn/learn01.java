package com.example.myapplication.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class learn01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn01);
    }
    public void onBackClicked(View v){
        Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
        startActivity(intent);
    }

    public void onForwardClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), Learn1Activity.class);
        startActivity(intent);
    }
}
