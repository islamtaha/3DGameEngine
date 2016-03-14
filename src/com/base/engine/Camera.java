package com.base.engine;

public class Camera {
	
	private static final Vector3f yAxis = new Vector3f(0, 1, 0);
	
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	
	public Camera(){
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	
	
	public void input(){
		float senstivty = 0.005f;
		float moveAmount = (float) (10 * Time.getDelta());
	//	float rotAmount = (float) (100 * Time.getDelta());
		
		
		
		if(Input.getKey(Input.KEY_ESCAPE)){
			Input.setCursor(true);
			mouseLocked = false;
		}
		if(Input.getMouseDown(0)){
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}
		
		if(Input.getKey(Input.KEY_W)){
			move(getForward(), moveAmount);
		}if(Input.getKey(Input.KEY_S)){
			move(getForward(), -moveAmount);
		}if(Input.getKey(Input.KEY_A)){
			move(getLeft(), moveAmount);
		}if(Input.getKey(Input.KEY_D)){
			move(getRight(), moveAmount);
		}
		
	//	move(getRight(), moveAmount);
	
	//	rotateY(14f);
		
		if(mouseLocked){
			Vector2f deltapos = Input.getMousePosition().sub(centerPosition);
			
			boolean rotX = deltapos.getX() != 0;
			boolean rotY = deltapos.getY() != 0;
			
			if(rotY){
				rotateY(deltapos.getX() * senstivty);
			}
			if(rotX){
				rotateX(-deltapos.getY() * senstivty);
			}
			
			if(rotX || rotY){
				Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
			}
			
		}
		
		
//		if(Input.getKey(Input.KEY_UP)){
//			rotateX(-rotAmount);
//		}if(Input.getKey(Input.KEY_DOWN)){
//			rotateX(rotAmount);
//		}if(Input.getKey(Input.KEY_LEFT)){
//			rotateY(-rotAmount);
//		}if(Input.getKey(Input.KEY_RIGHT)){
//			rotateY(rotAmount);
//		}
//		
		
		
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
		
		up.normalize();
		forward.normalize();
	}

	public void move(Vector3f dir, float amount){
		pos = pos.add(dir.mul(amount));
	}
	
	
	public void rotateY(float angle){
		 
		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();

		forward = forward.rotate(angle, yAxis).normalize();

		up = forward.cross(Haxis);
		up.normalize();
	}
	
	
	public void rotateX(float angle){

		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();

		forward = forward.rotate(angle, Haxis).normalize();

		up = forward.cross(Haxis);
		up.normalize();
		
	}
	
	public Vector3f getLeft(){
		Vector3f left = forward.cross(up);
		left.normalize();
		return left;
	}
	
	
	public Vector3f getRight(){
		Vector3f right = up.cross(forward);
		right.normalize();
		return right;
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}

	
	
}
