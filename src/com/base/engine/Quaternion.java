package com.base.engine;

public class Quaternion {
	private float x;
	private float y;
	private float z;
	private float w;
	
	public Quaternion(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float getX() {
		return x;
	}

	public float lenght(){
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Quaternion normalize(){
		float len = lenght();
		
		x /= len;
		y /= len;
		z /= len;
		w /= len;
		
		return this;
	}
	
	public Quaternion conjugate(){
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion mul(Quaternion q){
		float wComp = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
		float xComp = x * q.getW() + w * q.getX() + y * q.getZ() - z * q.getY();
		float yComp = y * q.getW() + w * q.getY() + z * q.getX() - x * q.getZ();
		float zComp = w * q.getW() + w * q.getZ() + x * q.getY() - y * q.getX();
		
		return new Quaternion(xComp, yComp, zComp, wComp);
	}
	
	
	public Quaternion mul(Vector3f v){
		float wComp = -x * v.getX() - y * v.getY() - z * v.getZ();
		float xComp = w * v.getX() + y * v.getZ() - z * v.getY();
		float yComp = w * v.getY() + z * v.getX() - x * v.getZ();
		float zComp = w * v.getZ() + x * v.getY() - y * v.getX();
		
		return new Quaternion(xComp, yComp, zComp, wComp);
		
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

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
	
	
}
