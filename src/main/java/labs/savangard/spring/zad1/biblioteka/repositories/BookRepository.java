package labs.savangard.spring.zad1.biblioteka.repositories;

import labs.savangard.spring.zad1.biblioteka.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
