package hunter;

import java.rmi.Remote;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public interface HunterServerInterface  extends Remote{
    public JPanel createGame(String name, JFrame frame, String playerName);

    public JPanel joinGame(String name, JFrame frame, String playerName);

    public Enumeration getGamesList();

}
