package giftshunter;

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
		Santa component = new Santa();
		add(component);
	}
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 600;
}
