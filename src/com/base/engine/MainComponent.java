package com.base.engine;


public class MainComponent {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final String TITLE = "3D Game Engine";
	public static final double FRAME_CAP = 5000.0;
	
	
	private boolean isRunning;
	private Game game;
	
	public MainComponent(){
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		isRunning = false;
		game = new Game();
		
	}
	
	public void start() {
		if(isRunning){
			return;
		}
		run();
	}
	
	public void stop() {
		if(!isRunning){
			return;
		}
		isRunning = false;
		
	}
	
	
	private void run() {
		isRunning = true;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		int frames = 0;
		int frametimeCnt = 0;
		
		
		long lastTime = Time.getTime();
		double unproccesedTime = 0.0; 
		
		while(isRunning){
			boolean render = false;
			
			long statTime = Time.getTime();
			long passedTime = statTime - lastTime;
			lastTime = statTime;
			
			unproccesedTime += passedTime / (double)Time.SECOND;
			frametimeCnt += passedTime;		
			
			while(unproccesedTime > frameTime){
				render = true;
				
				unproccesedTime -= frameTime;
				
				
				if(Window.isCloseRequested()){
					stop();
				}
				
				Time.setDelta(frameTime);
				
				game.input();
				Input.update();
				
				game.update();
				
				
				if(frametimeCnt >= Time.SECOND){
					System.out.println(frames);
					frames = 0;
					frametimeCnt = 0;
				}
				unproccesedTime -= frameTime;
			}
			
			if(render){
				render();
				frames++;
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}

		cleanUp();
	}
	
	
	private void render() {
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	
	private void cleanUp() {
		Window.destroy();
	}
	
	
	
	
	public static void main(String[] args){
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		MainComponent game = new MainComponent();
		game.start();
		
	}
}
