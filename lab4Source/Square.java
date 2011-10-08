/*
 * Created on 2005-apr-22
 */

package lab4Source;
import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Represents the square shape.
 * @author Peter Sunnergren
 */
public class Square extends AbstractSquare {
	protected Vector children;
	private int depth = 0;
	
	public Square() {
		children = new Vector();
	}	
	
	/**
	 * Gets the marked shape. Checks if this or its children is marked.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {
		AbstractShape retur = null;
		Iterator iter = children.iterator();
		while (iter.hasNext()) {
			AbstractShape s = (AbstractShape)iter.next();
			s = s.getMarkedShape(cx, cy);
			if (null != s) {
				/**
				 * To make sure that all children is checked.
				 */
				retur = s;
			}
		}
		
		if (null != retur) {
			return retur;
		}
 
		if ((getX() < cx) &&
				(getX() + getWidth() > cx) && 
				(getY() < cy) && 
				(getY() + getHeight() > cy)) {
			return this;
		}
		return null;
	}
	
	/**
	 * Draws the square.
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {
		g.setColor(new Color((200 - getDepth()*20), (200 - getDepth()*20), (200 - getDepth()*20)));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		if (paintChildren) {
			paintChildren(g); 
		}
	}

	/**
	 * Draws the children.
	 * @param g Graphics.
	 */
	public void paintChildren(Graphics g) {
		AbstractShape s;
		Iterator iter = children.iterator();
		while (iter.hasNext()) {
			s = (AbstractShape)iter.next();
			s.paint(g);
		}
	}
	
	/**
	 * Adds the square and its children to the list of shapes.
	 * @param l List of shapes.
	 * @return New version of list of shapes.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractList getListOfShapes(AbstractList l) {
		l.add(this);
		Iterator iter = children.iterator();
		while (iter.hasNext()) {
			((AbstractShape)iter.next()).getListOfShapes(l);
		}		
		return l;
	}
	
	/**
	 * Accepts a visitor.
	 * @param v Visitor.
	 */
	@SuppressWarnings("rawtypes")
	public void accept (AbstractVisitor v) {
		v.visit(this);
		
		Iterator iter = children.iterator();
		while (iter.hasNext()) {
			((AbstractShape)iter.next()).accept(v);
		}
	}

	/**
	 * Adds a child.
	 * @param child The new child.
	 */
	@SuppressWarnings("unchecked")
	public void addChild(AbstractShape child) {
		children.add(child);
	}
	
	/**
	 * Used to check if there are any children.
	 * @return Return true if the proxy has children and false if it does not.
	 */
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	/**
	 * Gets the last child in the list of children.
	 * @return The last child if there is any.
	 */
	public AbstractShape getLastChild() {
		return (AbstractShape)children.lastElement();
	}
	
	/**
	 * Sets the depth of the square.
	 * @param d Depth.
	 */
	public void setDepth(int d) {
		depth = d;
	}
	
	/**
	 * Gets the depth of the square.
	 * @return Depth.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Sets the width and make sure that the square is square.
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
	 * Sets the height and make sure that the square is square.
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