package com.base.engine;

public class Vector2f {

	private float x;
	private float y;	
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float lenght(){
		return (float)Math.sqrt(x * x + y * y);
		
	}
	
	public float dot(Vector2f v){
		return x * v.getX() + y * v.getY();
	}
	
	public Vector2f normalize(){
		float len = lenght();
		x /= len;
		y /= len;
		return this;
	}
	
	public Vector2f rotate(float angle){
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f( (float)(cos * x - sin * y) , (float)(sin * x + cos * y));
	}

	
	public Vector2f add(Vector2f v){
		return new Vector2f(x + v.getX(), y + v.getY());
	}
	

	public Vector2f add(float v){
		return new Vector2f(v + x, v + y);
	}

	
	public Vector2f sub(Vector2f v){
		return new Vector2f(x - v.getX(), y - v.getY());
	}
	

	public Vector2f sub(float v){
		return new Vector2f(x - v, y - v);
	}

	
	public Vector2f mul(Vector2f v){
		return new Vector2f(x * v.getX(), y * v.getY());
	}
	

	public Vector2f mul(float v){
		return new Vector2f(v * x, v * y);
	}

	public Vector2f div(Vector2f v){
		return new Vector2f(x / v.getX(), y / v.getY());
	}
	

	public Vector2f div(float v){
		return new Vector2f(x / v, y / v);
	}

	
	public String toString(){
		return "(" + x + " " + y + ")";
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


	
	
}
