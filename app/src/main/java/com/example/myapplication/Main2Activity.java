package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.game.game_menu;
import com.example.myapplication.learn.LearnActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onButton6Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
        startActivity(intent);
    }
    public void onButton7Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), game_menu.class);
        startActivity(intent);
    }
    public void onButton8Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(intent);
    }
    public void onButton9Clicked(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
