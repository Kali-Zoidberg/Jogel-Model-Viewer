package org.kalizoid.jogl.modelview;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.jogamp.opengl.awt.GLCanvas;


public class Controller {
	Point4D rotation = new Point4D(0,0,0,0);

	public void initializeControls(JPanel window_panel, GLCanvas gl_canvas) {
	
		
		InputMap im = Main.window_panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		
		Action moveup = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Point4D tempRotation = new Point4D(-1,
						rotation.getY(),
						rotation.getZ(),
						(rotation.getW() + 2f)  % 360);
					rotateModel(gl_canvas, tempRotation);
				
			}
		};
		
		Action movedown = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				Point4D tempRotation = new Point4D(1,
											rotation.getY(),
											rotation.getZ(),
											(rotation.getW() + 2f) % 360);
				rotateModel(gl_canvas, tempRotation);
			
				//Render.setSpeed(yspeedup,1);
				//player.rigidbody.setY((int)player.rigidbody.getY() + 10);
			}
		};
		
		Action moveright = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Point4D tempRotation = new Point4D(rotation.getX(),
						rotation.getY() + 1,
						rotation.getZ() - 1,
						rotation.getW() + 2f);
				rotateModel(gl_canvas, tempRotation);
				//	Render.setSpeed(xspeedright, 3);

			
				
				}
		};
		
		Action moveleft = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Point4D tempRotation = new Point4D(rotation.getX(),
						rotation.getY() - 1,
						rotation.getZ(),
						rotation.getW() + 2f);
				rotateModel(gl_canvas, tempRotation);
				//	Render.setSpeed(xspeedleft, 2);
				
				
			}
				
		};
				
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "moveright");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "movedown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"moveleft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"moveup");
		Main.window_panel.getActionMap().put("moveright", moveright);
		Main.window_panel.getActionMap().put("moveleft",  moveleft);
		Main.window_panel.getActionMap().put("movedown", movedown);
		Main.window_panel.getActionMap().put("moveup", moveup);
	}
	
	
	
	public int movedown(int speed) {
		
		return speed;
	}
	
	
	private void rotateModel(GLCanvas gl_canvas, Point4D tempRotation)
	{
		
		rotation = new Point4D(tempRotation);
		Main.test_model_render.setRotation(new Point4D(rotation));
		gl_canvas.repaint();
	}

	
}
