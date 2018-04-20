import java.rmi.*;

// Interface for defining methods (Only Abstarct / Signature) NO Implementation

public interface myinterface extends Remote {

	public String input(String str1, String str2) throws Exception; 
	
}

