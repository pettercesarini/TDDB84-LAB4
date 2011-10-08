/*
 * Created on 2005-apr-18
 *
 */
package lab4Source;

/**
 * A class that creates shapes according to rules about size. Also adds the new
 * shape to its parent.
 * 
 * @author Peter Sunnergren
 */
public class ShapeFactory {
	private static ShapeFactory instance;

	/**
	 * Used to get the an instance of the factory.
	 * 
	 * @return An instance of the factory.
	 */
	public static ShapeFactory instance() {
		if (null == instance) {
			instance = new ShapeFactory();
		}
		return instance;
	}

	private AbstractSquare getMarkedParent() {
		AbstractShape marked = Marked.instance();
		if (Marked.instance().getClass() == Square.class) {
			return (Square) Marked.instance();
		} else if (Marked.instance().getClass() == SquareProxy.class) {
			return (SquareProxy) Marked.instance();
		} else if ((Marked.instance().getClass() == Circle.class)
				|| (Marked.instance().getClass() == Triangle.class)
				|| (Marked.instance().getClass() == Rectangle.class)) {
			return (AbstractSquare) Marked.instance().getParent();
		}
		return null;
	}

	private int getMaxSize(AbstractShape s) {
		if (s.getClass() == Square.class) {
			return 300;
		} else if (s.getClass() == SquareProxy.class) {
			return 300;
		} else if (s.getClass() == Circle.class) {
			return 150;
		} else if (s.getClass() == Triangle.class) {
			return 150;
		} else if (s.getClass() == Rectangle.class) {
			return 150;
		}
		return 0;
	}

	private boolean isToSmall(AbstractShape s, double width, double height) {
		double parentScale = s.getParent().getScale();
		if ((width / parentScale == 0) || (height / parentScale == 0)) {
			return true;
		}
		if (s.getClass() == Square.class) {
			return ((width / parentScale < 100) || (height / parentScale < 100));
		} else if (s.getClass() == SquareProxy.class) {
			return ((width / parentScale < 100) || (height / parentScale < 100));
		} else if (s.getClass() == Circle.class) {
			return ((width / parentScale < 50.0) || (height / parentScale < 50.0));
		} else if (s.getClass() == Triangle.class) {
			return ((width / parentScale < 50.0) || (height / parentScale < 50.0));
		} else if (s.getClass() == Rectangle.class) {
			return ((width / parentScale < 50.0) || (height / parentScale < 50.0));

		}
		return false;
	}

	/**
	 * Loops util the shape can be randomly placed some where.
	 * 
	 * @param s
	 *            A shape.
	 */
	private void create(AbstractShape s) {
		int counter = 0;
		java.awt.Rectangle r = new java.awt.Rectangle();
		s.setParent(getMarkedParent());

		do {
			counter++;
			if ((s.getClass() == Square.class)
					|| (s.getClass() == SquareProxy.class)
					|| (s.getClass() == Circle.class)) {
				r.height = r.width = (int) Math.round(Math.random()
						* getMaxSize(s) * s.getParent().getScale());
			} else {
				r.width = (int) Math.round(Math.random() * getMaxSize(s)
						* s.getParent().getScale());
				r.height = (int) Math.round(Math.random() * getMaxSize(s)
						* s.getParent().getScale());
			}
			r.x = (int) Math.round(Math.random()
					* (s.getParent().getWidth() - r.width))
					+ s.getParent().getX();
			r.y = (int) Math.round(Math.random()
					* (s.getParent().getHeight() - r.height))
					+ s.getParent().getY();

			if (s.getParent().hasChildren()) {
				s.setSibling(s.getParent().getLastChild());				
				s.handleOverlap(s.getSibling(), r);
				/**
				 * This is where the code for the removal of overlap between
				 * shapes should be put.
				 */
				// TODO Fix code here
			}

		} while (isToSmall(s, r.width, r.height) && (counter < 100));

		if (!(counter < 100)) {
			ShapeApplet.setOutputText(s.getClass().toString()
					+ " can not be placed");
			System.out.println(s.getClass().toString() + " can not be placed");
		} else {
			s.setX(r.x);
			s.setY(r.y);
			s.setWidth(r.width);
			s.setHeight(r.height);
			s.setScale((s.getWidth() / 400.0) * s.getParent().getScale());
			if (s.getClass() == Square.class) {
				((Square) s).setDepth(((Square) s.getParent()).getDepth() + 1);
			}
			s.getParent().addChild(s);
		}
	}

	/**
	 * Creates a square.
	 * 
	 * @return A square.
	 */
	public Square createSquare() {
		Square square = new Square();
		create(square);
		return square;
	}

	/**
	 * Creates a squareproxy.
	 * 
	 * @return A squareproxy.
	 */
	public SquareProxy createSquareProxy() {
		SquareProxy proxy = new SquareProxy();
		create(proxy);
		return proxy;
	}

	/**
	 * Creates a circle.
	 */
	public void createCircle() {
		create(new Circle());
	}

	/**
	 * Create a triangle.
	 */
	public void createTriangle() {
		create(new Triangle());
	}

	/**
	 * Creates a rectangle.
	 */
	public void createRectangle() {
		create(new Rectangle());
	}
}
