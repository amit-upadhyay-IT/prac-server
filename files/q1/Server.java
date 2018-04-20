import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class Server {

	public static void main(String[] args) {
		// Step1: get client socket
		try {
			System.out.println("Server waiting for connection");
			ServerSocket serverSoc = new ServerSocket(4567);
			final Socket socket = serverSoc.accept();
			System.out.println("Recieved connection from "+ socket.getInetAddress()+" on port "+socket.getPort());
			
			// Step 2: get message but use thread to do so
			Thread readMsg = new Thread(new Runnable() {
				@Override
				public void run()
				{
					BufferedReader br;
					try {
						br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String msg;
						while ((msg = br.readLine()) != null)
						{
							System.out.println("Client message: "+ msg);
						}
					} catch (IOException e) {
						System.out.println();
						e.printStackTrace();
					}
				}
			});
			readMsg.start();
			
			// Step 3: sent to client thread
			Thread sendThread = new Thread(new Runnable() {
				@Override
				public void run()
				{
					// create printwriter
					try {
						PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						while (true)
						{
							BufferedReader inputBr = new BufferedReader(new InputStreamReader(System.in));
							String outMessage = inputBr.readLine();
							out.println(outMessage);
							out.flush();
							System.out.println("Enter your message to client: ");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			sendThread.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

