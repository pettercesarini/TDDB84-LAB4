/*
 * Created on 2005-apr-17
 *
 */
package lab4Source;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * The abstract super class to the shapes. Holds information about placement,
 * size and family.
 * 
 * @author Peter Sunnergren
 */
abstract public class AbstractShape {
	private int x;
	private int y;
	private int width;
	private int height;
	private int maxSize;
	private AbstractSquare parent;
	private AbstractShape sibling;
	private double scale = 1.0;

	/**
	 * True if the square should paint its children in the paint(g) method.
	 */
	protected static boolean paintChildren = false;

	/**
	 * Draws the shape
	 */
	abstract public void paint(Graphics g);

	/**
	 * Removes the overlap of the shapes by resizing the bounds of the new shape
	 * so that it dose not overlap with this one.
	 * 
	 * @param bounds
	 *            The bounds of the new shape.
	 */
	private void removeOverlap(Rectangle bounds) {
		Rectangle thisBounds = new Rectangle(getX(), getY(), getWidth(),
				getHeight());

		if (thisBounds.contains(bounds) || bounds.contains(thisBounds)) {
			bounds.setRect(0, 0, 0, 0);
		} else {
			Rectangle intersection = bounds.intersection(thisBounds);
			System.out.println("" + intersection.height + ""
					+ intersection.width);

			if (!intersection.isEmpty()) {
				System.out.println("hej hej");
				if (getX() == bounds.x) {
					if (getY() < bounds.y) {
						bounds.y += intersection.height;
						bounds.height -= intersection.height;
					} else {
						bounds.height -= intersection.height;
					}
				} else if (getY() == bounds.y) {
					if (getX() < bounds.x) {
						bounds.x += intersection.width;
						bounds.width -= intersection.width;
					} else {
						bounds.width -= intersection.width;
					}
				} else if ((getX() < bounds.x) && (getY() < bounds.y)) {
					bounds.y += intersection.height;
					bounds.x += intersection.width;
					bounds.height -= intersection.height;
					bounds.width -= intersection.width;
				} else if ((getX() < bounds.x + bounds.width)
						&& (getY() < bounds.y)) {
					bounds.width -= intersection.width;
					bounds.y += intersection.height;
					bounds.height -= intersection.height;
				} else if ((getX() < bounds.x)
						&& (getY() < bounds.y + bounds.height)) {
					bounds.height -= intersection.height;
					bounds.x += intersection.width;
					bounds.width -= intersection.width;
				} else if ((getX() < bounds.x + bounds.width)
						&& (getY() < bounds.y + bounds.height)) {
					bounds.height -= intersection.height;
					bounds.width -= intersection.width;
				} else {
					System.out.print("**************************");
				}
			}
		}
	}

	/**
	 * Gets the marked shape.
	 * 
	 * @param x
	 *            X coordinate where the mouse was clicked.
	 * @param y
	 *            Y coordinate where the mouse was clicked.
	 * @return The marked shape.
	 */
	abstract public AbstractShape getMarkedShape(int x, int y);

	/**
	 * Accepts a visitor.
	 * 
	 * @param v
	 *            Visitor.
	 */
	abstract public void accept(AbstractVisitor v);

	/**
	 * Adds the shape to the list of shapes.
	 * 
	 * @param l
	 *            List of shapes.
	 * @return List of shapes.
	 */
	abstract public AbstractList getListOfShapes(AbstractList l);

	/**
	 * Sets X coordinat.
	 * 
	 * @param x
	 *            X coordinat.
	 */
	protected void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets X coordinat.
	 * 
	 * @return X coordinat.
	 */
	protected int getX() {
		return x;
	}

	/**
	 * Sets Y coordinat.
	 * 
	 * @param y
	 *            Y coordinat.
	 */
	protected void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets Y coordinat.
	 * 
	 * @return Y coordinat.
	 */
	protected int getY() {
		return y;
	}

	/**
	 * Sets the witdh.
	 * 
	 * @param w
	 *            Witdh.
	 */
	protected void setWidth(int w) {
		this.width = w;
	}

	/**
	 * Gets the witdh.
	 * 
	 * @return Witdh.
	 */
	protected int getWidth() {
		return width;
	}

	/**
	 * Sets the height.
	 * 
	 * @param h
	 *            Height.
	 */
	protected void setHeight(int h) {
		this.height = h;
	}

	/**
	 * Gets the height.
	 * 
	 * @return Height.
	 */
	protected int getHeight() {
		return height;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param p
	 *            Parent.
	 */
	protected void setParent(AbstractSquare p) {
		parent = p;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return Parent.
	 */
	protected AbstractSquare getParent() {
		return parent;
	}

	/**
	 * Sets the sibling.
	 * 
	 * @param s
	 *            Sibling.
	 */
	protected void setSibling(AbstractShape s) {
		sibling = s;

	}

	/**
	 * Gets the sibling.
	 * 
	 * @return Sibling.
	 */
	protected AbstractShape getSibling() {
		return sibling;
	}

	/**
	 * Sets the scale.
	 * 
	 * @param s
	 *            Scale.
	 */
	protected void setScale(double s) {
		scale = s;
	}

	/**
	 * Gets the scale.
	 * 
	 * @return Scale.
	 */
	protected double getScale() {
		return scale;
	}

	public void handleOverlap(AbstractShape abstractShape) {
		if (abstractShape == null)
			return;
		Rectangle rect = new Rectangle(abstractShape.x, abstractShape.y,
				abstractShape.width, abstractShape.height);
		this.removeOverlap(rect);
		handleOverlap(abstractShape.getSibling());
	}
}
