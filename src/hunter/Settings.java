package hunter;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class Settings {

    private Settings()
    {
        Preferences root = Preferences.userRoot();
        node = root.node("/com/fealaer/hunter");
        try
        {
            if (!node.nodeExists("players"))
            {
                Preferences players = node.node("players");
                players.put("player", System.getProperty("user.name"));
            }
            if (!node.nodeExists("servers"))
            {
                Preferences players = node.node("servers");
                players.put("server", System.getProperty("localhost:666999"));
            }
        }
        catch (BackingStoreException ex)
        {
            ex.printStackTrace();
        }
    }

    public static Settings init() {
        if (settings == null)
        {
            settings = new Settings();
        }
        return settings;
    }

    public static ArrayList getPlayers()
    {
        Preferences players = node.node("players");
        return new ArrayList();
    }

    private static Preferences node;
    private static Settings settings;
}
