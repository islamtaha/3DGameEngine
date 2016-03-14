package com.base.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class Window {
	
	
	public static void createWindow(int width, int height, String title){
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void destroy(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static void render(){
		Display.update();
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static int getWidth(){
		return Display.getDisplayMode().getWidth();
	}
	
	
	public static int getHeight(){
		return Display.getDisplayMode().getHeight();
	}
	
	
	public static String getTitle(){
		return Display.getTitle();
	}
	
	
	
}
