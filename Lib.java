import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Lib {

    private LinkedList<Book> myList;

    // Constructor to initialize the library and populate the list from the file
    public Lib() {
        String line;
        Book tempBook;
        String temp[] = new String[3];
        myList = new LinkedList<Book>();

        // Populate the list from the file...
        try {
            FileReader fR = new FileReader("logins.txt");
            BufferedReader bR = new BufferedReader(fR);

            while ((line = bR.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

                for (int i = 0; i < 3; i++) {
                    temp[i] = st.nextToken();
                }

                tempBook = new Book(temp[1], temp[0], Float.parseFloat(temp[2]));
                myList.add(tempBook);
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    // Method to add a book to the library
    public synchronized void addBook(String t, String a, String p) {
        Book temp = new Book(a, t, Float.parseFloat(p));
        String line;
        myList.add(temp);

        try {
            FileWriter fR = new FileWriter("logins.txt", true); // Append mode
            BufferedWriter bR = new BufferedWriter(fR);
            line = t + " " + a + " " + p + "\n";
            bR.append(line);
            bR.close();
            fR.close();
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    // Method to search for a book by title
    public synchronized String searchBook(String t) {
        Book temp;
        int found = 0;
        Iterator<Book> i = myList.iterator();
        String result = "Not found";

        while (i.hasNext() && found == 0) {
            temp = i.next();
            if (temp.getTitle().equalsIgnoreCase(t)) {
                result = temp.toString();
                found = 1;
            }
        }

        return result;
    }

    // Method to get a list of all books in the library
    public synchronized String[] listOfBooks() {
        Book temp;
        Iterator<Book> i = myList.iterator();
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
