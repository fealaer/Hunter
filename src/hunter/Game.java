package hunter;

import java.util.Hashtable;
import javax.swing.*;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class Game extends Thread {

    public Game(String name, JFrame frame) {
        this.name = name;
        panel = new JPanel();//new PlayArea (50, frame.getWidth(), frame.getHeight() - 80, "img/backgrounds/bg1_sign.png");

        frame.add(panel);
        this.start();
    }

    public void run() {
        
        while (true)
        {

        }
    }

    public void playerJoin(String playerName) {
        players.put(playerName, playerName);
    }

    public JPanel getPanel()
    {
        return panel;
    }

    private String name;
    private JPanel panel;
    private Hashtable players = new Hashtable<String, String>();
}
