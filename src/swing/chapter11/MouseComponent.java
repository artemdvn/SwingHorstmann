package swing.chapter11;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class MouseComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current; // rectangle with mouse cursor
	
	private Cursor customCursor;

	public MouseComponent() {
		squares = new ArrayList<>();
		current = null;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("bomb.png");
		customCursor = tk.createCustomCursor(img, new Point(5, 5), "bomb");
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// draw all squares
		for (Rectangle2D r : squares)
			g2.draw(r);
	}

	/**
	 * Finds the first square containing current point
	 * 
	 * @param ð
	 *            a point
	 * @return the first square containing point p
	 */
	public Rectangle2D find(Point2D p) {
		for (Rectangle2D r : squares) {
			if (r.contains(p))
				return r;
		}
		return null;
	}

	/**
	 * Adds a square to the collection
	 * 
	 * @param ð
	 *            the center of the square
	 */
	public void add(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
	}

	/**
	 * Removes a square from the collection
	 * 
	 * @param s
	 *            the square to remove
	 */
	public void remove(Rectangle2D s) {
		if (s == null)
			return;
		if (s == current)
			current = null;
		squares.remove(s);
		repaint();
	}

	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			// add new square if the point is out of the current square
			current = find(event.getPoint());
			if (current == null)
				add(event.getPoint());
		}

		public void mouseClicked(MouseEvent event) {
			// remove current square if it is double clicked
			current = find(event.getPoint());
			
			// get the timeout between clicks
			Integer timerInterval = (Integer) Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
			//System.out.println(timerInterval);
			
			if (current != null && event.getClickCount() >= 2){
				remove(current);
			}
		}
	}

	private class MouseMotionHandler implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) {
			// set the cross cursor if it is inside the square
			if (find(event.getPoint()) == null)
				setCursor(Cursor.getDefaultCursor());
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				//setCursor(customCursor);
		}

		public void mouseDragged(MouseEvent event) {
			if (current != null) {
				int x = event.getX();
				int ó = event.getY();

				// drag current square to center it in point (x,y)
				current.setFrame(x - SIDELENGTH / 2, ó - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
				repaint();
			}
		}
	}
}
