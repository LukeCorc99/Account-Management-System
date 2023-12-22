import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Logins {

    private LinkedList<Account> myList;

    // Initialize logins and add to file
    public Logins() {
        String line;
        Account tempAccount;
        String temp[] = new String[6];
        myList = new LinkedList<Account>();

        // Populate the list from the file
        try {
            FileReader fR = new FileReader("logins.txt");
            BufferedReader bR = new BufferedReader(fR);

            while ((line = bR.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

                for (int i = 0; i < 6; i++) {
                    temp[i] = st.nextToken();
                }

                tempAccount = new Account(temp[0], temp[1], temp[2], temp[3], temp[4], Float.parseFloat(temp[5]));
                myList.add(tempAccount);
            }

        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    // Method to add an account to the logins data
    public synchronized void addAccount(String n, String pps, String em, String p, String a, String i) {
        Account temp = new Account(n, pps, em, p, a, Float.parseFloat(i));
        String line;
        myList.add(temp);

        try {
            FileWriter fR = new FileWriter("logins.txt", true);
            BufferedWriter bR = new BufferedWriter(fR);
            line = n + " " + pps + " " + em + " " + p + " " + a + " " + i + "\n";
            bR.append(line);
            bR.close();
            fR.close();
            // Create a file named 'n + ".txt"'
            String fileName = n + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(n + " " + p + " " + Float.parseFloat(i) + " ");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }

    }

    // Method to search for an account by name
    public synchronized String searchAccount(String name, String password) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getName().equalsIgnoreCase(name) && temp.getPassword().equals(password)) {
                result = temp.toString();
                found = 1;
            }
        }

        return result;
    }

    // Method to search for an account by name
    public synchronized String transferMoneytoAccount(String toName, String email, String pps, float addAmount) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getEmail().equalsIgnoreCase(email) && temp.getPpsNumber().equals(pps)
                    && temp.getName().equals(toName)) {
                // Update the account balance
                temp.setInitialBalance(temp.getInitialBalance() + addAmount);
                result = temp.toString();
                found = 1;
            }
        }

        try {
            String fileName = toName + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("+" + addAmount + " ");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }

        return result;
    }

    // Method to search for an account by name
    public synchronized String transferMoneyfromAccount(String fromName, String password, float subtractAmount) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getPassword().equalsIgnoreCase(password) && temp.getName().equals(fromName)) {
                // Update the account balance
                temp.setInitialBalance(temp.getInitialBalance() - subtractAmount);
                result = temp.toString();
                found = 1;
            }
        }

        try {
            String fileName = fromName + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("-" + subtractAmount + " ");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }

        return result;
    }

    // Method to search for an account by name
    public synchronized String addMoney(String password, float addAmount) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getPassword().equals(password)) {
                // Update the account balance
                temp.setInitialBalance(temp.getInitialBalance() + addAmount);
                result = "Money added successfully.";
                found = 1;
            }
        }

        return result;
    }

    // Method to search for an account by name
    public synchronized String changePassword(String password, String passChange) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getPassword().equals(password)) {
                // Update the account balance
                temp.setPassword(passChange);
                result = "Password changed successfully";
                found = 1;
            }
        }

        return result;
    }

    // Method to get a list of all transactions in an account
    public synchronized String[] listOfTransactions(String accountName) {
        String fileName = accountName + ".txt";
        LinkedList<String> transactions = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] transactionArray = line.split(" ");
                transactions.addAll(Arrays.asList(transactionArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions.toArray(new String[0]);
    }

    // Method to get a list of all accounts in database
    public synchronized String[] listOfAccounts() {
        Account temp;
        Iterator<Account> i = myList.iterator();
        String[] result = new String[myList.size()];
        int count = 0;

        while (i.hasNext()) {
            temp = i.next();
            result[count] = temp.toString();
            count++;
        }

        return result;
    }
}