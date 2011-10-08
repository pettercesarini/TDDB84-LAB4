package lab4Source;

import java.awt.Graphics;

public class S_PaintVisitor extends AbstractVisitor {

	private Graphics g;	


	public S_PaintVisitor(Graphics g) {
		this.g = g;
	}

	@Override
	public void visit(Square s) {
		s.paint(g);
	}

	@Override
	public void visit(Rectangle r) {
		r.paint(g);
	}

	@Override
	public void visit(Circle c) {
		c.paint(g);
	}

	@Override
	public void visit(Triangle t) {
		t.paint(g);
	}

}
