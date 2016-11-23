package com.example.myapplication.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class learn03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn03);
    }
    public void onBack3Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),LearnActivity.class);
        startActivity(intent);
    }
    public void onForward3Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),Learn3Activity.class);
        startActivity(intent);
    }
}
