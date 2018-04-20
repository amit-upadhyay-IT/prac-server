/*
 * Step 1: create reference of Server so that we can bind its reference (i.e. marshalling) in the Registry
 * 		   then create the registry on the port and then bind the remote object.
 * Step 2: make the server UnicastRemoteObject because in RMI the remote server is always Unicase
 * Step 3: implement the HelperInterfac and write logic.
 */

//@SuppressWarnings("serial")
public class Server extends java.rmi.server.UnicastRemoteObject implements HelperInterface {

	protected Server() throws java.rmi.RemoteException {
		System.out.println("Started");
	}

	public static void main(String[] args) {
		// Step 1
		try {
			Server s = new Server();
			java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.createRegistry(1111);
			try {
				registry.bind("ServerKey", s);
			} catch (java.rmi.AlreadyBoundException e) {
				e.printStackTrace();
			}
		} catch (java.rmi.RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Binded Successfully");
	}

	@Override
	public String concatenateStrings(String str1, String str2) {
		return str1.concat(str2);
	}
}

