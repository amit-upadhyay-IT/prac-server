import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class myserver extends UnicastRemoteObject implements myinterface {

	public myserver() throws Exception { System.out.println("Started"); }
	
	// Server implements interface methods 
	public String input(String str1, String str2) throws Exception
	{
		
		return str1.concat(str2);
		
		
	};
	
	public static void main(String[] args)   throws Exception {
		
		
		myserver s= new myserver();
		
		// Create a Local registry on Port No 1100 on server machine 
		// Registry is created to store server object 
		Registry rr=LocateRegistry.createRegistry(1111);
		
		// In registry it will bind server object by using name abc
		rr.bind("abc", s);		
		
		System.out.println("Binded Successfully");
		


	}

}

