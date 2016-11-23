package com.example.myapplication.game;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import java.util.Random;

public class game1_2 extends AppCompatActivity {

    int score = 0;
    int stage = 0;
    EditText answer1;            //정답쓰는곳
    TextView score_text;         //점수표시
    TextView stage_text;         //정답 이팩트
    String answer[] = {"2","4","18","4","7","8","2","24","6","11","10","1","6","4","6","4","12","8","2","3","5","2","4","2","3","8"};
    Button putText1;              //입력 버튼
    Runnable mMyTask;
    Handler mHandler;
    Handler handler;
    TextView count_text;            //남은시간 표시
    int value =60;  //게임시간
    Thread t;
    View v;
    ImageView img;
    int i, q=0;
    int num[] = new int[100];
    Random mRandom;
    int imgs[] ={R.drawable.a01,R.drawable.a02,R.drawable.a03,R.drawable.a04,R.drawable.a05,R.drawable.a06,R.drawable.a07
            ,R.drawable.a08,R.drawable.a09,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14
            ,R.drawable.a15,R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21
            ,R.drawable.a22,R.drawable.a23,R.drawable.a24,R.drawable.a25,R.drawable.a26};
    String tableName;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_2);

        putText1 = (Button) findViewById(R.id.putText1);
        answer1 = (EditText) findViewById(R.id.answer1);
        score_text = (TextView) findViewById(R.id.score_text);
        stage_text = (TextView) findViewById(R.id.stage_text);
        count_text = (TextView) findViewById(R.id.count_text);
        stage_text.setVisibility(View.INVISIBLE);
        img = (ImageView) findViewById(R.id.img);
        mRandom = new Random();

        String databaseName = "membership.db";
        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        MyApplication myApp = (MyApplication) getApplication();

        tableName = myApp.getGlobalString(); // 사용자 아이디 집어넣는곳
        database.execSQL("CREATE TABLE if not exists " + tableName + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "id text, "
                + "scr text"
                + ")");

        for(int k = 0; k < num.length; k++) {
            num[k] = mRandom.nextInt(26) + 1;
        }
        img.setImageResource(imgs[num[stage]]);

        mHandler = new Handler();

        handler = new Handler() {

            public void handleMessage(Message msg) {

                count_text.setText("남은 시간 : " + value + "초");
                if (value == 0) { //시간종료
                    Cursor cursor = database.rawQuery("SELECT id, scr FROM " + tableName, null);

                    int count = cursor.getCount();
                    cursor.moveToNext();
                    String id = cursor.getString(0);
                    int scr = cursor.getInt(1);
                    cursor.close();
                    if (scr < score) {
                        database.execSQL("UPDATE " + tableName + " SET scr = " + score + ";");
                    }
                    Intent intent = new Intent(getApplicationContext(), game1_3.class); //다음화면으로 전환
                    startActivity(intent);
                }
            }
        };

        mMyTask = new Runnable() {
            @Override
            public void run() {
                stage_text.setVisibility(View.INVISIBLE);   //정답 글자 숨기기
            }
        };

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (value > -1) { //0초까지 돌리기
                    try {
                        Thread.sleep(1000); // 1초대기
                        handler.sendMessage(handler.obtainMessage());
                        value--; //1초씩감소
                    } catch (InterruptedException e) {
                    }
                } // end of while
            }
        });
        t.start(); // 시간 카운트 시작

        putText1.setOnClickListener(new View.OnClickListener() {        //입력 버튼 이벤트
            public void onClick(View v) {
                ////////////////////////////////////////////////stage4
                if (num[stage] == 26) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 25) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 24) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 23) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 22) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 21) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                if (num[stage] == 20) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 19) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 18) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 17) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 16) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 15) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 14) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 13) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 12) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 11) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 10) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 9) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 8) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 7) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 6) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 5) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage4
                if (num[stage] == 4) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage3
                if (num[stage] == 3) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage2
                if (num[stage] == 2) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
                ////////////////////////////////////////////////stage1
                if (num[stage] == 1) {
                    if (answer[num[stage]].equals(answer1.getText().toString())) {    //정답일 경우
                        stage_text.setVisibility(View.VISIBLE); //정답 글자 보이기
                        stage_text.setText("정답!!!!!");
                        YoYo.with(Techniques.Tada).duration(700).playOn(stage_text);    //글자 진동
                        score++;     //점수 1점 추가
                        stage++;
                        score_text.setText("점수 : " + score);    //점수 추가 텍스트 반영
                        mHandler.postDelayed(mMyTask, 2000); // 정답 글자 3초 대기 후 숨김
                        img.setImageResource(imgs[num[stage]]);
                    } else if (!answer[num[stage]].equals(answer1.getText().toString())) //오답일 경우
                        Toast.makeText(game1_2.this, "다시 풀어보세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}