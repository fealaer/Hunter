package letitsnow;

import java.applet.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;



/**
 *  This class describes the LetItSnow applet.
 *
 *  @author Jeff Friesen
 */


public class LetItSnow extends Applet implements Runnable
{
   /**
    *  Animation loop delay factor (in milliseconds). The paint(Graphics g)

    *  method is called every DELAY milliseconds.
    */


   private final static int DELAY = 80;


   /**
    *  Number of SnowFlake objects (and, hence, snowflakes) managed by this
    *  applet.

    */


   private final static int NFLAKES = 50;


   /**
    *  Radius of largest snowflake. Diameter is twice this value.
    */


   private final static int MAX_RADIUS = 35;



   /**
    *  "Let It Snow! Let It Snow! Let It Snow!" audio clip.
    */


   private AudioClip ac;


   /**
    *  Audio clip is playing flag. Not very accurate, but convenient for this

    *  simple applet.
    */


   private boolean fPlaying;


   /**
    *  Graphics device on which animation frames are painted.
    */


   private Graphics2D gBuffer;


   /**

    *  The graphics device associates with an image buffer. This buffer is
    *  used to eliminate one source of flicker.
    */


   private Image imBuffer;


   /**
    *  Applet height. Passed to each SnowFlake object's constructor and also

    *  used to determine image buffer height.
    */


   private int height;


   /**
    *  Applet width. Passed to each SnowFlake object's constructor and also
    *  used to determine image buffer width.

    */


   private int width;





   /**
    *  "Let It Snow! Let It Snow! Let It Snow!" song lyrics. These lyrics are
    *  displayed on what appears to be a glass pane over the middle of the

    *  applet's drawing surface. Note: I am not certain if these lyrics match
    *  the original lyrics exactly.
    */


   private String [] lyrics =
   {
      "Oh, the weather outside is frightful,",

      "But the fire is so delightful,",
      "And since we've no place to go,",
      "Let it snow, let it snow, let it snow.",
      "It doesn't show signs of stopping,",

      "And I brought some corn for popping;",
      "The lights are turned way down low,",
      "Let it snow, let it snow, let it snow.",
      "When we finally kiss good night,",

      "How I'll hate going out in the storm;",
      "But if you really hold me tight,",
      "All the way home I'll be warm.",
      "The fire is slowly dying,",

      "And, my dear, we're still good-bye-ing,",
      "But as long as you love me so.",
      "Let it snow, let it snow, let it snow."
   };


   /**
    *  Animation thread -- created in the start() method and destroyed in the

    *  stop() method.
    */


   private Thread thdAnimate;


   /**
    *  Datastructure for managing SnowFlake objects.
    */


   private Vector flakes = new Vector ();



   /**
    *  Initialize the applet. SnowFlake objects are created and placed into a
    *  datastructure, a mouse listener is registered for playing and
    *  stopping the music, and an image buffer is created for painting each

    *  animation frame off screen.
    */


   public void init ()
   {
      width = getWidth ();


      height = getHeight ();


      for (int i = 0; i < NFLAKES; i++)
      {

           int radius = random (MAX_RADIUS-5)+5;
           int x = random (width-2*radius);
           int y = -radius*2;
           int v = radius/5+1;
           flakes.add (new Snowflake (width, height, radius, x, y, v,

                       random (NFLAKES)+1));
      }


      ac = getAudioClip (getDocumentBase (), "letitsno.mid");


      addMouseListener (new MouseAdapter ()
                        {

                            public void mouseClicked (MouseEvent e)
                            {
                               if (fPlaying)
                                   ac.stop ();
                               else

                                   ac.play ();


                               fPlaying = !fPlaying;
                            }
                        });


      imBuffer = createImage (width, height);

      gBuffer = (Graphics2D) imBuffer.getGraphics ();
      gBuffer.setRenderingHint (RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
   }


   /**

    *  Paint the next animation frame.
    *
    *  @param g graphics device on which animation frame is painted
    */


   public void paint (Graphics g)
   {
      if (gBuffer == null) // Prevent an exception should paint() be called

          return;          // before init() finishes.


      // Clear entire frame to black.


      gBuffer.setColor (Color.black);


      gBuffer.fillRect (0, 0, width, height);


      // Paint snowflakes.



      for (int i = 0; i < NFLAKES; i++)
      {
           Snowflake sf = (Snowflake) flakes.get (i);
           sf.move (gBuffer);
      }


      // Create and paint snowbank at bottom of frame.



      GeneralPath gp = new GeneralPath ();
      gp.moveTo (0, height-MAX_RADIUS);
      gp.curveTo (width/4, height-MAX_RADIUS/2, width-width/4,
                  height+MAX_RADIUS/4, width, height-MAX_RADIUS);

      gp.lineTo (width, height);
      gp.lineTo (0, height);
      gp.closePath ();


      gBuffer.setPaint (Color.white);
      gBuffer.fill (gp);


      // Create and paint text (with partial transparency) over snowflakes.



      gBuffer.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER,
                                                        0.5f));


      gBuffer.setPaint (Color.black);
      Rectangle2D r2d = new Rectangle2D.Double (width/2-130, height/2-157, 260,

                                                315);
      gBuffer.fill (r2d);


      gBuffer.setComposite (AlphaComposite.SrcOver);





      gBuffer.setFont (new Font ("Serif", Font.BOLD, 14));

      gBuffer.setPaint (Color.yellow);


      FontMetrics fm = gBuffer.getFontMetrics ();
      for (int i = 0; i < lyrics.length; i++)
      {
           int swidth = fm.stringWidth (lyrics [i]);

           gBuffer.drawString (lyrics [i], width/2-130+(260-swidth)/2,
                               height/2-157+fm.getHeight ()*(i+1));
      }


      // Paint image buffer's animation frame on applet's graphics device.



      g.drawImage (imBuffer, 0, 0, this);
   }


   /**
    *  Launch the animation loop. The loop continues as long as the current
    *  thread's reference is the same as the animation thread's reference.

    *  The animation thread's reference is set to null by the applet's stop()
    *  method when the user moves away from the current Webpage -- and hence
    *  the animation stops.
    */


   public void run ()

   {
      Thread thdCurrent = Thread.currentThread ();


      while (thdCurrent == thdAnimate)
      {
         repaint ();


         try
         {
             Thread.sleep (DELAY);

         }
         catch (InterruptedException e)
         {
         }
      }
   }


   /**
    *  Start the animation thread, which invokes the run() method.
    */


   public void start ()

   {
      if (thdAnimate == null)
      {
          thdAnimate = new Thread (this);
          thdAnimate.start ();
      }
   }


   /**
    *  Stop the animation thread, which causes the run() method to exit.

    */


   public void stop ()
   {
      thdAnimate = null;
   }


   /**
    *  The AWT invokes the update() method in response to the repaint() method
    *  call that is made in the run() method. The default implementation of

    *  this method, which is inherited from the Container class, clears the
    *  applet's drawing area to the background color prior to calling paint().
    *  This clearing followed by drawing causes flicker. LetItSnow overrides

    *  update() to prevent the background from being cleared, which eliminates
    *  this source of flicker.
    *
    *  @param g graphics device on which animation frame is painted
    */


   public void update (Graphics g)

   {
      paint (g);
   }


   /**
    *  Obtain a randomly-selected integer between 0 and a limit.
    *
    *  @param limit one more than the largest integer that can be returned
    *
    *  @return an integer ranging from 0 through limit-1

    */


   private int random (int limit)
   {
      return (int) (Math.random ()*limit);
   }
}