package com.base.engine;


public class Vertex {
	
	public static final int SIZE = 5;
	
	private Vector3f pos;
	private Vector2f texCood;
	
	public Vertex(Vector3f pos){
		this(pos, new Vector2f(0, 0));
	}

	public Vertex(Vector3f pos, Vector2f texCood){
		this.texCood = texCood;
		this.pos = pos;
	}
	
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector2f getTexCood() {
		return texCood;
	}

	public void setTexCood(Vector2f texCood) {
		this.texCood = texCood;
	}
	
	
	
}
