import java.net.Socket;
import java.io.*;

public class ServerThread extends Thread {

    Socket myConnection;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    String message1;
    String message2;
    String message3;
    Lib library;

    // Constructor to initialize the ServerThread with a socket and library instance
    public ServerThread(Socket s, Lib lib) {
        myConnection = s;
        library = lib;
    }

    // Run method to handle the communication with the client
    public void run() {
        try {
            // Initialize output and input streams for communication with the client
            out = new ObjectOutputStream(myConnection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(myConnection.getInputStream());

            // Server Communications
            do {
                // Prompt the client to enter a choice
                sendMessage("Please enter 1 to ADD A BOOK or 2 to SEARCH FOR A BOOK or 3 for a book listing");
                message = (String) in.readObject();

                if (message.equalsIgnoreCase("1")) {
                    // If the client chooses to add a book
                    sendMessage("Please enter the book title");
                    message1 = (String) in.readObject();

                    sendMessage("Please enter the book author");
                    message2 = (String) in.readObject();

                    sendMessage("Please enter the book price");
                    message3 = (String) in.readObject();

                    // Add the book to the library
                    library.addBook(message1, message2, message3);
                } else if (message.equalsIgnoreCase("2")) {
                    // If the client chooses to search for a book
                    sendMessage("Please enter the book title");
                    message1 = (String) in.readObject();

                    // Search for the book and send the result to the client
                    String result = library.searchBook(message1);
                    sendMessage(result);
                } else if (message.equalsIgnoreCase("3")) {
                    // If the client chooses to get a book listing
                    String[] temp = library.listOfBooks();
                    sendMessage("" + temp.length);

                    // Send each book information to the client
                    for (int i = 0; i < temp.length; i++) {
                        sendMessage(temp[i]);
                    }
                }

                // Prompt the client to enter 1 to repeat or any other value to exit
                sendMessage("Please enter 1 to repeat");
                message1 = (String) in.readObject();

            } while (message1.equalsIgnoreCase("1"));

            // Close input and output streams when done
            in.close();
            out.close();
        } catch (ClassNotFoundException classnot) {
            System.err.println("Data received in an unknown format");
        } catch (IOException e) {
            // Handle IOExceptions
            e.printStackTrace();
        }
    }

    // Method to send a message to the client
    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}