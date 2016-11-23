package com.example.myapplication.learn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

import opengles.GLRenderer;
import yuku.ambilwarna.AmbilWarnaDialog;
//import yuku.

public class Learn1Activity extends Activity implements AdapterView.OnItemSelectedListener {
    float oldx,oldy;
    final static float DRAGSPEED=4;
    GLSurfaceView surfaceView;
    float x=0;
    float y=0;
    float z=0;
    String strColor = "#FFFFFF";

    int init_color = Color.parseColor(strColor);
    TextView text;

    TextView selection;
    String[] ar = { "삼각형", "사각형", "사각뿔", "육면체" };

    GLRenderer renderer = new GLRenderer(this);

    public void onButtonBack(View v) {
        Intent intent = new Intent(getApplicationContext(), learn01.class);
        startActivity(intent);
    }
    public void onHomeClicked(View v){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new GLSurfaceView(this);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        surfaceView.setRenderer(renderer);
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setContentView(surfaceView);

        //setContentView(R.layout.activity_learn1);

        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v  = (View)vi.inflate(R.layout.activity_learn1, null);
        this.addContentView(v, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,FrameLayout.LayoutParams.FILL_PARENT ));

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ar);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sp = (Spinner) findViewById(R.id.spinner1);
        //sp.setPrompt("shape");

        sp.setAdapter(a);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
               /* Toast.makeText(getApplicationContext(), ar[position],
                        Toast.LENGTH_SHORT).show();*/
                //int color = Color.parseColor(strColor);
                text = (TextView)findViewById(R.id.textView1);
                if (ar[position].equals("사각형")) {
                    renderer.option = "2";
                    text.setText("변의 수: 4개");
                    surfaceView.requestRender();
                } else if (ar[position].equals("삼각형")) {
                    renderer.option = "1";
                    text.setText("변의 수: 3개");
                    surfaceView.requestRender();
                } else if (ar[position].equals("사각뿔")) {
                    renderer.option = "3";
                    text.setText("변의 수: 8개");
                    surfaceView.requestRender();
                } else if (ar[position].equals("육면체")) {
                    renderer.option = "4";
                    text.setText("변의 수: 12개");
                    surfaceView.requestRender();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.color1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_Pick();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x+=0.1;
                y+=0.1;
                z+=0.1;
                renderer.setScale(x,y,z);
                surfaceView.requestRender();
            }
        });
        /*findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                surfaceView.requestRender();
            }
        });*/
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x-=0.1;
                y-=0.1;
                z-=0.1;
                renderer.setScale(x,y,z);
                surfaceView.requestRender();
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
