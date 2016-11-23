package com.example.myapplication.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

public class game_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }
    public void onButton15Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game1_1.class);
        startActivity(intent);
    }
    public void onButton16Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game2_1.class);
        startActivity(intent);
    }
    public void onButton17Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game3_1.class);
        startActivity(intent);
    }
    public void onButton18Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game4_1.class);
        startActivity(intent);
    }
    public void onButtonBack(View v){
        Intent intent= new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
}
