package com.base.engine;




public class Game {
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Material material;
	
	
	public Game(){
		mesh = new Mesh();//ResourseLoader.loadMesh("box.obj");
		shader = PhongShader.getInstance();
		material = new Material(ResourseLoader.loadTexture("test.png"), new Vector3f(1, 1, 1));
		camera = new Camera();
		transform = new Transform();
		
		
		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),
										  new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 0)),
										  new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f, 0)),
										  new Vertex(new Vector3f(0, -1, 1), new Vector2f(0.5f, 1.0f))};
		int[] indices = new int[]{3,1,0,
								  2,1,3,
								  0,1,2,
								  0,2,3};
		
		mesh.addVertices(vertices, indices);

		
		Transform.setProjection(70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000);
		Transform.setCamera(camera);
		
		PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
		
	}
	
	public void input() {
		camera.input();
//		if(Input.getKeyDown(Keyboard.KEY_UP)){
//			System.out.println("pressed UP");	
//		}
//		
//		if(Input.getKeyUp(Keyboard.KEY_UP)){
//			System.out.println("released UP");	
//		}
//		
//		
//		if(Input.getMouseDown(1)){
//			System.out.println("pressed right click at " + Input.getMousePosition());	
//		}
//		
//		if(Input.getMouseUp(1)){
//			System.out.println("released right click " + Input.getMousePosition());	
//		}
	}
	
	float tmp = 0.0f;
	
	public void update() {
		tmp += Time.getDelta();
		transform.setTranslation((float)Math.abs(Math.sin(tmp)), 0, 5);
		transform.setRotation(0, (float)Math.abs(Math.sin(tmp)*180), 0);
		//transform.setScale(0.7f*(float)Math.sin(tmp), 0.7f*(float)Math.sin(tmp), 0.7f*(float)Math.sin(tmp));;
	}
	

	public void render() {
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f));
		shader.bind();
		shader.updateUniforms(transform.getTransFromation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}


}
