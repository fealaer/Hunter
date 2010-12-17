package giftshunter;

import javax.swing.JFrame;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class GameFrame extends JFrame
{
    public GameFrame()
    {
        setTitle("Gifts Hunter Game");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Santa santa = new Santa();
        add(santa);
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
}
