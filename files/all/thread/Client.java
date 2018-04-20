import java.io.*;
import java.net.*;
public class Client 
{
	public static void main(String[] args) throws Exception
	{
			// Create Socket on Port no 444
			Socket sock = new Socket("localhost",44444);
			
			// Class 1: SendThread : Used for Sending to Server  
			SendThread sendThread = new SendThread(sock);
			Thread thread = new Thread(sendThread);
			thread.start();
			
			// Class 2: RecieveThread : Used for Receiving  from Server 
			RecieveThread recieveThread = new RecieveThread(sock);
			Thread thread2 =new Thread(recieveThread);
			thread2.start();
		
	}
}

// Class 1: RecieveThread Used for receiving Data from Server
class RecieveThread implements Runnable
{
	Socket sock=null;
	BufferedReader bb=null;
	
	public RecieveThread(Socket sock) {
		this.sock = sock;
	}

	public void run() {
		try{
		bb = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));//get inputstream
		String str1 = null;
		while((str1 = bb.readLine())!= null)
		{
			System.out.println("From Server: " + str1);
			System.out.println("Please enter something to send to server..");
		}
		}catch(Exception e){System.out.println(e.getMessage());}
	}
}


// Class 2: SendThread Used for Sending Data to Server
class SendThread implements Runnable
{
	Socket sock=null;
	PrintWriter pp=null;
	BufferedReader bb=null;
	
	public SendThread(Socket sock)
	{
		this.sock = sock;
	}
	
	public void run(){
		try{
			// Checking Socket Connection 
		if(sock.isConnected())
		{
			System.out.println("Client connected to "+sock.getInetAddress() + " on port "+sock.getPort());
			this.pp = new PrintWriter(sock.getOutputStream(), true);	
		while(true){
			System.out.println("Type your message to send to server..type 'EXIT' to exit");
			bb = new BufferedReader(new InputStreamReader(System.in));
			String str2=null;
			str2 = bb.readLine();
			this.pp.println(str2);
			this.pp.flush();
		
			if(str2.equals("EXIT"))
			break;
			}
		sock.close();}
		}catch(Exception e){System.out.println(e);}
	}
}


