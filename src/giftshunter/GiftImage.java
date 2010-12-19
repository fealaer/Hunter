package giftshunter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
/**
 * A class that simplifies a few common image operations, in
 * particular, creating a BufferedImage from an image file and
 * using MediaTracker to wait until an image or several images
 * are done loading.
 */
public class GiftImage extends JComponent
{

	/** Create Image from a file, then turn that into a
	 * BufferedImage.
	 */
	public GiftImage(String imageFile)
	{
		try
		{
			bufferedImage = ImageIO.read(this.getClass().getResource(imageFile));
		}
		catch (IOException ex)
		{
			Logger.getLogger(Santa.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public BufferedImage getGiftImage()
	{
		return bufferedImage;
	}
	private BufferedImage bufferedImage;
}
