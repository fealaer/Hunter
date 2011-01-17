package hunter;

import hunter.HunterServerInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.naming.*;
/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                frame = new GameFrame();
            }
        });
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static HunterServerInterface connect()
    {
        try {
            Context namingContext = new InitialContext();
            String url = "rmi://localhost/hunter_server";
            server = (HunterServerInterface) namingContext.lookup(url);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return server;
    }

    public static HunterServerInterface getServer()
    {
        return server;
    }
    
    private static javax.swing.JFrame frame;
    private static HunterServerInterface server;
}
