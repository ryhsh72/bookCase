import java.util.Comparator;
/**
 * Class for Book object, which stores data referring to a book.
 * 
 * @author James No
 * @version CSC 142 Version 3/16/16 B. Barry
 */
public class Book implements Quotable, Comparable<Book> {
    //---------------------------------------------------------
    //      PRIVATE DATA
    //---------------------------------------------------------
    private String isbn;
    private String author;
    private String title;
    private String publisher;
    private int year;
    private int month;
    private int day;
    private int pageCount;
    //---------------------------------------------------------
    //      CONSTRUCTORS
    //---------------------------------------------------------
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public Book(String isbn, String author, String title, String publisher, int year, int month, int day, int pageCount) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.month = month;
        this.day = day;
        this.pageCount = pageCount;
    }
    //---------------------------------------------------------
    //      ACCESSORS
    //---------------------------------------------------------
    /**
     * Returns isbn of Book object
     *
     * @return     isbn string of Book
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Returns Author name of Book object
     *
     * @return     Author string of Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns Title of Book object
     *
     * @return     Title string of Book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns Publisher of Book object
     *
     * @return     Publisher string of Book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns Year of recorded publishing of Book object
     *
     * @return     Year int of Book
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns Month of recorded publishing of Book object
     *
     * @return     Month int of Book
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns Day of recorded publishing of Book object
     *
     * @return     Day int of Book
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns Page Count of Book object
     *
     * @return     Page Count int of Book
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Overrides compareTo for Comparable interface, compares ISBN with another Book object's ISBN and returns an int
     * @return     Int representing first book ISBN is <, =, or > than second ISBN
     */
    public int compareTo(Book firstBook) {
        return isbn.compareTo(firstBook.getISBN());
    }
    //---------------------------------------------------------
    //      CONSTRUCTORS
    //---------------------------------------------------------
    /**
     * A concatenated list of strings representing the stored values of Book 
     *
     * @return     Concatenated list of strings
     */
    public String toString() {
        String descrip = "";
        descrip += "Book isbn: " + isbn + "\n";
        descrip += "Book Author: " + author + "\n";
        descrip += "Book Title: " + title + "\n";
        descrip += "Book Publisher: " + publisher + "\n";       
        descrip += "Publication Date: " + month + "/" + day + "/" + year + "\n";
        descrip += "Page Count: " + pageCount + "\n";
        return descrip;
    }

    /**
     * Test method for Book object
     */
    public static void test() {
        Book testBook = new Book("10096357186", "Herve This", "Science of Cooking", "Potter", 1955, 5, 15, 328);

        System.out.println("\nTests on Book started. Failures will be shown here:");

        // Testing Accessors
        if (!testBook.getISBN().equals("10096357186") )         System.out.println("Expected \"10096357186\" for getISBN, got: " + testBook.getISBN() );
        if (!testBook.getAuthor().equals("Herve This") )        System.out.println("Expected Herve This for getAuthor, got: " + testBook.getAuthor() );
        if (!testBook.getTitle().equals("Science of Cooking") ) System.out.println("Expected Science of Cooking for getTitle, got: " + testBook.getTitle() );
        if (!testBook.getPublisher().equals("Potter") )         System.out.println("Expected Potter for getPublisher, got: " + testBook.getPublisher() );
        if (testBook.getYear() != 1955)                         System.out.println("Expected 1955 for getYear, got: " + testBook.getYear() );
        if (testBook.getMonth() != 5)                           System.out.println("Expected 5 for getMonth, got: " + testBook.getMonth() );
        if (testBook.getDay() != 15)                            System.out.println("Expected 15 for getDay, got: " + testBook.getDay() );
        if (testBook.getPageCount() != 328)                     System.out.println("Expected 328 for getPageCount, got: " + testBook.getPageCount() );

        System.out.println("\nEnd of tests on Book.\n");

        System.out.println(testBook.toString() );
    }
}
