package org.kalizoid.jogl.modelview;

public class Vertex {
	
	private float x,y,z;
	
	Vertex(float x, float y, float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public void printVertex() {
		System.out.printf("x: %f y: %f z: %f \n",x,y,z);
	}
	

}
