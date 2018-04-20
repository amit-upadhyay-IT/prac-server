/*
 * An object is present on one machine and this object has to be accessed from another machine to invoke the method of the object.
 * 
 * Here client will invoke the object method residing on the server.
 * 
 * 
 * Client -> Stub (proxy for object, i.e. LocateRegistry) -> RRL -> T.L
 * 
 * Server -> Skeleton -> RRL -> T.L
 * 
 */

public class Client {

	public static void main(String[] args) throws Exception {
		// step 1: accept two string
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.println("Enter String 1:");
		String str1 = sc.nextLine();
		System.out.println("Enter String 2:");
		String str2 = sc.nextLine();
		
		// step 2: formation of stub i.e. taking out the proxy for the server using LocateRegistry
		try {
			java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1111);
			
			// step 3: marshalling process, i.e. serializing the server object to do so bind the object on basis of some key
			try {
				HelperInterface hi = (HelperInterface)registry.lookup("ServerKey");
				
				// step 4: calling the remote method
				String str3 = hi.concatenateStrings(str1, str2);
				System.out.println("Concatenated String: " + str3);
			} catch (java.rmi.NotBoundException e) {
				System.out.println("The lookup not found: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (java.rmi.RemoteException e) {
			System.out.println("I think port already exists: "+ e.getMessage());
			e.printStackTrace();
		}
		sc.close();
	}

}

