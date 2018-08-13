package org.kalizoid.jogl.modelview;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public class RenderModel extends Render{

	private GLU glu = new GLU();
	private ArrayList<Vertex> vertices_list = null;
	private Point4D rotation = new Point4D(0, 0.0,0.0,0.0);
	
	RenderModel(ArrayList<Vertex> vertex_list) {
		setVerticesList(vertex_list);
	}
	
	public void display(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();
		
		gl.glEnable(GL2.GL_LIGHTING); 
		gl.glEnable(GL2.GL_LIGHT0);  
		gl.glEnable(GL2.GL_NORMALIZE); 
		gl.glEnable(GL2.GL_CULL_FACE);
	
		float[] ambientLight = { 0.1f, 0.f, 0.f,0f };  // weak RED ambient 
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0); 

		float[] diffuseLight = { 1f,2f,1f,0f };  // multicolor diffuse 
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0); 
		
		float x = 0,y = 0,z = 0, nx = 0, ny = 0, nz = 0;
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Provides a bitwise operation to create a color buffer and depth buffer.
		gl.glLoadIdentity(); //reset the view
		gl.glTranslatef(-1.5f, 0.0f, -6f); //Places the object onto the screen
	    gl.glRotatef( (float) rotation.getW(), 
	    		(float) rotation.getX(), 
	    		(float) rotation.getY(), 
	    		(float) rotation.getZ()); // Rotate The Cube On X, Y & Z
	    
	    gl.glBegin(GL2.GL_TRIANGLES);

		/**
		 * Loops throughout the vertex list. After the 3rd vertex, a new triangle is rendered
		 */
		
		for (int i = 1; i < vertices_list.size(); ++i) { 
			/*if (i % 3 == 0) {
				
				if (i > 0)
					gl.glEnd();
				
				gl.glBegin(GL2.GL_TRIANGLES);
				
			}*/
			x = vertices_list.get(i - 1).getX();
			y = vertices_list.get(i - 1).getY();
			z = vertices_list.get(i - 1).getZ();
			nx = vertices_list.get(i - 1).getNx();
			ny = vertices_list.get(i - 1).getNy();
			nz = vertices_list.get(i - 1).getNz();
			gl.glNormal3f(nx, ny, nz);
			gl.glVertex3f(x, y, z);
			
			/**
			 * Generates a 'random' color for the model. Needs adjustment.
			 */
			if (i %5 == 0) 
				gl.glColor3f(((int)Math.random() * 255) + 1, 0, 0);
			if (i % 3 == 0)
				gl.glColor3f(0, ((int)(Math.random() * 3000) + 1) % 255, 0);
		
		}
		gl.glLoadIdentity(); //reset the view

		gl.glEnd();
		gl.glFlush();

	}
	
	/**
	 * Returns a list of the vertices of the model.
	 * @return Returns the list of vertices of the model.
	 */
	public ArrayList<Vertex> getVerticesList() {
		return vertices_list;
	}
	/**
	 * Loads the vertices for the model.
	 * @param vertices_list
	 */
	public void setVerticesList(ArrayList<Vertex> vertices_list) {
		this.vertices_list = vertices_list;
	}
	
	public Point3D getRotation()
	{
		return rotation;
	}
	/**
	 * Sets the rotation of the model.
	 * @param rotation Rotates the model.
	 */
	public void setRotation(Point4D rotation)
	{
		this.rotation = new Point4D(rotation);
	}

}
