import java.io.*;
import java.util.*;
/**
 * UI for Bookcase, issues commands to add/delete & sort Books in ArrayList<Book>
 * 
 * @author James No
 * @version CSC 142 Version 3/16/16 B. Barry
 */
public class BookcaseUI {
    /**
     * UI for Bookcase, Displays menu with conditions, each condition having a sub-condition, and an exit sentinel.
     */
    public static void main(String[] args) {
        int userChoice;
        Bookcase userBookcase = new Bookcase();

        // Initialize Scanner Object
        Scanner inScan = new Scanner(System.in);     
        do {
            // DISPLAY MENU
            // read and write books from/to file
            // sort books
            // display the books
            // add a book (gather input from user)
            // delete book (by isbn)
            System.out.println("\nWelcome to the Bookcase app!");
            System.out.println("");
            System.out.println("1) Read txt file for books");
            System.out.println("2) Write books to txt file");
            System.out.println("3) Sort books in Bookcase");
            System.out.println("4) Display stored books");
            System.out.println("5) Add a book to the Bookcase");
            System.out.println("6) Delete a book from the Bookcase by isbn");
            System.out.println("7) Clear all books from the Bookcase");
            System.out.println("8) Exit\n");
            System.out.print("Enter your choice: ");

            while (!inScan.hasNextInt() ) {      // if menu choice is not an int value, loops for user prompt until int is inputted
                inScan.next();
                System.out.print("Enter your choice: ");
            }

            // User Input Prompt
            userChoice = inScan.nextInt();

            // Variables

            String userFileString;
            String isbn;
            String author;
            String title;
            String publisher;
            int year;
            int month;
            int day;
            int pageCount;

            if (userChoice >= 1 && userChoice <= 7) {   // Checks for numbers between 1 and 7, exits if outside range
                if (userChoice == 1)  {                 // Read specified txt file
                    System.out.print("Please enter the txt file you wish to read from: ");
                    userFileString = inScan.next();
                    File userFile = new File(userFileString);

                    // Testing for existence of userFile
                    if (userFile.exists() ) {
                        try {
                            userBookcase.readFile(userFile);
                            System.out.println("Successfully scanned " + userFileString + ".");
                        }
                        catch (FileNotFoundException e) {
                        } 
                    } else {
                        System.out.println("File not found.");
                    }
                } else if (userChoice == 2) {           // Write to specified txt file
                    System.out.print("Please enter the txt file you wish to write to: ");
                    userFileString = inScan.next();
                    File userFile = new File(userFileString);

                    // Testing for existence of userFile
                    if (userFile.exists() ) {
                        try {
                            userBookcase.writeFile(userFile);
                            System.out.println("Successfully wrote to " + userFileString + ".");
                        }
                        catch (FileNotFoundException e) {
                        }
                    } else {
                        System.out.println("File not found.");
                    }
                } else if (userChoice == 3) {           // Sort books in Bookcase app
                    if (userBookcase.getBookCount() == 0) {
                        System.out.println("There are no books stored in the Bookcase app to sort.");
                    }
                    userBookcase.sortisbn();
                } else if (userChoice == 4) {           // Print books
                    if (userBookcase.getBookCount() == 0) {
                        System.out.println("There are no books stored in the Bookcase app.");
                    }
                    System.out.print(userBookcase.toString() );
                } else if (userChoice == 5) {           // Add a book to app
                    // User input for isbn
                    System.out.print("Please enter the isbn of the book you wish to add: ");
                    isbn = inScan.next();
                    inScan.nextLine();                  // Reads the newline character
                    
                    // User input for Author
                    System.out.print("Please enter the author's name: ");
                    author = inScan.nextLine();
                    
                    // User input for Title
                    System.out.print("Please enter the title: ");
                    title = inScan.nextLine();
                    
                    // User input for Publisher
                    System.out.print("Please enter the publisher: ");
                    publisher = inScan.nextLine();
                    
                    // User input for Year
                    System.out.print("Please enter the year of publication (in XXXX format): ");
                    while (!inScan.hasNextInt() ) {
                        inScan.next();
                        System.out.print("Please enter the year of publication (in XXXX format): ");
                    }
                    year = inScan.nextInt();

                    System.out.print("Please enter the month of publication (as a number): ");
                    while (!inScan.hasNextInt() ) {
                        inScan.next();
                        System.out.print("Please enter the month of publication (in XXXX format): ");
                    }
                    month = inScan.nextInt();

                    System.out.print("Please enter the day of publication (as a number): ");
                    while (!inScan.hasNextInt() ) {
                        inScan.next();
                        System.out.print("Please enter the day of publication (as a number): ");
                    }
                    day = inScan.nextInt();

                    System.out.print("Please enter the page count (as a number): ");
                    while (!inScan.hasNextInt() ) {
                        inScan.next();
                        System.out.print("Please enter the page count (as a number): ");
                    }
                    pageCount = inScan.nextInt();

                    Book userBook = new Book(isbn, author, title, publisher, year, month, day, pageCount);
                    userBookcase.addBook(userBook);

                    System.out.println("Your book is added to the Bookcase.");
                } else if (userChoice == 6) {           // Delete book using isbn parameter                
                    System.out.print("Please enter the isbn of the book you wish to delete: ");
                    isbn = inScan.next();
                    userBookcase.deleteBook(isbn);
                } else if (userChoice == 7) {
                    userBookcase.clearBookcase();
                }
            } 
        } while (userChoice != 8);      // Exit sentinel to break out of do while loop
        System.out.println("Thank you for using the Bookcase app. Have a nice day.");
    }
}
// *  Test output isn't very clear in terms of whether what is shown is intentional or represents error output; please be ultra clear on this, perhaps as I've shown on videos in the past.
// *  Why does my Write file have to exist before I write it?  That doesn't make sense.  
// *  Deleting a book yields no message telling me whether it did what I asked; each action should probably have confirmation or error information.
// *  Data type checking and range checking are done for the menu--good.  But they act differently; it would be ideal if they worked the same way.
//     -  BUT, range checking is NOT done on book additions, e.g., publication year allows -3 as valid.  You should have a REALLY SOLID in-range integer gathering method in your pocket by now, and should be using it EVERYWHERE you need integers from the user.
// *  Book:  nice work implementing Comparable interface AND a concise compareTo method.  Exactly right.
// *  Book:  no preconditions implemented; there really should be, esp. for clearly out-of-range data like days not 1-31, months not 1-12, etc.
// *  Book:  constructor has a default header; you didn't edit it to fill in YOUR stuff.
// *  JavaDoc:  notation looks good, otherwise.
// *  Style:  watch variable naming; ISBN should be isbn; acronyms don't override the style guidelines on naming.
// *  Bookcase:  searchISBN (should be searchIsbn, BTW) has a loop I really like; some folks did FOR and RETURNed in the middle which I DON'T like, but your way is perfect.
// *  Bookcase:  if searchISBN returned a position, rather than a book, it could be used as a helper function elsewhere here.
// *  Bookcase:  toString works too hard; you already have implemented toString in Book; why not rely on that instead of writing the same code AGAIN?
// *  Bookcase:  writeFile didn't use a FOR EACH loop, which I wanted you to get some experience with.  Please review that before the final.
// *  BookcaseUI:  main is too long; rule of thumb is two pages max.  Needs to be broken up a bit, e.g., pulling out adding a new book to a separate method.
// *  BookcaseUI:  variables all declared in one block seems right in a way, but in general you want to declare these near where you use them; some of them would be best left to Block scope.
// *  Good work overall!
// *  Later today (Sunday) check for a Moodle message from me; I'll send a wrap-up note talking about some common issues and some follow-up pointers you may want to review before the exam.