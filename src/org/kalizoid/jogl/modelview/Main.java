

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
import javax.swing.JPanel;

/**
 * This sets up the Window frame and adds OpenGL support to it.
 * TODO: Parse the vertex connections following the vertex coordinate declarations so that they 
 * can correctly represent the shape they are trying to connect.
 * - Move parsing to separate function
 * - Add menu layout before fixing the artifcating issues.
 * @author Nicholas Chow
 *
 */

public class Main {
	public static JFrame window_frame = new JFrame("Hello World");
	public static JPanel window_panel = new JPanel();
	public static RenderModel test_model_render;
	private static ArrayList<Vertex> vertex_list = new ArrayList<Vertex>();
	
	public static void main(String args[]) throws FileNotFoundException {
			File test_file = new File("sphere.ply");
			parseFile(test_file);
			renderWindow();

			
			
	}

	/**
	 * Parses the coordinates from a string and returns it as a float array.
	 * @param input_string The string that contains the formatted coordinates.
	 * @return Returns a float array of the coordinates the elements [0,3] contains the 
	 * vertex points and [4,6] contain the normals.
	 */
	private static float[] parseVertexCoord(String input_string)
	{
		int ind[] = new int[6];
		float temp_coords[] = new float[6];
		try {
		for (int i = 0; i < 6; ++i ){ 
		  	ind[i] = findXChar(input_string, ' ',0, i + 1);
		  	if (i > 0 && i < 5) {
			  	if (ind[i] > ind[i-1])
			  		temp_coords[i] = Float.parseFloat(input_string.substring(ind[i-1] + 1, ind[i]));
			} else if (ind[i] > 0 && i == 0)
		  		temp_coords[i] = Float.parseFloat(input_string.substring(0, ind[i]));
			else if ( i == 5){
				System.out.println(input_string.substring(ind[i-1] + 1, 
						input_string.length()));
				temp_coords[i] = Float.parseFloat(input_string.substring(ind[i-1] + 1, 
						input_string.length() - 1));
			}
		}
		} catch (NumberFormatException e)
		{
			System.out.println("Error with the string " + input_string);
		}
		return temp_coords;
	}
	
	/**
	 * Parses all the vertices from the model file.
	 * @param scan_file The model file
	 * @throws FileNotFoundException 
	 */

	private static void parseFile(File file) throws FileNotFoundException
	{
		Scanner scan_file = new Scanner(file);
		ArrayList<Vertex> vertices_list = new ArrayList<Vertex>();
		Vertex temp_vert;

		String input_string = "";
		String vertex_end_sentinel = "3 0 1 2";
		String vertex_end_sentinel_2 = "4 0 1 2 3";
		String vertex_count_tag = "element vertex ";

		boolean has_found = false;
		boolean end_vertex_input = false;
		
		float temp_coords[];
		
		
		int vertex_count = 0;
			
		/*
		 * Generates vertices based on given input.
		 * Runtime: O(6n) where n = number of vertices
		 */
		
		while (scan_file.hasNextLine() && !end_vertex_input) {
		
			input_string = scan_file.nextLine();
			
			//Exit loop if we reach the end of the vertex coordinate section of the file.
			if (input_string.contains(vertex_end_sentinel) || 
					input_string.contains(vertex_end_sentinel_2))
				break;
			
			
			/**
			 * Sorts through the string to parse all of the floats into their respective coordinate type.
			 */
			
			if (has_found && !input_string.contains(vertex_end_sentinel)) {
					
				//Parse vertex coordinates from file.
				temp_coords = parseVertexCoord(input_string);
				//Create vertex based off of coordinates.
				temp_vert = new Vertex(temp_coords[0], temp_coords[1], 
						temp_coords[2], temp_coords[3], temp_coords[4], temp_coords[5]);
				vertices_list.add(temp_vert);
			}				
		
			if (input_string.contains("end_header"))
				has_found = true;
			if (input_string.contains(vertex_count_tag)) 
				vertex_count = Integer.parseInt(input_string.substring(vertex_count_tag.length(), input_string.length())); // parses the integer followed after the vertex tax
					
		}
		
		/*
		while (scan.hasNextLine()) {
			
			
			if (input_string.substring(0,1).equals('3')) 
				vertices_list.get(index).setPolytype(PolygonType.TRIANGNLE);
			 else if (input_string.substring(0,1).equals('4')) 
				vertices_list.get(index).setPolytype(PolygonType.QUAD);
			 else
				 vertices_list.get(index).setPolytype(PolygonType.TRIANGNLE);
			
			input_string = scan.nextLine();
			++index;
			System.out.println(index);
		}
		*/
		
		vertex_list = vertices_list;
	
		for (Vertex vert : vertex_list) {
		vert.printVertex();
	}
	
	if (vertex_count == vertices_list.size())
		System.out.println("\nSuccesfully parsed all vertices!");
	
	System.out.println("Total vertices: " + vertices_list.size());
	
	scan_file.close();
		
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
	
	/**
	 * Renders the model into a JFrame Window.
	 */
	
	public static void renderWindow() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		//Canvas is drawn on JFrame
		GLCanvas glcanvas = new GLCanvas(capabilities);
		test_model_render = new RenderModel(vertex_list);
		glcanvas.addGLEventListener(test_model_render);
		glcanvas.setSize(500,500);
		Controller controller = new Controller();
		controller.initializeControls(window_panel, glcanvas);
		window_frame.add(window_panel);
		/*
		 * Right before here there should be the menu for loading, settings and about.
		 */
		window_frame.add(glcanvas);
			
		//Close program upon window exit.
		window_frame.setDefaultCloseOperation(window_frame.EXIT_ON_CLOSE);
		
		window_frame.setSize( window_frame.getContentPane().getPreferredSize());
		window_frame.setVisible(true);
		window_frame.setTitle("Model Viewer");

	}

}