import java.io.*;
import java.util.*;    
/**
 * Arraylist<Book> class for Bookcase, holds Book objects with methods to parse information
 * 
 * @author James No
 * @version CSC 142 Version 3/16/16 B. Barry
 */
public class Bookcase {
    //---------------------------------------------------------
    //      PRIVATE DATA
    //---------------------------------------------------------
    private ArrayList<Book> Bookcase;
    private int getBookCount;
    private Book getBook;
    //---------------------------------------------------------
    //      CONSTRUCTOR
    //---------------------------------------------------------
    /**
     * Constructor for objects of class Bookcase
     */
    public Bookcase() {
        this.Bookcase = new ArrayList<Book>();
    }
    //---------------------------------------------------------
    //      ACCESSORS
    //---------------------------------------------------------
    /**
     * Returns total amount of Book objects in Bookcase<Book>
     *
     * @return     Int Amount of Books
     */
    public int getBookCount() {
        return getBookCount;
    }

    /**
     * Returns a Book object based on a specified index position in Bookcase<Book>
     *
     * @param  bookPosition   An integer representing a specific index
     * @return     Book of specified index
     */
    public Book getBook(int bookPosition) {
        return this.Bookcase.get(bookPosition);
    }

    /**
     * Returns a Book object based on a specified ISBN String 
     * Checks if foundISBN is true, then gets book based on indexFound
     *
     * @param  ISBN   The ISBN String of a certain Book object
     * @return     Book of specified ISBN
     */
    public Book searchISBN(String isbn) {
        int indexFound = 0;
        boolean foundISBN = false;
        int i = 0;
        while (!foundISBN && i < Bookcase.size() ) {    // Checks to see if matching ISBN in Book is found & i going out of index
            if (this.Bookcase.get(i).getISBN().equals(ISBN) ) { // Checks sequentially if each book has same ISBN as parameter
                indexFound = i;
                foundISBN = true;
            }
            i += 1;
        }
        if (foundISBN) {                                // Runs if true
            getBook = this.Bookcase.get(indexFound);
        }
        return getBook;
    }

    /**
     * Returns a concatenated string of Book attributes 
     *
     * @return     A concatenated string
     */
    public String toString() {
        String descrip = "";
        for (int i = 0; i < Bookcase.size(); i++) {
            descrip += "\nBook isbn: " + Bookcase.get(i).getISBN() + "\n";
            descrip += "Book Author: " + Bookcase.get(i).getAuthor() + "\n";
            descrip += "Book Title: " + Bookcase.get(i).getTitle() + "\n";
            descrip += "Book Publisher: " + Bookcase.get(i).getPublisher() + "\n";       
            descrip += "Publication Date: " + Bookcase.get(i).getMonth() + "/" + Bookcase.get(i).getDay() + "/" + Bookcase.get(i).getYear() + "\n";
            descrip += "Page Count: " + Bookcase.get(i).getPageCount() + "\n";
        }
        return descrip;
    }
    //---------------------------------------------------------
    //      MUTATORS
    //---------------------------------------------------------  
    /**
     * Setter which adds a Book to the last index of Bookcase<Book>
     *
     * @param  book   A Book object
     */
    public void addBook(Book book) {
        this.Bookcase.add(book);
        getBookCount++;
    }

    /**
     * Setter which deletes a Book based on a specified ISBN String
     *
     * @param  ISBN   The ISBN String of a certain Book object
     */
    public void deleteBook(String ISBN) {
        int indexDelete = 0;
        boolean foundISBN = false;
        int i = 0;
        int bookcount = getBookCount();
        while (!foundISBN && i < getBookCount() ) {
            if (this.Bookcase.get(i).getISBN().equals(ISBN) ) {
                indexDelete = i;
                foundISBN = true;
            }
            i += 1;
        }
        if (foundISBN) {
            this.Bookcase.remove(indexDelete);
        }
    }

    /**
     * Sorts Book objects in Bookcase<Book> based on ISBN
     */
    public void sortISBN() {
        Collections.sort(Bookcase);
    }

