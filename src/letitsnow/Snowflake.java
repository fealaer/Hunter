package letitsnow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *  This class describes a snowflake graphics object. Use Snowflake to create,
 *  and move (in a downwards direction only) snowflakes on a graphics device.
 *
 *  @author Jeff Friesen
 */
class Snowflake
{
   /**
    *  The angle between the branch and its two immediate sub-branches, or
    *  between a sub-branch and its two immediate sub-branches.
    */
   private final static double BRANCH_ANGLE = 30.0*Math.PI/180.0;

   /**
    *  Multiplier used in determining a branch's/sub-branch's center point.
    */
   private final static double BRANCH_FACTOR = 0.33;

   /**
    *  Multiplier used in determining sub-branch length -- each level's two
    *  sub-branches should be smaller than the previous level's sub-branch.
    */
   private final static double SHRINK_FACTOR = 0.66;

   /**
    *  Graphics device on which snowflake is painted.
    */
   private Graphics2D gBuffer;

   /**
    *  The graphics device associates with an image buffer. For performance
    *  reasons, each snowflake is painted into its own buffer.
    */
   private Image imBuffer;

   /**
    *  Applet height. When the snowflake's vertical position exceeds this
    *  height, the snowflake is repositioned to its starting height.
    */
   private int aheight;

   /**
    *  Applet width. When the snowflake's vertical position exceeds the
    *  applet height, the snowflake is given a new horizontal position based
    *  on its radius and the applet width. This helps give the illusion of an
    *  unending supply of snowflakes.
    */
   private int awidth;

   /**
    *  Center x-coordinate of image buffer, for line-drawing.
    */
   private int centerx;

   /**
    *  Center y-coordinate of image buffer, for line-drawing.
    */
   private int centery;

   /**
    *  Delay factor used to minimize clumping -- multiple snowflakes that move
    *  together as if they were a single snowflake. This variable must reach
    *  zero before a snowflake can start to move.
    */
   private int pause;

   /**
    *  Snowflake radius. A snowflake is considered to be circular, even if it
    *  does not quite appear that way.
    */
   private int radius;

   /**
    *  Snowflake's starting horizontal position: 0 through awidth-2*radius-1.
    */
   private int startx;

   /**
    *  Snowflake's starting vertical position: this value should be negative,
    *  so that the snowflake can be seen falling into the top of the applet --
    *  not popping into existence.
    */
   private int starty;

   /**
    *  Snowflake velocity. Negative velocities are not considered for this
    *  applet. However, if you want to simulate wind gusts, the velocity could
    *  be negative to illustrate snowflakes being blown upwards.
    */
   private int velocity;

   /**
    *  Snowflake's current horizontal position.
    */
   private int x;

   /**
    *  Snowflake's current vertical position.
    */
   private int y;

