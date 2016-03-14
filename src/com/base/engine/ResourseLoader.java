package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

import org.newdawn.slick.opengl.TextureLoader;

public class ResourseLoader {
	
	public static Texture loadTexture(String fileName){
	
		String[] arrSplit = fileName.split("\\.");
		String extension = arrSplit[arrSplit.length-1];
	
		
		try{
			int id = TextureLoader.getTexture(extension, new FileInputStream(new File("./res/textures/"+fileName))).getTextureID();
			
			return new Texture(id);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	
	public static String loadShader(String fileName){
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try {
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
			String line;
			while((line = shaderReader.readLine()) != null){
				shaderSource.append(line).append("\n");
			}
			
			shaderReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}
	
	public static Mesh loadMesh(String fileName){
		String[] arrSplit = fileName.split("\\.");
		String extension = arrSplit[arrSplit.length-1];
		
		if(!extension.equals("obj")){
			System.err.println("Error: File format not supported for mesh data: " + extension);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		BufferedReader MeshReader = null;
		
		try {
			MeshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
			String line;
			while((line = MeshReader.readLine()) != null){
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyString(tokens);
				
				if(tokens.length == 0 || tokens[0].equals("#")){
					continue;
				}else if(tokens[0].equals("v")){
					vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
														 Float.valueOf(tokens[2]),
														 Float.valueOf(tokens[3]))));
				}else if(tokens[0].equals("f")){
					indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
					
					if(tokens.length > 4){
						indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
					}
				}
			}
			
			MeshReader.close();
			
			Mesh mesh = new Mesh();
			
			Vertex[] vertexData = new Vertex[vertices.size()];
			vertices.toArray(vertexData);
			
			Integer[] indexData = new Integer[indices.size()];
			indices.toArray(indexData);
			
			mesh.addVertices(vertexData, Util.toIntArray(indexData));
			
			return mesh;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
		
	}
	
}
