import java.net.*;
import java.io.*;
public class server {     
public static void main (String args[]) throws Exception {
	int serverPort = 8255; // the server port
    System.out.println("Server is Ready");      
	ServerSocket listenSocket = new ServerSocket(serverPort);
	while(true) {
		Socket clientSocket = listenSocket.accept();
		Connection c = new Connection (clientSocket);
	}
}
}
	class Connection extends Thread {
			DataInputStream in;
			DataOutputStream out;
			Socket clientSocket;
			int n;
public Connection (Socket aClientSocket) throws Exception 
{
	clientSocket = aClientSocket;
	in = new DataInputStream( clientSocket.getInputStream());
	out =new DataOutputStream( clientSocket.getOutputStream());
          	this.start(); 
}
public void run() {
		int fact=1;
try {	                               
                n =Integer.parseInt(in.readUTF());  
            		      for (int i= 1; i<=n; i++)
                  {
                      fact=fact*i;
                  }
	      out.writeUTF(Integer.toString(fact));
              }catch (Exception e) { System.out.println(e); }
}
	}
