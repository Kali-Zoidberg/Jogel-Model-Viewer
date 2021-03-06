package org.kalizoid.jogl.modelview;

public class Point3D {

	private double x;
	private double y;
	private double z;
	
	Point3D(double x, double y, double z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		
	}
	
	Point3D()
	{
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}

	Point3D(Point3D point)
	{
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	
}
