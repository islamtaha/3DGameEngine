package com.base.engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Util {
	
	public static FloatBuffer createFloatBuffer(int size){
		return BufferUtils.createFloatBuffer(size);
	}
	
	
	public static IntBuffer createIntBuffer(int size){
		return BufferUtils.createIntBuffer(size);
	}
	
	
	public static IntBuffer createFlippedBuffer(int ... values){
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for(int i = 0; i < vertices.length; i++){
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
			buffer.put(vertices[i].getTexCood().getX());
			buffer.put(vertices[i].getTexCood().getY());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	
	public static FloatBuffer createFlippedBuffer(Matrix4f m){
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				buffer.put(m.get(i, j));
			}
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	
	public static String[] removeEmptyString(String[] arr){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < arr.length; i++){
			if(!arr[i].equals("")){
				result.add(arr[i]);
			}
		}	
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	
	public static int[] toIntArray(Integer[] arr){
		int[] result = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			result[i] = arr[i].intValue();
		}
		return result;
	}
	
	
}
