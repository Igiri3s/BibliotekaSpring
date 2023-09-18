package labs.savangard.spring.zad1.biblioteka.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;

    String name;
    String surname;
    Boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;

    public User(String name, String surname, Boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
    }
}
