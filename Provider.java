import java.io.*;
import java.net.*;

public class Provider {
	
	public static void main(String args[]) {
		//Create instance of socket and logins
		ServerSocket providerSocket;
		Logins myList = new Logins(); 
		try {
			// Server socket with port and backlogs
			providerSocket = new ServerSocket(2006, 10);

			while (true) {
				System.out.println("Waiting for connection");
			
				// Accept a connection from a client
				Socket connection = providerSocket.accept();
			
				// Create a new thread for this connection and start it
				ServerThread T1 = new ServerThread(connection, myList);
				T1.start();
			}
			// serverSocket.close() 
		} catch (IOException e1) {
			// Handle IO exceptions
			e1.printStackTrace();
		}
	}
}

