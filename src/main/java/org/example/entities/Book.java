package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;
  private int publicationYear;
  private String genre;
  private String place;

  public Book(String title, String author, int publicationYear, String genre, String place) {
    this.title = title;
    this.author = author;
    this.publicationYear = publicationYear;
    this.genre = genre;
    this.place = place;
  }
}
