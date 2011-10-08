/*
 * Created on 2005-apr-20
 *
 */
package lab4Source;

/**
 * The abstract super class to all the visitors accepted by the shapes.
 * @author Peter Sunnergren
 */

public abstract class AbstractVisitor {
	/**
	 * Visits the square.
	 * @param s Square.
	 */
	public abstract void visit(Square s);
	/**
	 * Visits the rectangle.
	 * @param r Rectangle.
	 */
	public abstract void visit(Rectangle r);
	/**
	 * Visits the circle.
	 * @param c Circle.
	 */
	public abstract void visit(Circle c);
	/**
	 * Visits the triangle.
	 * @param t Triangle.
	 */
	public abstract void visit(Triangle t);
}