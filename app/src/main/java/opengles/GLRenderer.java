package opengles;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GLRenderer implements Renderer {
	//LINE_Cube line_cube;
	Triangle triangle;
	Square square;
	Cube cube;
	Pyramid pyramid;
	Sphere sphere;
	Circle circle;
	private final float[] mCurrentRotation = new float[16];
	float x,y,z;
	float R_set, G_set, B_set;
	public float xAngle;
	public float yAngle;
	public String option = "1";

	Context context;
	public volatile float mDeltaX;
	public volatile float mDeltaY;

	public GLRenderer(Context context) {

		this.context = context;

		cube = new Cube();
		triangle = new Triangle();
		square = new Square();
		pyramid = new Pyramid();
		sphere = new Sphere();
		circle = new Circle();
		//line_cube=new LINE_Cube();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		gl.glClearColor(1.0f, 1.0f, 1.0f, 0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// texture
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glShadeModel(GL10.GL_SMOOTH);

		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_LIGHTING);

		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotatef(xAngle, 1, 0, 0);
		gl.glRotatef(yAngle, 0, 1, 0);

		gl.glScalef(1.0f + x,1.0f+ y,1.0f+z);
		//Matrix.setIdentityM(mCurrentRotation, 0);
	//	Matrix.rotateM(mCurrentRotation, 0, mDeltaX, 0.0f, 1.0f, 0.0f);
		//Matrix.rotateM(mCurrentRotation, 0, mDeltaY, 1.0f, 0.0f, 0.0f);
		mDeltaX = 0.0f;
		mDeltaY = 0.0f;


		gl.glMatrixMode(GL10.GL_MODELVIEW);
		if (option.equals("1"))
			triangle.draw(gl, R_set, G_set, B_set);
		else if (option.equals("2"))
			square.draw(gl, R_set, G_set, B_set);
		else if (option.equals("3")) {
			pyramid.draw(gl, R_set, G_set, B_set);
		} else if (option.equals("4")) {
			cube.draw(gl, R_set, G_set, B_set);
		}else if (option.equals("5")) {
			sphere.draw(gl, R_set, G_set, B_set);
		}else if (option.equals("6")) {
			triangle.draw(gl, R_set, G_set, B_set);
		}else if (option.equals("7")) {
			square.draw(gl, R_set, G_set, B_set);
		}else if (option.equals("8")) {
			circle.draw(gl, R_set, G_set, B_set);
		}
		gl.glLoadIdentity();

	}
	public void setScale(float x,float y,float z){
		this.x =x;
		this.y=y;
		this.z=z;
	}

	public void setColor(String color) {
		String R = color.substring(2, 4);
		String G = color.substring(4, 6);
		String B = color.substring(6, 8);

		long R_l = Long.parseLong(R, 16);
		long G_l = Long.parseLong(G, 16);
		long B_l = Long.parseLong(B, 16);

		float R_f = (float) R_l;
		float G_f = (float) G_l;
		float B_f = (float) B_l;

		this.R_set = R_f / 255;
		this.G_set = G_f / 255;
		this.B_set = B_f / 255;

	}

}
