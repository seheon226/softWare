package com.example.myapplication.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class learn04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn04);
    }
    public void onBack4Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),LearnActivity.class);
        startActivity(intent);
    }
    public void onForward4Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),Learn4Activity.class);
        startActivity(intent);
    }
}
