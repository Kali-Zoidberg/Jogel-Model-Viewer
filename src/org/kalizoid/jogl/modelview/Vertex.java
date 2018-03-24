package org.kalizoid.jogl.modelview;

enum PolygonType {
	 QUAD,TRIANGNLE;
}
public class Vertex {
	
	private float x,y,z,nx,ny,nz;
	private PolygonType polytype;
	
	/**
	 * Default constructor for Vertex.
	 * @param x X coordinate of the Vertex.
	 * @param y Y coordinate of the Vertex.
	 * @param z Z coordinate of the Vertex.
	 * @param nx The normal of the X coordinate.
	 * @param ny The normal of the Y coordinate.
	 * @param nz The normal of the Z coordinate.
	 */
	
	Vertex(float x, float y, float z, float nx, float ny, float nz) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setNx(nx);
		this.setNy(ny);
		this.setNz(nz);
	
	}
	
	/**
	 * Returns the X coordinate of the Vertex.
	 * @return Returns the X of the Vertex.
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Sets the X coordinate of the Vertex.
	 * @param x The X coordinate.
	 */
	
	public void setX(float x) {
		this.x = x;
	}
	
	
	/**
	 * Returns the Y coordinate.
	 * @return Returns the Y coordinate of the vertex.
	 */
	
	public float getY() {
		return y;
	}
	
	
	/**
	 * Sets the Y coordinate of the vertex.
	 * @param y Sets the Y coordinate.
	 */
	
	public void setY(float y) {
		this.y = y;
	}
	
	
	/**
	 * Returns the Z coordinate.
	 * @return Returns the Z coordinate of the vertex.
	 */
	
	public float getZ() {
		return z;
	}
	
	
	/**
	 * Sets the Z coordinate of the vertex.
	 * @param z The z coordinate
	 */
	public void setZ(float z) {
		this.z = z;
	}
	
	/**
	 * Prints out the x,y,z coordinates of the Vertex.
	 */
	public void printVertex() {
		System.out.printf("\nx: %f y: %f z: %f \n", x, y, z);
		System.out.printf("nx: %f ny: %f nz: %f \n", nx, ny, nz);
		System.out.printf(" type" + polytype);
	}
	/**
	 * Returns the type of polygon that the vertex contributes to.
	 * @return Returns PolyType.QUAD if it the vertex is part of a Quadrilateral and returns PolygonType.TRIANGLE if the vertex is part of a triangle.
	 */

	public PolygonType getPolytype() {
		return polytype;
	}

	/**
	 * Sets the type of polygon that the vertex connects to.
	 * @param polytype Set this to polytype.QUAD if it is a Quadrilateral. Set it to Triangle if the vertex connects to a Triangle.
	 */
	public void setPolytype(PolygonType polytype) {
		this.polytype = polytype;
	}
	
	/**
	 * Returns the normal X coordinate.
	 * @return Returns the normal X coordinate.
	 */
	
	public float getNx() {
		return nx;
	}

	/**
	 * Sets the normal X coordinate. 
	 * @param nz The new normal X coordinate.
	 */
	
	public void setNx(float nx) {
		this.nx = nx;
	}

	/**
	 * Returns the normal Y coordinate.
	 * @return Returns the normal Y coordinate.
	 */
	
	public float getNy() {
		return ny;
	}


	/**
	 * Sets the normal Y coordinate. 
	 * @param ny The new normal Y coordinate.
	 */
	
	public void setNy(float ny) {
		this.ny = ny;
	}
	
	/**
	 * Returns the normal Z coordinate.
	 * @return Returns the normal Z coordinate.
	 */

	public float getNz() {
		return nz;
	}
	
	
	/**
	 * Sets the normal Z coordinate. 
	 * @param nz The new normal Z coordinate.
	 */
	
	public void setNz(float nz) {
		this.nz = nz;
	}
	

}
