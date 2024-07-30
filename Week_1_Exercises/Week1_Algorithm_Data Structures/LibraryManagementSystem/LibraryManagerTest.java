package LibraryManagementSystem;
import java.util.Arrays;
public class LibraryManagerTest {
    public static void main(String[] args) {
        // Create sample books
        Book[] books = {
            new Book(1, "Angel of the Dark", "Sidney Sheldon"),
            new Book(2, "The Love Hypothesis", "Ana Huang"),
            new Book(3, "", "George Orwell")
        };

        LibraryManager manager = new LibraryManager(books);

        // Test Linear Search
        System.out.println("Linear Search Result:");
        Book resultLinear = manager.searchBookByTitleLinear("1984");
        System.out.println(resultLinear);

        // Sort books by title for binary search
        manager.sortBooksByTitle();

        // Test Binary Search
        System.out.println("\nBinary Search Result:");
        Book resultBinary = manager.searchBookByTitleBinary("The Great Gatsby");
        System.out.println(resultBinary);
    }

}
