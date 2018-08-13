package org.kalizoid.jogl.modelview;
import java.util.ArrayList;
/**
 * TODO: Either extend render to include models or give Render 
 * a constructor Render(ArrayList<Vertex> vertex_list) and then modify
 * display to reflect displayModel's algorithm
 * @author Chow
 *
 */


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Render implements GLEventListener {
	
	private GLU glu = new GLU();
	
	
	@Override
	public void display(GLAutoDrawable glDrawable) {
		System.out.println("Display called");	
			
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
			System.out.println("Disposed called");
	}

	@Override
	public void init(GLAutoDrawable glDrawable) {

		System.out.println("initializing object");
		GL2 gl = glDrawable.getGL().getGL2();
		gl.glShadeModel( GL2.GL_SMOOTH );
	    gl.glClearColor( 0f, 0f, 0f, 0f );
	    gl.glClearDepth( 1.0f );
	    gl.glEnable( GL2.GL_DEPTH_TEST );
	    gl.glDepthFunc( GL2.GL_LEQUAL );
	    gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
		
	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
		
		System.out.printf("Reshaping object. x: %d, y: %d \n with width: %d, height: %d",x,y,width,height);
		
		final GL2 gl = glDrawable.getGL().getGL2();
		
		if (height <= 0) { //prevents division by 0
			height = 1;
		}
		
		final float scaled_ratio = (float) width/ (float) height; //Creates a ratio between width and height.
		
		gl.glViewport(0,0, width, height); // Sets the viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, scaled_ratio, 1.0, 20.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

}
