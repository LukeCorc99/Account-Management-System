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
    String message4;
    String message5;
    String message6;
    Logins allLogins;
    int loggedIn = 0;

    // Initialise ServerThread with an instance of the socket and Logins
    public ServerThread(Socket s, Logins login) {
        myConnection = s;
        allLogins = login;
    }

    public void run() {
        try {
            // Initialize output and input streams
            out = new ObjectOutputStream(myConnection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(myConnection.getInputStream());

            // Server Communications
            do {
                // Prompt client to enter choice
                sendMessage("Please enter 1 to Register an Account or 2 to Login to an Account");
                message = (String) in.readObject();

                // Register account
                if (message.equalsIgnoreCase("1")) {
                    sendMessage("Please enter name:");
                    message1 = (String) in.readObject();

                    sendMessage("Please enter PPS number:");
                    message2 = (String) in.readObject();

                    sendMessage("Please enter Email:");
                    message3 = (String) in.readObject();

                    sendMessage("Please enter Password:");
                    message4 = (String) in.readObject();

                    sendMessage("Please enter Address:");
                    message5 = (String) in.readObject();

                    sendMessage("Please enter Initial Balance:");
                    message6 = (String) in.readObject();

                    // Register account
                    allLogins.addAccount(message1, message2, message3, message4, message5, message6);

                    // Login
                } else if (message.equalsIgnoreCase("2")) {
                    sendMessage("Please enter the name");
                    message1 = (String) in.readObject();

                    sendMessage("Please enter the password");
                    message2 = (String) in.readObject();

                    String result = allLogins.searchAccount(message1, message2);
                    sendMessage(result);

                    message3 = (String) in.readObject();

                    // Return all registered accounts
                    if (message3.equalsIgnoreCase("1")) {
                        String[] temp = allLogins.listOfAccounts();
                        sendMessage("" + temp.length);

                        for (int i = 0; i < temp.length; i++) {
                            sendMessage(temp[i]);
                        }
                    }

                        // Add money to balance
                        if (message3.equalsIgnoreCase("2")) {

                            sendMessage("Please enter the password:");
                            message1 = (String) in.readObject();

                            sendMessage("Please enter the amount to add:");
                            float amountToAdd = Float.parseFloat((String) in.readObject());

                            String result2 = allLogins.addMoney(message1, amountToAdd);
                            sendMessage(result2);

                        }
            // Transfer money
            if (message3.equalsIgnoreCase("3")) {
                sendMessage("Enter password:");
                String fromPassword = (String) in.readObject();

                sendMessage("Enter email of account you want to transfer:");
                String toEmail = (String) in.readObject();

                sendMessage("Enter PPS of account you want to transfer to:");
                String toPps = (String) in.readObject();

                sendMessage("Enter the amount to transfer:");
                float transferAmount = Float.parseFloat((String) in.readObject());

                String transferFromResult = allLogins.transferMoneyfromAccount(fromPassword, transferAmount);
                String transferToResult = allLogins.transferMoneytoAccount(toEmail, toPps, transferAmount);

                if(!transferFromResult.equalsIgnoreCase("Not found") && !transferToResult.equalsIgnoreCase("Not found")){
                String result3 = "Successfully transferred";
                sendMessage(result3);
                }
              
            }
                        

                    }

                

                // Prompt client to enter 1 to repeat or any other value to exit
                sendMessage("Please enter 1 to repeat or any other value to exit");
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

    // Send a message to the client
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