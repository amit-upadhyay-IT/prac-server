import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
public class myclient {

	public static void main(String[] args)  throws Exception 
	{
		
	BufferedReader bb= new BufferedReader (new InputStreamReader(System.in));
		
		// Take First String 
		System.out.println("Enter string 1");
		String str1=bb.readLine();
		
		// Take Seconed String 
		System.out.println("Enter string 2");
		String str2=bb.readLine();
		
		// Get Registry from Server
		Registry rr= LocateRegistry.getRegistry("localhost", 1111);
		
		// Search the server object using name abc in Registry. 
		myinterface m =(myinterface) rr.lookup("abc");
		
		// Calling input() method from server 
		String str3= m.input(str1, str2);
		
		// Print Concatenated String 
		System.out.println(str3);
		
		
		
	}

}

