package opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {
	float vert[] = { 0, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f };
	FloatBuffer vertbuf;

	FloatBuffer light_a;
	FloatBuffer light_d;
	FloatBuffer light_p;

	private float[] lightAmbient = { 0.5f, 0.5f, 0.5f, 1.0f };
	private float[] lightDiffuse = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float[] lightPosition = { 0.0f, 0.0f, 2.0f, 1.0f };

	public Triangle() {
		vertbuf = ArrayToBuffer(vert);

		light_a = ArrayToBuffer(lightAmbient);
		light_d = ArrayToBuffer(lightDiffuse);
		light_p = ArrayToBuffer(lightPosition);

	}

	public FloatBuffer ArrayToBuffer(float[] ar) {
		ByteBuffer bytebuf = ByteBuffer.allocateDirect(ar.length * 4);
		bytebuf.order(ByteOrder.nativeOrder());
		FloatBuffer buf = bytebuf.asFloatBuffer();
		buf.put(ar);
		buf.position(0);
		return buf;
	}

	public void draw(GL10 gl, float R, float G, float B) {

		gl.glColor4f(R,G,B,1);
		gl.glFrontFace(GL10.GL_CW);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertbuf);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, light_a);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, light_d);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, light_p);

		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 180);
		gl.glMaterialf(GL10.GL_BACK, GL10.GL_SHININESS, 180);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 180);

	}
}