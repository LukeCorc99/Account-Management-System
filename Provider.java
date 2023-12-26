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

				// Create a new thread for each connection to accept a connection from a client and run
				Socket connection = providerSocket.accept();
				ServerThread T1 = new ServerThread(connection, myList);
				T1.start();
				
				Socket connection2 = providerSocket.accept();
				ServerThread T2 = new ServerThread(connection2, myList);
				T2.start();
				
				Socket connection3 = providerSocket.accept();
				ServerThread T3 = new ServerThread(connection3, myList);
				T3.start();

				Socket connection4 = providerSocket.accept();
				ServerThread T4 = new ServerThread(connection4, myList);
				T4.start();
				
				Socket connection5 = providerSocket.accept();
				ServerThread T5 = new ServerThread(connection5, myList);
				T5.start();
			}
			// serverSocket.close() 
		} catch (IOException e1) {
			// Handle IO exceptions
			e1.printStackTrace();
		}
	}
}

