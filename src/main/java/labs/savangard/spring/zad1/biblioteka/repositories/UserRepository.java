package labs.savangard.spring.zad1.biblioteka.repositories;

import labs.savangard.spring.zad1.biblioteka.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
}
