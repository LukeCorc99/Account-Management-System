import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Requester {
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    String response;
    Scanner input;

    Requester() {
        input = new Scanner(System.in);
    }

    void run() {
        try {
            // Create a socket to connect to the server
            requestSocket = new Socket("127.0.0.1", 2006);
            System.out.println("Connected to localhost");

            // Get Input and Output streams for communication with the server
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());

            // Client Communications
            try {
                do {
                    // Read and display the message from the server
                    message = (String) in.readObject();
                    System.out.println(message);

                    // Get user response
                    response = input.nextLine();
                    // Send the user response to the server
                    sendMessage(response);

                    if (response.equalsIgnoreCase("1")) {
                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

						message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                    } else if (response.equalsIgnoreCase("2")) {
                        message = (String) in.readObject();
                        System.out.println(message);

                        response = input.nextLine();
                        sendMessage(response);

                        message = (String) in.readObject();

                        if (message.equalsIgnoreCase("Not found")) {
                            System.out.println(message);
                        } else {
                            String[] result = message.split("[*]");

                            System.out.println("Name " + result[0]);
                            System.out.println("PPS Number " + result[1]);
                            System.out.println("Email " + result[2]);
							System.out.println("Password " + result[3]);
                            System.out.println("Address " + result[4]);
                            System.out.println("Initial Balance " + result[5]);
                        }
                    } else if (response.equalsIgnoreCase("3")) {
                        message = (String) in.readObject();

                        int temp = Integer.parseInt(message);

                        for (int i = 0; i < temp; i++) {
                            message = (String) in.readObject();

                            String[] result = message.split("[*]");

                            System.out.println("Name " + result[0]);
                            System.out.println("PPS Number " + result[1]);
                            System.out.println("Email " + result[2]);
							System.out.println("Password " + result[3]);
                            System.out.println("Address " + result[4]);
                            System.out.println("Initial Balance " + result[5]);
                        }
                    }

                    // Repeat messaging
                    message = (String) in.readObject();
                    System.out.println(message);

                    response = input.nextLine();
                    sendMessage(response);

                } while (response.equalsIgnoreCase("1"));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // Closing connection
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // Send a message to the server
    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Create Instance of Requester and run
        Requester client = new Requester();
        client.run();
    }
}