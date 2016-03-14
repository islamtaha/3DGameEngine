package com.base.engine;

public class Vector3f {
	private float x;
	private float y;
	private float z;
	
	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public float lenght(){
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	

	public float dot(Vector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public Vector3f cross(Vector3f v){
		float xComp = y * v.getZ() - z * v.getY();
		float yComp = z * v.getX() - x * v.getZ();
		float zComp = x * v.getY() - y * v.getX();
		
		return new Vector3f(xComp, yComp, zComp);
	}
	
	
	public Vector3f normalize(){
		float len = lenght();
		x /= len;
		y /= len;
		z /= len;
		return this;
	}
	
	
	public Vector3f rotate(float angle, Vector3f axis){
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle/2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle/2));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjegate = rotation.conjugate();
		
		Quaternion w = rotation.mul(this).mul(conjegate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return this;
	}
	
	public Vector3f add(Vector3f v){
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	

	public Vector3f add(float v){
		return new Vector3f(v + x, v + y, z + v);
	}


	public Vector3f sub(Vector3f v){
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	

	public Vector3f sub(float v){
		return new Vector3f(x - v, y - v, z - v);
	}

	
	public Vector3f mul(Vector3f v){
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	

	public Vector3f mul(float v){
		return new Vector3f(v * x, v * y, z * v);
	}

	
	public Vector3f div(Vector3f v){
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}
	

	public Vector3f div(float v){
		return new Vector3f(x/ v, y / v, z / v);
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
	
	

}
