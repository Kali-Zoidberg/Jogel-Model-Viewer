package org.kalizoid.jogl.modelview;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public class RenderModel extends Render{

	private GLU glu = new GLU();
	private ArrayList<Vertex> vertices_list = null;
	
	RenderModel(ArrayList<Vertex> vertex_list) {
		setVerticesList(vertex_list);
	}
	
	public void display(GLAutoDrawable glDrawable) {
		final GL2 gl = glDrawable.getGL().getGL2();
		
		float x = 0,y = 0,z = 0;
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Provides a bitwise operation to create a color buffer and depth buffer.
		gl.glLoadIdentity(); //reset the view
		gl.glTranslatef(-1.5f, 0.0f, -6f); //Places the object onto the screen
		
		/**
		 * Loops throughout the vertex list. After the 3rd vertex, a new triangle is rendered
		 */
		gl.glBegin(GL2.GL_QUADS);
		
		for (int i = 1; i <= vertices_list.size(); ++i) { 
			/*if (i % 3 == 0) {
				
				if (i > 0)
					gl.glEnd();
				
				gl.glBegin(GL2.GL_TRIANGLES);
				
			}*/
			x = vertices_list.get(i - 1).getX();
			y = vertices_list.get(i - 1).getY();
			z = vertices_list.get(i - 1).getZ();
			
			gl.glVertex3f(x, y, z);
			if (i %4 == 0) 
				gl.glColor3f(((int)Math.random() * 255) + 1, 0, 0);
			if (i % 2 == 0)
				gl.glColor3f(0, ((int)(Math.random() * 3000) + 1) % 255, 0);
			if (i % 3 == 0)
				gl.glColor3f(0, 0, 100);
			//System.out.println((int) (90.0 * y) % 256);
			//System.out.println(y);
			System.out.println((int) (Math.random() * 256) + 1);
		}
		gl.glEnd();
		gl.glFlush();

}

	public ArrayList<Vertex> getVerticesList() {
		return vertices_list;
	}

	public void setVerticesList(ArrayList<Vertex> vertices_list) {
		this.vertices_list = vertices_list;
	}

}
