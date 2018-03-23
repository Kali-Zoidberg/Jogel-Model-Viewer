package org.kalizoid.jogl.modelview;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
/**
 * This sets up the Window frame and adds OpenGL support to it.
 * TODO: Look at the .ply file and look at how it handles normals. IT seems to have a correspondence between quadrilaterals and triangles.
 * @author Nicholas Chow
 *
 */
public class Main {
	private static ArrayList<Vertex> vertex_list = new ArrayList<Vertex>();
	public static void main(String args[]) throws FileNotFoundException {
			File test_file = new File("cube.ply");
			Scanner scan = new Scanner(test_file);
			String input_string = "";
			boolean has_found = false;
			ArrayList<Vertex> vertices_list = new ArrayList<Vertex>();
			
			Vertex temp_vert;
			float temp_coords[] = new float[3];
			
			int totalcount = 0;
			int timesadded =0;
			int ind1 = 0,ind2 = 0, ind3 = 0;
			String vertex_end_sentinel = "4 0 1 2 3";
			int vertex_count = 0;
			
			String vertex_count_tag = "element vertex ";
			
			/*
			 * Generates vertices based on given input.
			 */
			
			while (scan.hasNextLine() && !input_string.contains(vertex_end_sentinel)) {
			
				
				input_string = scan.nextLine();

				if (has_found && !input_string.contains(vertex_end_sentinel)) {
								
					ind1 = findXChar(input_string, ' ', 0, 1);
					ind2 = findXChar(input_string, ' ', 0, 2);
					ind3 = findXChar(input_string, ' ', 0, 3);
				
					if (ind1 > 0)
						temp_coords[0] = Float.parseFloat(input_string.substring(0, ind1)); // generates first vertex
					if (ind2 > ind1)
						temp_coords[1] = Float.parseFloat(input_string.substring(ind1 + 1, ind2)); //generates second vertex
					if (ind3 > ind2)
						temp_coords[2] = Float.parseFloat(input_string.substring(ind2 + 1, ind3));
											
					temp_vert = new Vertex(temp_coords[0], temp_coords[1], temp_coords[2]);
					vertices_list.add(temp_vert);
				}				
			
				if (input_string.contains("end_header"))
					has_found = true;
				if (input_string.contains(vertex_count_tag)) 
					vertex_count = Integer.parseInt(input_string.substring(vertex_count_tag.length(), input_string.length())); // parses the integer followed after the vertex tax
					
				
			}
			
			
			if (vertex_count == vertices_list.size())
				System.out.println("Succesfully parsed all vertices!");
			System.out.println("Total vertices: " + vertices_list.size());
			
			vertex_list = vertices_list;
		for (Vertex vert : vertex_list) {
			vert.printVertex();
		}
				renderWindow();
	}
	
	
	
	/**
	 * Counts specified amount of char in the string and returns the location of the last char.
	 * @param input The input string to search.
	 * @param character The character to search in the string.
	 * @param index The index to begin searching in the string.
	 * @param xtimes The amount of times you want to search for the string.
	 * @return Returns the index of the last character. If there are less than the specified spaces, the last occurence of the character is returned. If the character does not occur anywhere, -1 is returned.
	 */
	public static int findXChar(String input, char character, int index, int xtimes) {
		int location = -1;
		int count = 0;
		
		if (index > input.length())
			return -1;
		
		for (int i = index; i < input.length(); ++i) {
			if (input.charAt(i) == character) {
				++count;
				location = i;
			}
			if (count == xtimes) 
				return i;
		}
		
		return location;
	}
	
	
	public static void renderWindow() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		//Canvas is drawn on JFrame
		GLCanvas glcanvas = new GLCanvas(capabilities);
		RenderModel test_model_render = new RenderModel(vertex_list);
		glcanvas.addGLEventListener(test_model_render);
		glcanvas.setSize(500,500);
		
		JFrame window_frame = new JFrame("Hello World");
		
		window_frame.getContentPane().add(glcanvas);
		
		//Close program upon window exit.
		window_frame.setDefaultCloseOperation(window_frame.EXIT_ON_CLOSE);
		
		window_frame.setSize( window_frame.getContentPane().getPreferredSize());
		window_frame.setVisible(true);
		window_frame.setTitle("Model Viewer");

	}

}
