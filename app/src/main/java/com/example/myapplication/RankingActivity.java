package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RankingActivity extends AppCompatActivity {
    SQLiteDatabase database;
    TextView TextView6, TextView7, TextView8, TextView9, TextView10;
    TextView score1, score2, score3, score4, score5;
    String tableName, tableName2;
    String[] rankid = {"null", "null,", "null", "null", "null"}, rankid2 = {"null"};
    int[] rankscr = {0, 0, 0, 0, 0}, rankscr2 = {0};
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        TextView6 = (TextView) findViewById(R.id.textView6);
        TextView7 = (TextView) findViewById(R.id.textView7);
        TextView8 = (TextView) findViewById(R.id.textView8);
        TextView9 = (TextView) findViewById(R.id.textView9);
        TextView10 = (TextView) findViewById(R.id.textView10);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4 = (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);
        String databaseName = "membership.db";
        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        tableName = "rank1";
        MyApplication myApp = (MyApplication) getApplication();
        userid = myApp.getGlobalString();

        Cursor cursor = database.rawQuery("SELECT id, scr FROM " + tableName, null);

        int count = cursor.getCount();

        for(int i = 0; i < 5; i++) {
            cursor.moveToNext();
            String id = cursor.getString(0);
            int scr = cursor.getInt(1);
            rankid[i] = id;
            rankscr[i] = scr;
        }
        cursor.close();
        tableName2 = userid; // 여기가 사용자 아이디 집어넣는곳
        Cursor cursor2 = database.rawQuery("SELECT id, scr FROM " + tableName2, null);

        cursor2.moveToNext();
        String id = cursor2.getString(0);
        int scr = cursor2.getInt(1);
        rankid2[0] = id;
        rankscr2[0] = scr;
        cursor2.close();
        for(int j =0; j<5; j++)
        {
            if(rankscr2[0] > rankscr[j])
            {
                for(int k=4; k >j; k--)
                {
                    rankid[k] = rankid[k-1];
                    rankscr[k] = rankscr[k-1];
                }
                rankid[j] = rankid2[0];
                rankscr[j] = rankscr2[0];
                break;
            }
        }

        TextView6.setText("점수 : "+rankscr[0]);
        TextView7.setText("점수 : "+rankscr[1]);
        TextView8.setText("점수 : "+rankscr[2]);
        TextView9.setText("점수 : "+rankscr[3]);
        TextView10.setText("점수 : "+rankscr[4]);
        score1.setText("아이디 : "+rankid[0]);
        score2.setText("아이디 : "+rankid[1]);
        score3.setText("아이디 : "+rankid[2]);
        score4.setText("아이디 : "+rankid[3]);
        score5.setText("아이디 : "+rankid[4]);
        database.execSQL("DELETE FROM "+tableName+";");
        database.execSQL("CREATE TABLE if not exists " + tableName + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "id text, "
                + "scr integer"
                + ")");
        database.execSQL("INSERT INTO " + tableName + "(id, scr) VALUES"
                + "(" + "'" + rankid[0] + "'" + "," + rankscr[0] + ")");
        database.execSQL("INSERT INTO " + tableName + "(id, scr) VALUES"
                + "(" + "'" + rankid[1] + "'" + "," + rankscr[1] + ")");
        database.execSQL("INSERT INTO " + tableName + "(id, scr) VALUES"
                + "(" + "'" + rankid[2] + "'" + "," + rankscr[2] + ")");
        database.execSQL("INSERT INTO " + tableName + "(id, scr) VALUES"
                + "(" + "'" + rankid[3] + "'" + "," + rankscr[3] + ")");
        database.execSQL("INSERT INTO " + tableName + "(id, scr) VALUES"
                + "(" + "'" + rankid[4] + "'" + "," + rankscr[4] + ")");
    }
    public void onButton12Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
