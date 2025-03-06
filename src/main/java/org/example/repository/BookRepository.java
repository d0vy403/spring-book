package org.example.repository;

import org.example.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByGenreIgnoreCase(String genre);

  @Query("SELECT COUNT(b) FROM Book b WHERE b.genre = :genre AND b.publicationYear = YEAR(CURRENT_DATE)")
  long countBooksByGenreAndCurrentYear(@Param("genre") String genre);
}
