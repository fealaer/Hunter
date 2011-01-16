package hunter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class Target
{

	/**
	 *  Create a Target object that describes a graphical Target. For
	 *  performance reasons, the Target is predrawn into an image buffer.
	 *
	 *  @param awidth game frame drawing surface width
	 *  @param aheight game frame drawing surface height
	 *  @param radius target's radius
	 *  @param startx target's starting x position on drawing surface
	 *  @param starty target's starting y position on drawing surface
	 *  @param velocity target's velocity
	 *  @param pause delay factor used to minimize clumping
	 */
	Target(int x, int y, int sidelength, int pause, int imgIndex, int velocity, int height)
	{
		this.x = x;
		this.y = y;
		this.sidelength = sidelength;
		this.pause = pause;
		this.imageIndex = imgIndex;
		this.velocity = velocity;
		this.height = height;

		target = new Rectangle2D.Double(x - sidelength / 2,
										y - sidelength / 2,
										sidelength,
										sidelength);
	}

	/**
	 *  Move the Target downwards according to its velocity. Movement does
	 *  not begin until the pause counter reaches zero. (This counter is used
	 *  to minimize clumping.) When the Target reaches the bottom of the
	 *  graphics device's drawing surface, it's recycled back to its starting
	 *  vertical position, but with a different horizontal position.
	 *
	 *  @param g graphics device on which Target is painted
	 */
	public boolean move(Graphics2D g)
	{
		if (--pause >= 0)
		{
			return true;
		}
		else
		{
			pause = 0;
		}

		drawTarget(g);

		y += velocity;

		if (y + sidelength > height)
		{
			return false;
		}
		return true;
	}

	/**
	 *  Draw the Target in terms of its six branches, and their three levels
	 *  of sub-branches. Each branch is positioned 60 degrees from the previous
	 *  branch. Furthermore, each branch is raised 30 degrees above the x axis.
	 *
	 *  @param g graphics device on which branch and sub-branches are painted
	 */
	private void drawTarget(Graphics2D g2)
	{
		target = new Rectangle2D.Double(x - sidelength / 2,
										y - sidelength / 2,
										sidelength,
										sidelength);
		g2.setPaint(new TexturePaint(TargetImages.get(imageIndex), target));
		g2.fill(target);
		g2.draw(target);
	}

	public Rectangle2D getTargetSquare()
	{
		return target;
	}
	/**
	 *  Image for target.
	 */
	private int imageIndex;
	/**
	 *  Graphics device on which Target is painted.
	 */
	private Rectangle2D target;
	/**
	 *  Snowflake velocity. Negative velocities are not considered for this
	 *  applet. However, if you want to simulate wind gusts, the velocity could
	 *  be negative to illustrate snowflakes being blown upwards.
	 */
	private int velocity;
	private int pause;
	/**
	 *  
	 */
	private int sidelength;
	/**
	 *  Target's current horizontal position.
	 */
	private int x;
	/**
	 *  Target's current vertical position.
	 */
	private int y;
	/**
	 *  Frame height. Passed to each Target object's constructor and also
	 *  used to determine target destroy.
	 */
	private int height;
}
