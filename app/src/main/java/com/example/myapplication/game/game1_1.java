package com.example.myapplication.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class game1_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_1);
    }
    public void onButton9Clicked(View v) {

        Intent intent = new Intent(getApplicationContext(), game1_2.class);
        startActivity(intent);
    }
}
