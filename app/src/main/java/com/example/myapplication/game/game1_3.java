package com.example.myapplication.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class game1_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_3);
    }
    public void onButton10Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game_menu.class);
        startActivity(intent);
    }
    public void onButton11Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game1_1.class);
        startActivity(intent);
    }

}
