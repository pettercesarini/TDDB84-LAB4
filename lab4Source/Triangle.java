/*
 * Created on 2005-apr-21
 *
 */
package lab4Source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.AbstractList;


/**
 * The triangle shape.
 * @author Peter Sunnergren
 */
public class Triangle extends AbstractShape{
	private Polygon polygon;
	
	/**
	 * Gets the marked shape.
	 * @return This if it is marked, null otherwise.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {		
		if (polygon.contains(cx, cy)) { 
			return this; 
		} else {	
			return null;
		}
	}
	
	/**
	 * Draws the triangle.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (null == polygon) {
			polygon = new Polygon();
			polygon.addPoint(getX() + getWidth() / 2, getY());
			polygon.addPoint(getX(), getY() + getHeight());
			polygon.addPoint(getX() + getWidth(), getY() + getHeight());
		}
		g2.setColor(Color.green);
		g2.fill(polygon);
	}

	/**
	 * Accepts a visitor.
	 */
	public void accept (AbstractVisitor v) {
		v.visit(this);
	}
	
	/**
	 * Adds the triangle to the list of shapes.
	 * @param l The old version of the list.
	 * @return The new version of the list.
	 */
	public AbstractList getListOfShapes(AbstractList l) {
		l.add(this);
		return l;
	}
}
