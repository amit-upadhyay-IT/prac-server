import java.net.*;
import java.io.*;
public class client {
public static void main (String args[]) throws Exception {
	 Socket s = null;
               int n;
               int serverPort = 8255;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));
	                                                
                            
                         System.out.println("Enter a number");
                         n= Integer.parseInt(bb.readLine());                                                   
                         out.writeUTF(Integer.toString(n));      	
                         int fact =Integer.parseInt(in.readUTF());	   
                         System.out.println("Factorial of "+n+"is "+fact) ; 
		
}
}
