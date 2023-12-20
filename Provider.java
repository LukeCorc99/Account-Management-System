import java.io.*;
import java.net.*;

public class Provider {
	
	public static void main(String args[]) {
		ServerSocket providerSocket;
		Logins myList = new Logins(); // Create an instance of the Lib class for book operations
		try {
			// 1. Create a ServerSocket on port 2006 with a backlog of 10 connections
			providerSocket = new ServerSocket(2006, 10);

			while (true) {
				// 2. Wait for connection
				System.out.println("Waiting for connection");

				// Create a new thread (ServerThread) for each connection to handle communication and accept a connection from a client
				Socket connection = providerSocket.accept();
				ServerThread T1 = new ServerThread(connection, myList);
				T1.start();
				
				Socket connection2 = providerSocket.accept();
				ServerThread T2 = new ServerThread(connection2, myList);
				T2.start();
				
				Socket connection3 = providerSocket.accept();
				ServerThread T3 = new ServerThread(connection3, myList);
				T3.start();
			}
			// The serverSocket.close() is commented out because the server runs indefinitely,
			// but you may want to close it in a different context (e.g., when the server is stopped).
			// providerSocket.close();
		} catch (IOException e1) {
			// Handle IO exceptions (e.g., if there is an issue with creating the ServerSocket)
			e1.printStackTrace();
		}
	}
}