   /**
    *  Create a Snowflake object that describes a graphical snowflake. For
    *  performance reasons, the snowflake is predrawn into an image buffer.
    *
    *  @param awidth applet's drawing surface width
    *  @param aheight applet's drawing surface height
    *  @param radius snowflake's radius
    *  @param startx snowflake's starting x position on drawing surface
    *  @param starty snowflake's starting y position on drawing surface
    *  @param velocity snowflake's velocity
    *  @param pause delay factor used to minimize clumping
    */
   Snowflake (int awidth, int aheight, int radius, int startx, int starty,
              int velocity, int pause)
   {
      this.awidth = awidth;
      this.aheight = aheight;
      this.radius = radius;
      this.startx = startx;
      this.starty = starty;
      this.velocity = velocity;
      this.pause = pause;

      centerx = centery = radius;
      x = startx;
      y = starty;

      imBuffer = new BufferedImage (radius*2, radius*2,
                                    BufferedImage.TYPE_INT_ARGB);

      gBuffer = (Graphics2D) imBuffer.getGraphics ();
      gBuffer.setRenderingHint (RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

      drawSnowflake (gBuffer);
   }

   /**
    *  Move the snowflake downwards according to its velocity. Movement does
    *  not begin until the pause counter reaches zero. (This counter is used
    *  to minimize clumping.) When the snowflake reaches the bottom of the
    *  graphics device's drawing surface, it's recycled back to its starting
    *  vertical position, but with a different horizontal position.
    *
    *  @param g graphics device on which snowflake is painted
    */
   void move (Graphics2D g)
   {
        if (--pause >= 0)
        {
            return;
        }
        else
        {
            pause = 0;
        }


      g.drawImage (imBuffer, x, y, null);

      y += velocity;

      if (y > aheight)
      {
          y = starty;
          x = (int) (Math.random ()*(awidth-2*radius));
      }
   }

   /**
    *  Draw the snowflake in terms of its six branches, and their three levels
    *  of sub-branches. Each branch is positioned 60 degrees from the previous
    *  branch. Furthermore, each branch is raised 30 degrees above the x axis.
    *
    *  @param g graphics device on which branch and sub-branches are painted
    */
   private void drawSnowflake (Graphics2D g)
   {
      g.setColor (Color.white);

      for (int branch = 0; branch < 6; branch++)
      {
           double angle = Math.toRadians (branch*60.0+30.0);

           drawSnowflakeBranch (g, 0.0, 0.0, rotateX (radius, 0.0, angle),
                                rotateY (radius, 0.0, angle), 0);
      }
   }

   /**
    *  Draw one of the snowflake's branches and three levels of sub-branches.
    *
    *  @param g graphics device on which branch and sub-branches are painted
    *  @param startx x component of branch/sub-branch start point
    *  @param starty y component of branch/sub-branch start point
    *  @param endx x component of branch/sub-branch end point
    *  @param endy y component of branch/sub-branch end point
    *  @param depth recursion level (0 represents branch level, 3 represents
    *  third -- and final -- sub-branch level)
    */
   private void drawSnowflakeBranch (Graphics2D g, double startx,
                                     double starty, double endx, double endy,
                                     int depth)
   {
      if (depth == 4)
      {
          return;
      }

      g.setStroke (new BasicStroke (depth > 1 ? 1 : 2));
      g.drawLine (centerx+(int) startx, centery+(int) starty,
                  centerx+(int) endx, centery+(int) endy);

      double cx = startx+(endx-startx)*BRANCH_FACTOR;
      double cy = starty+(endy-starty)*BRANCH_FACTOR;

      double nendx = cx+(endx-startx)*SHRINK_FACTOR;
      double nendy = cy+(endy-starty)*SHRINK_FACTOR;

      double rx1 = rotateX (nendx-cx, nendy-cy, BRANCH_ANGLE)+cx;
      double ry1 = rotateY (nendx-cx, nendy-cy, BRANCH_ANGLE)+cy;

      double rx2 = rotateX (nendx-cx, nendy-cy, -BRANCH_ANGLE)+cx;
      double ry2 = rotateY (nendx-cx, nendy-cy, -BRANCH_ANGLE)+cy;

      drawSnowflakeBranch (g, cx, cy, rx1, ry1, depth+1);
      drawSnowflakeBranch (g, cx, cy, rx2, ry2, depth+1);
   }

   /**
    *  Rotate the x component of a point through a clockwise angle around the
    *  (0,0) origin.
    *
    *  @param x point's horizontal component
    *  @param y point's vertical component
    *  @param angle number of radians in which to rotate x component
    *
    *  @return equivalent x component following rotation
    */
   private double rotateX (double x, double y, double angle)
   {
      return x*Math.cos (angle)+y*Math.sin (angle);
   }

   /**
    *  Rotate the y component of a point through a clockwise angle around the
    *  (0,0) origin.
    *
    *  @param x point's horizontal component
    *  @param y point's vertical component
    *  @param angle number of radians in which to rotate y component
    *
    *  @return equivalent y component following rotation
    */
   private double rotateY (double x, double y, double angle)
   {
      return -x*Math.sin (angle)+y*Math.cos (angle);
   }
} 