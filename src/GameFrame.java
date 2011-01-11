import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class GameFrame extends JFrame
{

	public GameFrame()
	{
		setTitle("Gifts Hunter");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		

		// add component to frame
		Thrower component = new Thrower();
		add(component);
	}
	
	public static final int DEFAULT_WIDTH = 1024;
	public static final int DEFAULT_HEIGHT = 788;
}
