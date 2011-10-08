/*
 * Created on 2005-apr-20
 *
 */
package lab4Source;

/**
 * An abstract class describing how an iterator looks like.
 * @author Peter Sunnergren
 */
public abstract class AbstractIterator {
	/**
	 * Reset the iterator and make it point to the first item.
	 */
	public abstract void first();
	
	/**
	 * Make the iterator take a step to the next item in the list.
	 */
	public abstract void next();
	
	/**
	 * Checks if the iteration is done, the end of the list is reached.
	 * @return True if it is done.
	 */
	public abstract boolean isDone();
	
	/**
	 * Gets the current item in the list.
	 * @return The current item.
	 */
	public abstract Object currentItem();
}

