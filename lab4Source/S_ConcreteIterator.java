package lab4Source;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class S_ConcreteIterator extends AbstractIterator {

	private AbstractShape current;
	private AbstractList l;
	int position = -1;

	public S_ConcreteIterator(Square root) {
		this.l = new ArrayList();
		root.getListOfShapes(l);
		int hej=1;
	}

	@Override
	public void first() {
		if (l.size() >= 1)
			position = 0;

	}

	@Override
	public void next() {
		if (!isDone() && position!=-1)
			position++;
		else
			throw new NoSuchElementException();
	}

	@Override
	public boolean isDone() {
		return position == l.size();
	}

	@Override
	public Object currentItem() {
		return l.get(position);
	}

}
