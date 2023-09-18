package labs.savangard.spring.zad1.biblioteka.repositories;

import labs.savangard.spring.zad1.biblioteka.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
