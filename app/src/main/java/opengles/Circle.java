package opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by heejin on 2016-11-15.
 */
public class Circle {

    private final static float GL_PI = 3.1415f;

    private float[] mVertex;
    private float[] mNormal;
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mNormalBuffer;

    private float mInterval;

    FloatBuffer light_a;
    FloatBuffer light_d;
    FloatBuffer light_p;
    FloatBuffer light_di;

    private float[] lightAmbient = { 0.1f,0.3f, 0.3f,0.1f};
    private float[] lightDiffuse = { 0.7f,0.7f,0.7f,1.0f};
    private float[] lightPosition = { 2.0f,2.0f,2.0f,2.0f};
    private float[] lightDirection = { 1.0f,1.0f,1.0f};

    public FloatBuffer ArrayToBuffer(float[] ar){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(ar.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buf= byteBuffer.asFloatBuffer();
        buf.put(ar);
        buf.position(0);
        return buf;
    }
    public Circle() {

        int num = 36;
        mVertex = new float[num * 3];
        mNormal = new float[num * 3];

        mInterval = (2 * GL_PI) / num;

        for (int i = 0; i < num; i++) {
            mVertex[i * 3 + 0] = (float) Math.sin(mInterval * i);
            mVertex[i * 3 + 0] = (float) Math.sin(mInterval * i);
            mVertex[i * 3 + 1] = (float) Math.cos(mInterval * i);
            mVertex[i * 3 + 1] = (float) Math.cos(mInterval * i);
            mVertex[i * 3 + 2] = 0;
            mVertex[i * 3 + 2] = 0;
        }
        mVertexBuffer = ArrayToBuffer(mVertex);
        mNormalBuffer = ArrayToBuffer(mNormal);

        light_a = ArrayToBuffer(lightAmbient);
        light_d = ArrayToBuffer(lightDiffuse);
        light_p = ArrayToBuffer(lightPosition);
        light_di = ArrayToBuffer(lightDirection);
    }


    public void draw(GL10 gl, float R, float G, float B) {
        //gl.glDisable(GL10,GL_TEXTURE_2D);
        gl.glColor4f(R,G,B,1);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,mVertexBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT,0,mNormalBuffer);
        gl.glScalef(0.5f,0.3f,0.5f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,mVertex.length/3);

        gl.glLightfv(GL10.GL_LIGHT0,GL10.GL_AMBIENT,light_a);
        gl.glLightfv(GL10.GL_LIGHT0,GL10.GL_DIFFUSE,light_d);
        gl.glLightfv(GL10.GL_LIGHT0,GL10.GL_POSITION,light_p);
        gl.glLightfv(GL10.GL_LIGHT0,GL10.GL_SPOT_DIRECTION,light_di);

        gl.glMaterialf(GL10.GL_FRONT,GL10.GL_SHININESS,180);
        gl.glMaterialf(GL10.GL_BACK,GL10.GL_SHININESS,180);
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK,GL10.GL_SHININESS,180);
    }



}