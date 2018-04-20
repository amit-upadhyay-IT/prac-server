/*
 * For any remote object we need to define an interface,
 * so that we can write the business method which will be available
 * for the client to call on the remote object (Server). Thus, the
 * Server need to implement this interface.
 */
public interface HelperInterface extends java.rmi.Remote {
	
	public String concatenateStrings(String str1, String str2) throws Exception;
}

