package hunter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */

/**
 * A class that simplifies a few common image operations, in
 * particular, creating a BufferedImage from an image file and
 * place them into array.
 */
public class TargetImages
{

	/** Create Image from a file, then turn that into a
	 * BufferedImage and place in array.
	 */
	private TargetImages(String imageFile)
	{
		try
		{
			//System.out.println(new java.io.File(imageFile).getAbsolutePath());
			images.add(ImageIO.read(this.getClass().getResource(imageFile)));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static BufferedImage get (int index)
	{
		return (BufferedImage) images.get(index);
	}
	
	public static void add (String imageFile)
	{
		new TargetImages(imageFile);
	}

	public static int getSize ()
	{
		return images.size();
	}

	private static Vector images = new Vector();
}
