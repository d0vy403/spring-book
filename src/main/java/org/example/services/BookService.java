package org.example.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.entities.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {
  private final BookRepository bookRepository;
  

  public Book addBook(Book book) {
    return bookRepository.saveAndFlush(book);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

  public List<Book> getBooksByGenre(String genre) {
    return bookRepository.findByGenreIgnoreCase(genre);
  }

  public Book updateBookLocation(Long id, String newPlace) {
    return bookRepository
        .findById(id)
        .map(
            book -> {
              book.setPlace(newPlace);
              return bookRepository.save(book);
            })
        .orElseThrow(() -> new RuntimeException("Book not found"));
  }

  public long countCurrentYearBooksByGenre(String genre) {
    return bookRepository.countBooksByGenreAndCurrentYear(genre);
  }
}
