/*
 * Created on 2005-apr-21
 */
package lab4Source;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;


/**
 * This is the class where the students implements the proxy for the square.
 * @author Peter Sunnergren
 */

public class SquareProxy extends AbstractSquare {
	private boolean open;
	
	/**
	 * Gets the marked shape. Checks this nad this children if it is open.
	 * @return This if it is marked, null otherwise.
	 */
	public AbstractShape getMarkedShape (int cx, int cy) {
		AbstractShape retur = null;
		if ((getX() < cx) &&
				(getX() + getWidth() > cx) && 
				(getY() < cy) && 
				(getY() + getHeight() > cy)) {
			open = true;
			return this;
		} else {
			if (open) {
				open = Marked.isControlDown();
			}
		}
		return null;
	}

	/**
	 * Draws the proxy.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		if (open) {
			g.setColor(Color.orange);
		} else {
			g.setColor(new Color(205, 133, 63));
		}
		
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Draws the children of the proxy;
	 * @param g Graphics.
	 */
	public void paintChildren(Graphics g) {

	}
	
	/**
	 * Adds the proxy to the list of shapes. Only adds the childeren if the proxy is open.
	 */
	public AbstractList getListOfShapes(AbstractList l) {
		return l;
	}
	
	/**
	 * Accepts a Visitor.
	 */
	public void accept (AbstractVisitor v) {

	}
	
	/**
	 * Adds a child.
	 */
	public void addChild(AbstractShape child) {
	
	}
	
	/**
         * Used to check if there are any children.
	 * @return Return true if the proxy has children and false if it does not.
	 */
	public boolean hasChildren() {
		return false;
	}
	
	/**
	 * Gets the last child in the list of children.
	 * @return The last child if there is any.
	 */
	public AbstractShape getLastChild() {
		return null;
	}
}
