package hunter;

import java.util.Hashtable;
import javax.swing.*;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("Gifts Hunter");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new PlayersJPanel();
        setPanel("PlayerFrame", panel);
        add(panel);
        setVisible(true);
    }

    public final JPanel getPanel(String panelName) {
        return (JPanel) panels.get(panelName);
    }

    public final void setPanel(String panelName, JPanel panel) {
        panels.put(panelName, panel);
    }

    public final void changePanelsActivity(JPanel nowPanel, String nextPanel) {
        JPanel panel = getPanel(nextPanel);
        if (panel == null) {
            try {
                panel = (JPanel) Class.forName(
                        this.getClass().getPackage().getName() + "." + nextPanel,
                        true,
                        this.getClass().getClassLoader()).newInstance();
                setPanel(nextPanel, panel);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
            add(panel);
        }
        nowPanel.setVisible(false);
        panel.setVisible(true);
    }
    private java.util.Hashtable panels = new Hashtable<String, JPanel>();
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 630;
}
