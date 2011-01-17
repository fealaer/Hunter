package hunter;

import java.rmi.*;
import javax.naming.*;
/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class RMIServer
{
	public static void main(String[] args) throws RemoteException, NamingException
	{
		System.out.println("Constructing server implementation...");

		HunterServer hunterServer = new HunterServer();

		System.out.println("Binding server implementation to registry...");

		Context namingContext = new InitialContext();
		namingContext.bind("rmi:hunter_server", hunterServer);

		System.out.println("Waiting for invocation from clients...");
	}
}