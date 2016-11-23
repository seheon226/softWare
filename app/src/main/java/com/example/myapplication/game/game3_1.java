package com.example.myapplication.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.R;

public class game3_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_1);
    }
    public void onButton9Clicked(View v) {

        Intent intent = new Intent(getApplicationContext(), game3_2.class);
        startActivity(intent);
    }
}
