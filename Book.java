public class Book {

    // Instance variables to store book information
    private String author;
    private String title;
    private float price;

    // Constructor to initialize a Book object with author, title, and price
    public Book(String author, String title, float price) {
        super();
        this.author = author;
        this.title = title;
        this.price = price;
    }

    // Method to represent a book as a string in the format: "title*author*price"
    public String toString() {
        return title + "*" + author + "*" + price;
    }

    // Getter and setter methods for author, title, and price
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

