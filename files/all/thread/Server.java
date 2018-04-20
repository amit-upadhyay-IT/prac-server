import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {
		// Port No 444;
		final int port = 44444;
		System.out.println("Server waiting for connection on port "+ port);
		
		// Creating a new Server Socket ss
		ServerSocket ss = new ServerSocket(port);
		
		// Creating another Socket to handle client  
		Socket clientSocket = ss.accept();
		// getInetAddress() returns IP Address of Client
		// getPort() returns Port No  
		System.out.println("Recieved connection from "+ clientSocket.getInetAddress()+" on port "+clientSocket.getPort());
		
		
		// Create two threads to send and receive from client
		// User Class : RecieveFromClient (RECEIVING DATA FROM CLIENT)
		// Create Object of Thread 
		// Start() method present in Thread Class executes Thread
				
		RecieveFromClientThread recieve = new RecieveFromClientThread(clientSocket);
		Thread thread = new Thread(recieve);
		thread.start();
		
		// User Class : SentToClientThread (SENDING DATA TO CLIENT) 
		// Create Object of Thread 
		// Start() method present in Thread Class executes Thread
		SendToClientThread send = new SendToClientThread(clientSocket);
		Thread thread2 = new Thread(send);
		thread2.start();
	}}

// Class 1: RecieveFromClientThread for Receiving data From Client

// User Defined Thread Class (implements Runnable) Runnable is an interface 
class RecieveFromClientThread implements Runnable
{
Socket clientSocket=null;
BufferedReader bb = null;
	
// This is default Constructor 
public RecieveFromClientThread(Socket clientSocket)
{
	this.clientSocket = clientSocket;
}
	
//run() is the default Method where Thread starts to execute

public void run()  
{
	try{
		bb = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));		
		String str1;
		while(true)
		{
		while((str1= bb.readLine())!= null){//assign message from client to messageString
		
			if(str1.equals("EXIT"))
			{
				break; //break to close socket if EXIT
			}
			System.out.println("From Client: " + str1);//print the message from client
			System.out.println("Please enter something to send back to client..");
		}
		this.clientSocket.close();
		System.exit(0);
	}
		
	}
	catch(Exception e){System.out.println(e);}
	}
	
}

// Class 2: SendToClientThread Used for Sending Data To Client 
class SendToClientThread implements Runnable
{
	// PrintWriter is Predefined Class in Java.io Package Used for Printing on CLient
	PrintWriter pp;
	Socket clientSock = null;
	
	// Constructor 
	public SendToClientThread(Socket clientSock)
	{
		this.clientSock = clientSock;
	}
	
	public void run() {
	try{
	pp =new PrintWriter(new OutputStreamWriter(this.clientSock.getOutputStream()));//get outputstream
		
	while(true)
	{
	String str2 = null;
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//get userinput
			
	str2 = input.readLine();//get message to send to client

	pp.println(str2);//send message to client with PrintWriter
	pp.flush(); //flush the PrintWriter, Clear the Buffer by flushing out Buffer
	System.out.println("Please enter something to send back to client..");
	}
	}
	
	catch(Exception e){System.out.println(e);}	
	}
}


