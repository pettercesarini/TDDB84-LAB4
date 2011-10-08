/*
 * Created on 2005-apr-18
 *
 */
package lab4Source;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * This class is an abstraction of the square class according to the proxy pattern.
 * @author Peter Sunnergren
 */

public abstract class AbstractSquare extends AbstractShape {
	
	/**
	 * Gets the marked shape.
	 * @param x Mouse X-coordinate.
	 * @param y Mouse Y-coordinate.
	 * @return This if it is marked otherwise null.
	 */
	abstract public AbstractShape getMarkedShape(int x, int y);

	/**
	 * Draws the square.
	 * @param g Graphics.
	 */
	abstract public void paint(Graphics g);
	
	/**
	 * Draws the children.
	 * @param g Graphics.
	 */
	abstract public void paintChildren(Graphics g);

	/**
	 * Accepts a visitor.
	 * @param v Visitor.
	 */
	abstract public void accept (AbstractVisitor v);

	/**
	 * Adds this to the list of shapes.
	 * @param l List of shapes.
	 * @return List of shapes.
	 */
	abstract public AbstractList getListOfShapes(AbstractList l);
		
	/**
	 * Adds a child.
	 * @param child A new child.
	 */
	abstract public void addChild(AbstractShape child);
	
	/**
	 * Used to check if there are any children.
	 * @return True if the list of children is not empty.
	 */
	abstract public boolean hasChildren();
	
	/**
	 * Gets the last child in the list of children.
	 * @return The last child.
	 */
	abstract public AbstractShape getLastChild();
}
