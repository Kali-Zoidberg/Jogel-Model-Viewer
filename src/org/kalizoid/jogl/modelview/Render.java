package org.kalizoid.jogl.modelview;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
/**
 * TODO: Either extend render to include models or give Render 
 * a constructor Render(ArrayList<Vertex> vertex_list) and then modify
 * display to reflect displayModel's algorithm
 * @author Chow
 *
 */
public class Render implements GLEventListener {
	
	private GLU glu = new GLU();
	/**
	 * Renders a model with the specified vertex_list
	 * @param glDrawable 
	 * @param vertex_list The vertex list to render.
	 */
	public void displayModel(GLAutoDrawable glDrawable, ArrayList <Vertex> vertex_list) {
		final GL2 gl = glDrawable.getGL().getGL2();
		int i = 1;
		float x,y,z = 0;
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Provides a bitwise operation to create a color buffer and depth buffer.
		gl.glLoadIdentity(); //reset the view
		gl.glTranslatef(-1.5f, 0.0f, -6f); //Places the object onto the screen
		
		/**
		 * Loops throughout the vertex list. After the 3rd vertex, a new triangle is rendered
		 */
		gl.glBegin(GL2.GL_TRIANGLES);
		
		while (i <= vertex_list.size()) { 
			/*if (i % 3 == 0) {
				
				if (i > 0)
					gl.glEnd();
				
				gl.glBegin(GL2.GL_TRIANGLES);
				
			}*/
			x = vertex_list.get(i - 1).getX();
			y = vertex_list.get(i - 1).getY();
			z = vertex_list.get(i - 1).getZ();
			
			gl.glVertex3f(x, y, z);
			gl.glColor3f((int) (x * 10 * y) % 256, (int) (y * z * 10) % 256, (int) (x * z * 10) % 256);
		}
		gl.glEnd();
		gl.glFlush();
	}
	
	@Override
	public void display(GLAutoDrawable glDrawable) {
			final GL2 gl = glDrawable.getGL().getGL2();
			
			
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
			gl.glLoadIdentity();
			gl.glTranslatef(-1.5f, 0.0f, -6f);
			gl.glBegin(GL2.GL_TRIANGLES);
			gl.glVertex3f(0.0f, 1.0f, 0.0f);
			gl.glColor3f(1.0f, 0.0f, 0.0f);
			gl.glVertex3f(-1.0f,-1.0f,0.0f);
			gl.glColor3f(255, 255, 255);
			gl.glVertex3f(1.0f,-1.0f,0.0f);
			gl.glEnd();
			gl.glTranslatef(3.0f,0.0f,0.0f);
			
			gl.glBegin(GL2.GL_QUADS); //Begin drawing a square
			gl.glVertex3f(-1.0f, 1.0f, 0.0f);
			gl.glVertex3f(1.0f,1.0f,0.0f);
			gl.glVertex3f(1.0f,-1.0f,0.0f);
			gl.glVertex3f(1.0f,-1.0f,0.0f);
			gl.glVertex3f(-1.0f, -1.0f,0.0f);
			gl.glEnd();
			gl.glFlush();
			
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
			System.out.println("Disposed called");
	}

	@Override
	public void init(GLAutoDrawable glDrawable) {

		System.out.println("initializing object");
		GL2 gl = glDrawable.getGL().getGL2();
		//gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
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
