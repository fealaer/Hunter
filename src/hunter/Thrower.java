package hunter;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class Thrower extends JComponent
{

	public Thrower()
	{
		targets = new ArrayList<Rectangle2D>();
		current = null;
		imageFile = "img/bg.jpg";
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if (bg == null)
		{
			try
			{
				bg = ImageIO.read(this.getClass().getResource(imageFile));
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		g.drawImage(bg,0,0,this);
		Graphics2D g2 = (Graphics2D) g;
		setImagePaint("img/gift.png");
		// draw all squares
		for (Rectangle2D target : targets)
		{
			g2.setPaint(new TexturePaint(image, target));
			g2.fill(target);
			g2.draw(target);
		}
	}

	/**
	 * Finds the first square containing a point.
	 * @param p a point
	 * @return the first square that contains p
	 */
	public Rectangle2D find(Point2D p)
	{
		for (Rectangle2D target : targets)
		{
			if (target.contains((Point) p))
			{
				return target;
			}
		}
		return null;
	}

	/**
	 * Adds a square to the collection.
	 * @param p the center of the square
	 */
	public void add(Point2D p)
	{
		double x = p.getX();
		double y = p.getY();

		current = new Rectangle2D.Double(x - SIDELENGTH / 2,
                                                 y - SIDELENGTH / 2,
                                                 SIDELENGTH,
                                                 SIDELENGTH);
		targets.add(current);
		repaint();


	}

	public void setImagePaint(String ImageFile)
	{
		TargetImage ig = new TargetImage(ImageFile);
		image = ig.getTargetImage();

	}

	/**
	 * Removes a square from the collection.
	 * @param s the square to remove
	 */
	public void remove(Rectangle2D s)
	{
		if (s == null)
		{
			return;
		}
		if (s == current)
		{
			current = null;
		}
		targets.remove(s);
		repaint();
	}
	private ArrayList<Rectangle2D> targets;
	private Rectangle2D current;
	private static final int SIDELENGTH = 25;
	private BufferedImage image;
	private BufferedImage bg;
	private int score;
	private String imageFile;

	// the square containing the mouse cursor
	private class MouseHandler extends MouseAdapter
	{

		@Override
		public void mousePressed(MouseEvent event)
		{
			// add a new square if the cursor isn't inside a square
			current = find(event.getPoint());
			if (current != null && event.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
			{
				remove(current);
				++score;
			}
			else if (current == null && event.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
			{
				--score;
			}
			else if (current == null && event.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK)
			{
				add(event.getPoint());
			}
		}
	}

	private class MouseMotionHandler implements MouseMotionListener
	{

		public void mouseMoved(MouseEvent event)
		{
			// set the mouse cursor to cross hairs if it is inside
			// a rectangle

			if (find(event.getPoint()) == null)
			{
				setCursor(Cursor.getDefaultCursor());
			}
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}

		public void mouseDragged(MouseEvent event)
		{
		}
	}
}
