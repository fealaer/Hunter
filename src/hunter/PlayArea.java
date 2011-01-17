package hunter;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class PlayArea extends JComponent
{
	public PlayArea(int nTargets, int width, int height, String bgImg)
	{
		try
		{
			bg = ImageIO.read(this.getClass().getResource(bgImg));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());

		thrower = new Thrower(nTargets, width, height);
		start();
	}

	private void start()
	{

		class Animator implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{
				if (thrower.getTargetsSize() == 0)
				{
					stop();
				}
				repaint();
			}
		}
		
		timer = new Timer(DELAY, new Animator());
		timer.start();
	}

	private void stop()
	{
		timer.stop();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, this);
		thrower.drawTargets((Graphics2D) g);
	}
	/**
	 *  Animation loop delay factor (in milliseconds). The paint(Graphics g)
	 *  method is called every DELAY milliseconds.
	 */
	private final static int DELAY = 80;
	private BufferedImage bg;
	private int score;
	private Thrower thrower;
	private Timer timer;

	// the square containing the mouse cursor
	private class MouseHandler extends MouseAdapter
	{

		@Override
		public void mousePressed(MouseEvent event)
		{
			if (event.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
			{
				boolean res = thrower.shoot(event.getPoint());
				if (res)
				{
					++score;
				}
				else if (res)
				{
					--score;
				}
			}
		}
	}

	private class MouseMotionHandler implements MouseMotionListener
	{

		public void mouseMoved(MouseEvent event)
		{
			// set the mouse cursor to cross hairs if it is inside
			// a rectangle
			if (thrower.find(event.getPoint()) == null)
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
