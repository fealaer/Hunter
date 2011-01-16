package hunter;

import javax.swing.*;

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
		PlayArea component = new PlayArea (50, DEFAULT_WIDTH, DEFAULT_HEIGHT - 80, "img/backgrounds/bg1_sign.png");
		add(component);
	}
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 630;
}
