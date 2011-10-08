/*
 * Created on 2005-apr-18
 *
 */
package lab4Source;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Vector;


/**
 * The class that holds a list of the marked shapes.
 * @author Peter Sunnergren
 */
public class Marked {
	private static Vector markedShapes;
	private static boolean controlIsDown;
	
	private Marked () {}
	
	/**
	 * Gets an instance of the marked shape. If there are more then one the last added (in the list) is returned.
	 * @return A marked shape.
	 */
	public static AbstractShape instance() {
		if (null == markedShapes || markedShapes.isEmpty()) {
			System.out.println("No shape set as marked");
			return null;
		} else {
			return (AbstractShape)markedShapes.lastElement();
		}
	}
	
	/**
	 * Marks a shape by placing it it the list of marked shapes. If control is down it is added to the list if control is up it replaces the list.
	 * @param s Shape that is marked.
	 */
	public static void markShape(AbstractShape s) {
		if (null == markedShapes) { 
			markedShapes = new Vector();
		}
		if (controlIsDown) {
			markedShapes.add(s);
		} else {
			markedShapes.clear();
			markedShapes.add(s);
		}
	}
	
	/**
	 * Draws a border round the marked shapes.
	 */
	public static void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		
		Iterator iter = markedShapes.iterator();
		while (iter.hasNext()) {
			AbstractShape shape = (AbstractShape)iter.next();
			g2.setStroke(new BasicStroke(shape.getWidth() / 70 + 1));
			if (Square.class == shape.getClass()) {
				g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			} else if (Circle.class == shape.getClass()) {
				g2.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			} else if (SquareProxy.class == shape.getClass()) {
				g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			} else if (Triangle.class == shape.getClass()) {
				g2.drawLine(shape.getX() + shape.getWidth() / 2, shape.getY(), shape.getX(), shape.getY() + shape.getHeight());
				g2.drawLine(shape.getX(), shape.getY() + shape.getHeight(), shape.getX() + shape.getWidth(), shape.getY() + shape.getHeight());
				g2.drawLine(shape.getX() + shape.getWidth(), shape.getY() + shape.getHeight(), shape.getX() + shape.getWidth() / 2 , shape.getY());
			} else if (Rectangle.class == shape.getClass()) {
				g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			}
		}
	}
	
	/**
	 * Sets that control is down.
	 */
	public static void setControlDown() {
		controlIsDown = true;
	}
	
	/**
	 * Sets that control is up.
	 */
	public static void setControlUp() {
		controlIsDown = false;
	}

	/**
	 * Used to check if control is down.
	 * @return true if control is down false if it is not.
	 */
	public static boolean isControlDown() {
		return controlIsDown;
	}
}
