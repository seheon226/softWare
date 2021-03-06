package com.example.myapplication.learn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class Learn4Activity extends Activity implements AdapterView.OnItemSelectedListener {
    float oldx, oldy;
    final static float DRAGSPEED = 4;
    GLSurfaceView surfaceView;
    float x = 0;
    float y = 0;
    float z = 0;
    String strColor = "#FFFFFF";
    TextView text;
    int init_color = Color.parseColor(strColor);

    TextView selection;
    String[] ar = {"사각뿔", "육면체", "구"};

    GLRenderer renderer = new GLRenderer(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new GLSurfaceView(this);
        surfaceView.setRenderer(renderer);
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setContentView(surfaceView);

        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v  = (View)vi.inflate(R.layout.activity_learn4,null);
        addContentView(v, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ar);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(a);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                text = (TextView)findViewById(R.id.text4);

                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.RED);

                if  (ar[position].equals("사각뿔")) {
                    renderer.option = "3";
                    text.setText("사각뿔의 부피: (밑면의 넓이 * 높이)/3");
                    surfaceView.requestRender();
                } else if (ar[position].equals("육면체")) {
                    renderer.option = "4";
                    text.setText("육면체의 부피: 밑면의 넓이 * 높이");
                    surfaceView.requestRender();
                }  else if (ar[position].equals("구")) {
                    renderer.option = "5";
                    text.setText("구의 부피: (4/3)*반지름*반지름*반지름*3.14");
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

        findViewById(R.id.button44).setOnClickListener(new View.OnClickListener() {
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
        findViewById(R.id.button45).setOnClickListener(new View.OnClickListener() {
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
    public void onBack4Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), learn04.class);
        startActivity(intent);
    }

    public void onForward4Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
