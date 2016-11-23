package com.example.myapplication.game;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Random;

import opengles.GLRenderer;
import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by USER on 2016-11-14.
 */

public class game4_2 extends AppCompatActivity {
    float oldx,oldy;
    final static float DRAGSPEED=4;
    Thread t;
    GLSurfaceView surfaceView;
    GLRenderer renderer = new GLRenderer(this);
    Random mRandom;
    int i;
    String st;
    String strColor = "#FFFFFF";
    int init_color = Color.parseColor(strColor);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRandom=new Random();
        i=mRandom.nextInt(3)+3;//난수 생성
        st=Integer.toString(i); //옵션 선택
        surfaceView = new GLSurfaceView(this);
        renderer.option=st;//렌더링 옵션 1:삼각형 2:사각 3사각뿔 4육면체 5구 6삼각형 7 사각형 8원

        surfaceView.setRenderer(renderer);
        //surfaceView.requestRender();
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        float x = 0;
        float y = 0;
        float z = 0;
        setContentView(surfaceView);

        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = (View) vi.inflate(R.layout.activity_game4_2, null);
        addContentView(v, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

        findViewById(R.id.color1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_Pick();
            }
        });

        Button putText4 = (Button)findViewById(R.id.putText4);
        putText4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                EditText answer4 = (EditText)findViewById(R.id.answer4);
                String correct4 = "정답";

                if(correct4.equals(answer4.getText().toString()))
                    Toast.makeText(getApplicationContext(), "정답!!!!!", Toast.LENGTH_SHORT).show();
                else if(answer4.getText().toString().length()==0)
                    Toast.makeText(getApplicationContext(), "정답이 뭘까요?", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "다시 풀어보세요", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void color_Pick() {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, init_color,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        String color2 = Integer.toHexString(color);
                        renderer.setColor(color2);
                        init_color = color;
                        surfaceView.requestRender();
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // cancel was selected by the user
                    }
                });

        dialog.show();

    }
    public boolean onTouchEvent(MotionEvent event){

        float x = event.getX();
        float y= event.getY();

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            float dx=(oldx-x)/DRAGSPEED;
            float dy=(oldy-y)/DRAGSPEED;

            renderer.yAngle += dx;
            renderer.xAngle+= dy;

            surfaceView.requestRender();
        }

        oldx=x;
        oldy=y;

        return true;
    }
}