    /**
     * Clears Bookcase<Book> object of all Books stored within
     */
    public void clearBookcase() {
        this.Bookcase.clear();
    }
    //---------------------------------------------------------
    //      OTHER METHODS
    //---------------------------------------------------------  
    /**
     * Reads a specified txt file which contains organized raw data of Book values 
     * Parses through and sets the values into a Book object
     * Adds Book object into Bookcase<Book> Arraylist
     *
     * @param  inputFile   Specified txt file containing Book values
     */
    public void readFile(File inputFile) throws FileNotFoundException {
        Scanner inScan = new Scanner(inputFile);

        // Initializes variable for amount of books 
        int totalBookCount = inScan.nextInt();

        for (int i = 0; i < totalBookCount; i++) {
            inScan.nextLine();
            inScan.nextLine();
            String isbn = inScan.nextLine();
            String author = inScan.nextLine();
            String title = inScan.nextLine();
            String publisher = inScan.nextLine();
            int year = inScan.nextInt();
            int month = inScan.nextInt();
            int day = inScan.nextInt();
            int pageCount = inScan.nextInt();
            Book readBookFile = new Book(isbn, author, title, publisher, year, month, day, pageCount);
            addBook(readBookFile);
        }
    }

    /**
     * Writes to a specified txt file values from each indexed Book in Bookcase
     * Parses through Book object and outputs each value into txt file
     *
     * @param  outputFile   Specified txt file to write Book values into
     */
    public void writeFile(File outputFile) throws FileNotFoundException {
        PrintStream output = new PrintStream(outputFile);
        output.println(Bookcase.size() );
        output.println("");

        for (int i = 0; i < Bookcase.size(); i++) {
            output.println(Bookcase.get(i).getISBN() );
            output.println(Bookcase.get(i).getAuthor() );
            output.println(Bookcase.get(i).getTitle() );
            output.println(Bookcase.get(i).getPublisher() );
            output.println(Bookcase.get(i).getMonth() + "/" + Bookcase.get(i).getDay() + "/" + Bookcase.get(i).getYear() );
            output.println("");
        }
    }

    /**
     * Test method for Bookcase, testing for constructors, accessors, mutators, and other methods
     */
    public static void test() {
        Book testBook = new Book("666", "David Chang", "Momofuku", "Potter", 1999, 2, 15, 288);
        Book testBook2 = new Book("555", "Marc Vetri", "Rustic Italian Food", "Ten Speed Press", 2014, 10, 15, 150);
        Book testBook3 = new Book("444", "Julia Child", "Mastering the Art of French Cooking", "Watermark", 1965, 8, 15, 31);

        Bookcase testBookcase = new Bookcase();
        try { 
            testBookcase.readFile(new File("testBook.txt")); 
        }
        catch (FileNotFoundException e) {   
            System.out.println("File Not Found");
        }

        // Testing Accessors
        if (testBookcase.getBookCount() != 3) System.out.println("Expected 3 for getBookCount, got: " + testBookcase.getBookCount() );

        // Testing Specifically for getBook()
        if (!testBookcase.getBook(0).getISBN().equals("111") )                  System.out.println("Expected 111 for getISBN, got: " + testBook.getISBN() );
        if (!testBookcase.getBook(0).getAuthor().equals("Mitch Connell") )      System.out.println("Expected Mitch Connell for getAuthor, got: " + testBook.getAuthor() );
        if (!testBookcase.getBook(0).getTitle().equals("Science of Cooking") )  System.out.println("Expected Science of Cooking for getTitle, got: " + testBook.getTitle() );
        if (!testBookcase.getBook(0).getPublisher().equals("Potter") )          System.out.println("Expected Potter for getPublisher, got: " + testBook.getPublisher() );
        if (testBookcase.getBook(0).getYear() != 1955)                          System.out.println("Expected 1955 for getYear, got: " + testBook.getYear() );
        if (testBookcase.getBook(0).getMonth() != 5)                            System.out.println("Expected 5 for getMonth, got: " + testBook.getMonth() );
        if (testBookcase.getBook(0).getDay() != 15)                             System.out.println("Expected 15 for getDay, got: " + testBook.getDay() );
        if (testBookcase.getBook(0).getPageCount() != 328)                      System.out.println("Expected 328 for getPageCount, got: " + testBook.getPageCount() );
        
        // Searching for Book with ISBN 333
        System.out.println(testBookcase.searchISBN("333") );
        
        // Adding testbooks
        testBookcase.addBook(testBook);
        testBookcase.addBook(testBook2);
        testBookcase.addBook(testBook3);

        System.out.println("Book count will appear here.");
        System.out.println(testBookcase.getBookCount() );

        // Testing for writeFile
        System.out.println(testBookcase.toString() );
        try { 
            testBookcase.writeFile(new File("testWrite.txt") );
        }
        catch (FileNotFoundException e) {   
            System.out.println("File Not Found");
        }

        // Sorting ISBN
        testBookcase.sortISBN();
        
        // Testing DeleteBook
        testBookcase.deleteBook("666");
        System.out.println(testBookcase.toString() );

        try { 
            testBookcase.writeFile(new File("testWrite.txt") );
        }
        catch (FileNotFoundException e) {   
            System.out.println("File Not Found");
        }
    }
}
