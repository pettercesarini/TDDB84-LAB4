/*
 * Created on 2005-apr-17
 *
 */
package lab4Source;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * The panel that contains the root shape.
 * @author Peter Sunnergren
 */
public class ShapePanel extends JPanel {
	private Square root;
	private boolean paintVisitor = false;
	private boolean paintIterator = false;
	
	public ShapePanel () {
		setMinimumSize(new Dimension(400, 400));
		setMaximumSize(new Dimension(400, 400));
		setBackground(Color.yellow);
		root = new Square();
		root.setX(0);
		root.setY(0);
		root.setHeight(400);
		root.setWidth(400);
		Marked.markShape(root);
	}

	/**
	 * Draws the shapes and the borders around them.
	 * This is where the functionality to draw with a Visitor or Iterator are filled in.
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		if (AbstractShape.paintChildren) {
			root.paint(g);
		} else if (paintVisitor){
			/**
			 * Place code to draw with Visitor here
			 */ 
		} else if (paintIterator) {
			/**
			 * Place code to draw with Iterator here
			 */
		}
		Marked.paint(g);
	}

	/**
	 * Sets that next time use the default drawing method.
	 */
	public void setPaintDefault() {
		AbstractShape.paintChildren = true;
		paintVisitor = false;
		paintIterator = false;
	}
	
	/**
	 * Sets that next time drawing should bee done with the Visitior pattern.
	 *
	 */
	public void setPaintVisitor() {
		AbstractShape.paintChildren = false;
		paintVisitor = true;
		paintIterator = false;
	}
	
	/**
	 * Sets that next time drawing should bee done with the Iterator pattern.
	 *
	 */
	public void setPaintIterator() {
		AbstractShape.paintChildren = false;
		paintVisitor = false;
		paintIterator = true;		
	}
	
	/**
	 * Applies the Visitor to the root shape.
	 */
	public void applyVisitor() {
		int totalNumber = 0;
		/**
		 * Place to code to count the shapes using a Visitor here.
		 */
		ShapeApplet.setOutputText("Number of shapes: " + String.valueOf(totalNumber));
	}
	
	/**
	 * Applies a Iterator to the root shape.
	 */
	public void applyIterator() {
		int totalNumber = 0;
		/**
		 * Place the code to count the shapes using an Iterator here.
		 */

		ShapeApplet.setOutputText("Number of shapes: " + String.valueOf(totalNumber));
	}
	
	/**
	 * Marks the clicked shape by placing it in Marked.
	 * @param evt A mouseevent that contains the position of the click.
	 */
	public void markClickedShape(MouseEvent evt) {
		if ((evt.getX() > getBounds().x) && (evt.getX() < (getBounds().x + getBounds().width)) &&
				(evt.getY() > getBounds().y) && (evt.getY() < (getBounds().y + getBounds().height))) {
			Marked.markShape(root.getMarkedShape(evt.getX() - getLocation().x, evt.getY() - 24 - getLocation().y));
		}
	}
	
	/**
	 * Adds some test shapes. Useful when testing.
	 * @param number The number of layers of shapes.
	 */
	public void makeTestShapes(int number) {
		AbstractShape shape = root;
		for (int i = 0; i < number; i++) {
			ShapeFactory.instance().createCircle();
			ShapeFactory.instance().createTriangle();
			ShapeFactory.instance().createRectangle();
			Marked.markShape(ShapeFactory.instance().createSquareProxy());
			ShapeFactory.instance().createCircle();
			ShapeFactory.instance().createTriangle();
			ShapeFactory.instance().createRectangle();
			Marked.markShape(shape);
			Marked.markShape(shape = ShapeFactory.instance().createSquare());
		}
		Marked.markShape(root);
	}
}
