package org.kalizoid.jogl.modelview;

public class Point4D extends Point3D{
	
	private double w;
	Point4D(double x, double y, double z, double w)
	{
		super(x,y,z);
		this.setW(w);
		
	}
	
	Point4D()
	{
		super();
		this.setW(0);
	}
	Point4D(Point4D point)
	{
		super(point);
		this.w = point.getW();
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
}
