package lab4Source;

public class S_CountVisitor extends AbstractVisitor {

	private int totalNumber;
	@Override
	public void visit(Square s) {
		totalNumber++;

	}

	@Override
	public void visit(Rectangle r) {
		totalNumber++;
	}

	@Override
	public void visit(Circle c) {
		totalNumber++;
	}

	@Override
	public void visit(Triangle t) {
		totalNumber++;
	}


	public void visit(SquareProxy sp) {
		totalNumber++;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

}
