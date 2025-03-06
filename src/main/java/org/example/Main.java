package org.example;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.entities.Book;
import org.example.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class Main {
  private final BookService bookService;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void test() {

    Book book1 =
        bookService.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy", "Library"));
    Book book2 = bookService.addBook(new Book("Dune", "Frank Herbert", 2021, "Sci-Fi", "Office"));
    Book book3 =
        bookService.addBook(new Book("1984", "George Orwell", 1949, "Dystopian", "Bedroom"));
    Book book4 =
        bookService.addBook(
            new Book("Brave New World", "Aldous Huxley", 1932, "Dystopian", "Living Room"));
    Book book5 =
        bookService.addBook(new Book("Foundation", "Isaac Asimov", 1951, "Sci-Fi", "Office"));

    System.out.println("Books added successfully");

    List<Book> sciFiBooks = bookService.getBooksByGenre("Sci-Fi");
    System.out.println("\n Sci-Fi Books: " + sciFiBooks);

    System.out.println("\n Updating book location...");
    Book updatedBook = bookService.updateBookLocation(book2.getId(), "Home");
    System.out.println(
        "Updated Book location: "
            + updatedBook.getTitle()
            + ", new location: "
            + updatedBook.getPlace());

    long sciFiBooksCurrentYear = bookService.countCurrentYearBooksByGenre("Sci-Fi");
    System.out.println("\nBooks count for Sci-Fi in the current year: " + sciFiBooksCurrentYear);

    System.out.println("\nDeleting book...");
    bookService.deleteBook(book2.getId());

    System.out.println("Book deleted successfully: " + book3.getTitle());
  }
}
