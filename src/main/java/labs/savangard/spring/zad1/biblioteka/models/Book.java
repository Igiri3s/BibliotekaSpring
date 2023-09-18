package labs.savangard.spring.zad1.biblioteka.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    private String title;
    private String author;
    private String description;
    private Boolean isAvailable;

    public Book(String title, String author, String description,Boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isAvailable = isAvailable;
    }
}
