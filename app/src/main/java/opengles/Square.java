package opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
	float vert[] = { -0.5f, 0.5f, 0.0f, // 0, Left Top
			-0.5f, -0.5f, 0.0f, // 1, Right Top
			0.5f, -0.5f, 0.0f, // 2, Right Bottom
			0.5f, 0.5f, 0.0f // 3, Left Bottom
	};

	byte[] index = { 0, 1, 2, 0, 2, 3 };

	FloatBuffer vertbuf;
	ByteBuffer indexbuf;

	FloatBuffer light_a;
	FloatBuffer light_d;
	FloatBuffer light_p;

	private float[] lightAmbient = { 0.1f, 0.3f, 0.3f, 0.1f };
	private float[] lightDiffuse = { 0.7f, 0.7f, 0.7f, 1.0f };
	private float[] lightPosition = { 2.0f, 2.0f, 2.0f, 2.0f };

	public FloatBuffer ArrayToBuffer(float[] ar) {
		ByteBuffer bytebuf = ByteBuffer.allocateDirect(ar.length * 4);
		bytebuf.order(ByteOrder.nativeOrder());
		FloatBuffer buf = bytebuf.asFloatBuffer();
		buf.put(ar);
		buf.position(0);
		return buf;
	}

	ByteBuffer getByteBufferFromByteArray(byte array[]) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}

	public Square() {
		vertbuf = ArrayToBuffer(vert);
		indexbuf = getByteBufferFromByteArray(index);

		light_a = ArrayToBuffer(lightAmbient);
		light_d = ArrayToBuffer(lightDiffuse);
		light_p = ArrayToBuffer(lightPosition);
	}

	public void draw(GL10 gl, float R, float G, float B) {

		gl.glColor4f(R,G,B,1);

		gl.glFrontFace(GL10.GL_CW);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertbuf);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDrawElements(GL10.GL_TRIANGLES, index.length,
				GL10.GL_UNSIGNED_BYTE, indexbuf);
		// gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, light_a);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, light_d);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, light_p);

		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 180);
		gl.glMaterialf(GL10.GL_BACK, GL10.GL_SHININESS, 180);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 180);

	}
}
