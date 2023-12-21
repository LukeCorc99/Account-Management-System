import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Logins {

    private LinkedList<Account> myList;

    // Initialize logins and add to file
    public Logins() {
        String line;
        Account tempAccount;
        myList = new LinkedList<Account>();

        // Populate the list from the file
        try {
            FileReader fR = new FileReader("logins.txt");
            BufferedReader bR = new BufferedReader(fR);
        
            while ((line = bR.readLine()) != null) {
                String[] parts = line.split("\\s+", 6);
        
                if (parts.length == 6) {
                    tempAccount = new Account(parts[0], parts[1], parts[2], parts[3], parts[4], Float.parseFloat(parts[5]));
                    myList.add(tempAccount);
                } else {
                    System.err.println("Error parsing line: " + line);
                }
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
            FileWriter fR = new FileWriter("logins.txt", true); // Append mode
            BufferedWriter bR = new BufferedWriter(fR);
            line = n + " " + pps + " " + em + " " + p + " " + a + " " + i + "\n";
            bR.append(line);
            bR.close();
            fR.close();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    // Method to search for an account by name
    public synchronized String searchAccount(String t) {
        Account temp;
        int found = 0;
        Iterator<Account> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getName().equalsIgnoreCase(t)) {
                result = temp.toString();
                found = 1;
            }
        }

        return result;
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