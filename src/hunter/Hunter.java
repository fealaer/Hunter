package hunter;

import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.*;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class Hunter
{

	public Hunter ()
	{
		name = "Fealaer";
		
	}

	public String getName()
	{
		return name;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{

			public void run()
			{
				PlayerFrame frame = new PlayerFrame();
				setFrame("PlayerFrame", frame);
//				GameFrame frame = new GameFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public static JFrame getFrame(String frameName)
	{
		return (JFrame) frames.get("PlayerFrame");
	}

	public static void setFrame(String frameName, JFrame frame)
	{
		frames.put(frameName, frame);
	}

	private String name;
	private static java.util.Hashtable frames = new Hashtable<String, JFrame>();
}
