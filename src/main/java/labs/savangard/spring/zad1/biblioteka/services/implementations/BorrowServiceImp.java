package labs.savangard.spring.zad1.biblioteka.services.implementations;

import com.savangard.labs.rest.model.BorrowSchema.*;
import jakarta.annotation.PostConstruct;
import labs.savangard.spring.zad1.biblioteka.mappers.BorrowMapper;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import labs.savangard.spring.zad1.biblioteka.models.Borrow;
import labs.savangard.spring.zad1.biblioteka.models.User;
import labs.savangard.spring.zad1.biblioteka.repositories.BookRepository;
import labs.savangard.spring.zad1.biblioteka.repositories.BorrowRepository;
import labs.savangard.spring.zad1.biblioteka.repositories.UserRepository;
import labs.savangard.spring.zad1.biblioteka.services.interfaces.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowServiceImp implements BorrowService {

    BorrowRepository borrowRepository;
    UserRepository userRepository;
    BookRepository bookRepository;
    BorrowMapper mapper;

    @Override
    public GetBorrowResponse borrowBook(CreateBorrowReqest createBorrowReqest) {
        Borrow borrow = new Borrow();
        Optional<User> optionalUser = userRepository.findById(createBorrowReqest.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(createBorrowReqest.getBookId());

        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            Book book = optionalBook.get();

            borrow.setBook(book);
            borrow.setUser(user);

            borrowRepository.save(borrow);

            return mapper.mapFromBorrowToGetResponse(borrow);
        }
        return null;

    }

    @Override
    public GetBorrowResponse endBorrowing(Integer id) {
        Optional<Borrow> borrowToUnBorrow = borrowRepository.findById(id);
        if (borrowToUnBorrow.isPresent()) {
            borrowRepository.deleteById(borrowToUnBorrow.get().getBorrowId());
            return mapper.mapFromBorrowToGetResponse(borrowToUnBorrow.get());
        }
        return null;
    }
    @Override
    public BorrowDto findById(Integer id) {
        Optional<Borrow> foundedBorrow = borrowRepository.findById(id);
        if (foundedBorrow.isPresent()) {
            Borrow borrow = foundedBorrow.get();

            return mapper.fromBorrowToBorrowDto(borrow);
        }
        return null;
    }

    public GetBorrowsResponse getAllBorrows() {

        List<Borrow> listOfBorrows = borrowRepository.findAll();
        GetBorrowsResponse responseOfAllBorrows = new GetBorrowsResponse();
        for (Borrow borrow : listOfBorrows) {
            BorrowDto oneBorrowResponse = mapper.fromBorrowToBorrowDto(borrow);
            responseOfAllBorrows.getBorrows().add(oneBorrowResponse);
        }
        return responseOfAllBorrows;
    }

    @Override
    public GetBorrowsResponse getAllBorrowsFromSpecificUser(Integer id) {

        List<Borrow> listOfBorrows = borrowRepository.findAll();
        GetBorrowsResponse responseOfAllBorrows = new GetBorrowsResponse();
        for (Borrow borrow : listOfBorrows) {
            if (Objects.equals(borrow.getUser().getUserId(), id)) {
                BorrowDto oneBorrowResponse = mapper.fromBorrowToBorrowDto(borrow);

                responseOfAllBorrows.getBorrows().add(oneBorrowResponse);
            }
        }
        return responseOfAllBorrows;

    }

    //TODO przydala by sie jakas walidaca, np zeby sie nie dalo wyporzyczyc 2 razy tej samej ksiazi ITD

    //TODO jakas forma logowania, spring security cyz inne gowno

        /*TODO zrob testy
        tego nie trzeba tlumaczyc, ale trzeba sprawdzic pozostale mappery i servisy
         */
        /*TODO zrób frontend
        trzeba zrobic frontend moszesz się meczyc z thymeLeaf albo sprawdzic sobie Bootstrapa (kacper poleca)
         */

    @PostConstruct
    private void addExampleDataToDatabase() {
        //add users
        User user1 = new User(
                "John",
                "Doe",
                true
        );

        User user2 = new User(
                "Jane",
                "Smith",
                false
        );

        User user3 = new User(
                "Alice",
                "Johnson",
                false
        );

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        userRepository.saveAll(users);


        //add Books
        Book book1 = new Book(
                "The Hobbit",
                "J.R.R. Tolkien",
                "A fantasy novel about the journey of Bilbo Baggins in the world of Middle-earth.",
                true
        );

        Book book2 = new Book(
                "Harry Potter and the Sorcerer's Stone",
                "J.K. Rowling",
                "The first book in the popular series following the adventures of a young wizard.",
                true
        );

        Book book3 = new Book(
                "A Game of Thrones",
                "George R.R. Martin",
                "The first book in the 'A Song of Ice and Fire' series, known for its political intrigue and fantasy elements.",
                false
        );

        Book book4 = new Book(
                "The Name of the Wind",
                "Patrick Rothfuss",
                "The first book in the 'Kingkiller Chronicle' series, telling the story of Kvothe's life.",
                true
        );

        Book book5 = new Book(
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        );

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        //add borrow
        borrowRepository.save(new Borrow(1,user1, book1));
        borrowRepository.save(new Borrow(2,user1, book4));
        borrowRepository.save(new Borrow(3,user2, book4));

    }
}
