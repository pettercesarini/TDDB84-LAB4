/*
 * Created on 2005-apr-21
 *
 */
package lab4Source;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * The rectangular shape.
 * @author Peter Sunnergren
 */
public class Rectangle extends AbstractShape {

	/**
	 * Returns this if it is clicked returns null otherwise.
	 * @param cx Mouse clicked X-coordinate.
	 * @param cy Mouse clicked Y-coordinate.
	 * @return The rectangle or null.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {		
		if ((getX() < cx) &&
			(getX() + getWidth() > cx) && 
			(getY() < cy) && 
			(getY() + getHeight() > cy)) { 
			return this;
		} else {
			return null;
		}
	}
	
	/**
	 * Draws the rectangle.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Accepts a visitor.
	 * @param v Visitor.
	 */
	public void accept (AbstractVisitor v) {
		v.visit(this);
	}
	
	/**
	 * Adds the rectangle to the list of shapes.
	 * @param l List of shapes.
	 * @return List of shapes.
	 */
	public AbstractList getListOfShapes(AbstractList l) {
		l.add(this);
		return l;
	}
}
