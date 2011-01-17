package hunter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class HunterServer extends UnicastRemoteObject implements HunterServerInterface{

    public HunterServer() throws RemoteException {

    }

    public JPanel createGame(String name, JFrame frame, String playerName)
    {
        Game game = new Game(name, frame);
        game.playerJoin(playerName);
        games.put(name, game);
        return game.getPanel();
    }

    public JPanel joinGame(String name, JFrame frame, String playerName)
    {
        Game game = (Game) games.get(name);
        //game.join();
        return game.getPanel();
    }

    public Enumeration getGamesList()
    {
        Vector list =  new Vector<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        return (Enumeration) list;
        //return games.keys();
    }

    private java.util.Hashtable games = new Hashtable<String, Game>();
}
