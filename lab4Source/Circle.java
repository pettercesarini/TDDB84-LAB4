/*
 * Created on 2005-apr-18
 */
package lab4Source;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Arc2D;
import java.util.AbstractList;


/**
 * This class represent the circle shape.
 * @author Peter Sunnergren
 */
public class Circle extends AbstractShape{
	
	/**
	 * Returns this if this the marked shapes otherwise it returns null.
	 */
	public AbstractShape getMarkedShape (int cx, int cy) {
		if (new Arc2D.Double(getX(), getY(), getWidth(), getHeight(), 0.0, 360.0, Arc2D.CHORD).contains(cx, cy)) {
			return this;
		} 
		return null;
	}
	
	/**
	 * Draws the circle.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Accepts a visitor.
	 * @param v Visitor.
	 */
	public void accept(AbstractVisitor v) {
		v.visit(this);
	}

	/**
	 * Add the circle to the list of shapes.
	 * @param l List.
	 * @return List of shapes.
	 */
	public AbstractList getListOfShapes(AbstractList l) {
		l.add(this);
		return l;
	}
	
	/**
	 * Sets the width and makes sure that the circle is round.
	 * @param w Width.
	 */
	protected void setWidth(int w) {
		if (0 != getHeight()) {
			super.setHeight(Math.min(w, getHeight()));
			super.setWidth(Math.min(w, getHeight()));
		} else {
		super.setWidth(w);
		}
	}
	
	/**
	 * Sets the height of the circle and makes sure the it is round.
	 * @param h Height.
	 */
	protected void setHeight(int h) {
		if (0 != getWidth()) {
			super.setHeight(Math.min(h, getWidth()));
			super.setWidth(Math.min(h, getWidth()));
		} else {
		super.setHeight(h);
		}
	}
}
