package com.base.engine;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;


public class Shader {
	private int program;
	private HashMap<String, Integer> uniformVars;
	
	public Shader(){
		
		program = glCreateProgram();
		uniformVars = new HashMap<String, Integer>();
		
		if(program == 0){
			System.err.println("Shader creation failed: Couldnot find valid memory location in constructor");
			System.exit(1);
		}
	}
	
	
	public void bind(){
		glUseProgram(program);
	}
	
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
		
	}
	
	
	
	public void addUniform(String uniform){
		int uniformLoc = glGetUniformLocation(program, uniform);
		
		if(uniformLoc == -1){
			System.err.println("Error: Couldn't find uniform " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniformVars.put(uniform, uniformLoc);
	
	}
	
	public void compileShader(){
		glLinkProgram(program);
		
		if(glGetProgram(program, GL_LINK_STATUS) == 0){
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgram(program, GL_VALIDATE_STATUS) == 0){
			System.err.println(glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
	}
	
	public void addGeometryShader(String text){
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text){
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void addVertexShader(String text){
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addProgram(String text, int type){
		int shader = glCreateShader(type);
		
		if(shader == 0){
			System.err.println("Shader creation failed: Couldnot find valid memory location adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShader(shader, GL_COMPILE_STATUS) == 0){
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	
	
	public void setUniformi(String name, int val){
		glUniform1i(uniformVars.get(name), val);
	}
	
	public void setUniformf(String name, float val){
		glUniform1f(uniformVars.get(name), val);
	}
	
	public void setUniform(String name, Vector3f v){
		glUniform3f(uniformVars.get(name), v.getX(), v.getY(), v.getZ());
	}
	
	public void setUniform(String name, Matrix4f m){
		glUniformMatrix4(uniformVars.get(name), true ,Util.createFlippedBuffer(m));
	}
	
}
