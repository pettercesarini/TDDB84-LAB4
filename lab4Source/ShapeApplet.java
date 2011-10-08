/*
 * Created on 2005-apr-17
 *
 */
package lab4Source;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Applet to show the shapes and menu on screen.
 * @author Peter Sunnergren
 */

public class ShapeApplet extends JApplet implements MouseListener, ActionListener, KeyListener {
	private ShapePanel panel;
	private static JLabel outputLabel;
	
	/**
	 * Sets up the background for the shapes.
	 */
	public void init() {
		this.setSize(400, 450);
		
		makeMenu();
		
		outputLabel = new JLabel(" ");
		outputLabel.setBackground(Color.green);
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(outputLabel);
		
		panel = new ShapePanel();		
		
		panel.setPaintDefault();
		
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(panel);

		addMouseListener(this);
		addKeyListener(this);
		 		
		setFocusable(true);
		requestFocus();
	}
	
	/**
	 * Creates the menu at the top of the applet.
	 */
	private void makeMenu() {		
		JMenuBar myMenuBar;
		JMenu addMenu;
		JMenuItem rectangleItem; 
		JMenuItem squareItem;
		JMenuItem circleItem;
		JMenuItem triangleItem;
		JMenuItem boxItem;
		
		JMenu functionMenu;
		JMenu countMenu;
		JMenuItem visitorItem;
		JMenuItem iteratorItem; 
		JMenu countSpecMenu;
		JMenuItem defaultSpecItem;
		JMenuItem visitorSpecItem;
		JMenuItem iteratorSpecItem;
		JMenuItem makeTestItem;
		
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		myMenuBar = new JMenuBar();
		setJMenuBar(myMenuBar);
		
		addMenu = new JMenu("Graphics");
		myMenuBar.add(addMenu);
		
		squareItem = new JMenuItem("Square");
		squareItem.addActionListener(this);
		addMenu.add(squareItem);
		
		rectangleItem = new JMenuItem("Rectangle");
		rectangleItem.addActionListener(this);
		addMenu.add(rectangleItem);
		
		circleItem = new JMenuItem("Circle");
		circleItem.addActionListener(this);
		addMenu.add(circleItem);
		
		triangleItem = new JMenuItem("Triangle");
		triangleItem.addActionListener(this);
		addMenu.add(triangleItem);
				
		boxItem = new JMenuItem("Box");
		boxItem.addActionListener(this);
		addMenu.add(boxItem);
		
		functionMenu = new JMenu("Function");
		myMenuBar.add(functionMenu);
		
		countMenu = new JMenu("Count All");
		countMenu.addActionListener(this);
		functionMenu.add(countMenu);
		
		visitorItem = new JMenuItem("Visitor");
		visitorItem.addActionListener(this);
		countMenu.add(visitorItem);
		
		iteratorItem = new JMenuItem("Iterator");
		iteratorItem.addActionListener(this);
		countMenu.add(iteratorItem);

		countSpecMenu = new JMenu("Paint");
		countSpecMenu.addActionListener(this);
		functionMenu.add(countSpecMenu);
		
		defaultSpecItem = new JMenuItem("Default ");
		defaultSpecItem.addActionListener(this);
		countSpecMenu.add(defaultSpecItem);
		
		visitorSpecItem = new JMenuItem("Visitor ");
		visitorSpecItem.addActionListener(this);
		countSpecMenu.add(visitorSpecItem);
		
		iteratorSpecItem = new JMenuItem("Iterator ");
		iteratorSpecItem.addActionListener(this);
		countSpecMenu.add(iteratorSpecItem);
		
		makeTestItem = new JMenuItem("Make test");
		makeTestItem.addActionListener(this);
		functionMenu.add(makeTestItem);
	}
	
	/**
	 * Sets text in the label just below the menu.
	 * @param s The text.
	 */
	public static void setOutputText(String s) {
		outputLabel.setText(s);
	}
	
	/**
	 * Marks shapes as a response to a mouse click.
	 * @param evt Mouseevent that contains the position of the click.
	 */
	public void mouseClicked(MouseEvent evt) {
		panel.markClickedShape(evt);
		repaint();
	} 
	
	/**
	 * This method is called every time a key is pressed but only react when the control key is pressed.
	 * @param evt
	 */
	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == 17) {
			Marked.setControlDown();
		}
	}
	
	/**
	 * This method is called every time a key is released but only react when the control key is released.
	 * @param evt
	 */
	public void keyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == 17) {
			Marked.setControlUp();
		}
	}
	
	/**
	 * Implements the actions that should be taking when an item in the menu is chosen.
	 * @param event An event that originated in the item.
	 */
	public void actionPerformed(ActionEvent event) {
		if ("Rectangle" == event.getActionCommand()) {
			ShapeFactory.instance().createRectangle();
		} else if ("Square" == event.getActionCommand()) {
			ShapeFactory.instance().createSquare();
		} else if ("Circle"== event.getActionCommand()) {
			ShapeFactory.instance().createCircle();
		} else if ("Triangle"== event.getActionCommand()) {
			ShapeFactory.instance().createTriangle();
		} else if ("Box"== event.getActionCommand()) {
			ShapeFactory.instance().createSquareProxy();
		} else if ("Default "== event.getActionCommand()) {
			panel.setPaintDefault();
		} else if ("Visitor "== event.getActionCommand()) {
			panel.setPaintVisitor();
		} else if ("Iterator "== event.getActionCommand()) { 
			panel.setPaintIterator();
		} else if ("Visitor"== event.getActionCommand()) {
			panel.applyVisitor();
		} else if ("Iterator"== event.getActionCommand()) {
			panel.applyIterator();
		} else if ("Make test"== event.getActionCommand()) {
			panel.makeTestShapes(3);
		} else {
		   	System.out.print("Missed menuoption");
		}
		repaint();    	
	}
	/**
	 * Not used but required by the KeyListener interface.
	 * @param evt
	 */
	public void keyTyped(KeyEvent evt) { }
	/**
	 * Not used but required by the MouseListener interface.
	 * @param evt
	 */
	public void mouseEntered(MouseEvent evt) { }   
	/**
	 * Not used but required by the MouseListener interface.
	 * @param evt
	 */
	public void mouseExited(MouseEvent evt) { }    
	/**
	 * Not used but required by the MouseListener interface.
	 * @param evt
	 */
	public void mousePressed(MouseEvent evt) { }	
	/**
	 * Not used but required by the MouseListener interface.
	 * @param evt
	 */
	public void mouseReleased(MouseEvent evt) { }
	
}
