import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

	public static void main(String[] args) {

		String ip = "localhost";
		int port = 4567;
		// Step 1: create a socket channel
		final Socket socket;
		try
		{
			socket = new Socket(ip, port);
			
			// Step 2: send data to server by using OutputStreamWriter and PrintWriter
			Thread sendThread = new Thread(new Runnable(){
				@Override
				public void run() {
					
					if (socket.isConnected())
					{
						BufferedReader inputBr = new BufferedReader(new InputStreamReader(System.in));
						while (true)
						{
							// send to server
							try {
								// Ask for user input
								System.out.println("Enter a string: Enter 'EXIT' to exit: ");
								String inputText = inputBr.readLine();
								PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
								out.println(inputText);
								out.flush();
								if (inputText.equalsIgnoreCase("EXIT"))
								{
									break;
								}
							} catch (IOException e) {
								System.out.println(e.getMessage());
								e.printStackTrace();
							}
						}
						try {
							// make sure to flag a var so that next time the socket should not access anything
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			sendThread.start();
			
			// Step 3: get ready to receive 
			Thread receiveThread = new Thread(new Runnable(){
				public void run()
				{
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String msg = null;
						while ((msg = br.readLine()) != null)
						{
							System.out.println("Server message: " + msg);
							System.out.println("You can enter something to sent to server: ");
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			});
			receiveThread.start();
		}
		// if the port is already occupied
		catch (Exception e)
		{
			System.out.println("Port already occupied: "+ e.getMessage());
			e.printStackTrace();
		}
	}
}
