package labs.savangard.spring.zad1.biblioteka.services;

import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBookResponse;
import com.savangard.labs.rest.model.BookSchema.GetBooksResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.mappers.BookMapper;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import labs.savangard.spring.zad1.biblioteka.repositories.BookRepository;
import labs.savangard.spring.zad1.biblioteka.services.implementations.BookServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImpTest {
    BookMapper mapper = new BookMapper();
    BookRepository testRepository = mock(BookRepository.class);
    BookServiceImp testBookService = new BookServiceImp(testRepository,mapper);

    @Test
    void addBook() {
        //given
        Book book = new Book(
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        );
        GetBookResponse exampleAddResponse = new GetBookResponse();
        exampleAddResponse.setAuthor("J.R.R. Tolkien");
        exampleAddResponse.setTitle("The Hobbit");
        exampleAddResponse.setDescription("A fantasy novel");
        exampleAddResponse.setIsAvailable(false);

        //when
        Mockito.when(testRepository.save(any()))
                .thenReturn(book);
        CreateBookReqest testCreateRequest =new CreateBookReqest();
        testCreateRequest.setIsAvailable(true);
        testCreateRequest.setTitle("The Hobbit");
        testCreateRequest.setDescription("A fantasy novel");
        testCreateRequest.setAuthor("J.R.R. Tolkien");
        GetBookResponse testAddResponse = testBookService.addBook(testCreateRequest);

        //then
        assertEquals("J.R.R. Tolkien",testAddResponse.getAuthor());
    }

    @Test
    void findBookById() {
        //given
        Optional<Book> randomBook = Optional.of(new Book(
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        ));
        //when
        Mockito.when(testRepository.findById(any()))
                .thenReturn(randomBook);
        Optional<Book> testBook = testBookService.findBookById(2);
        //then

        assertEquals("Brandon Sanderson",testBook.get().getAuthor());
    }

    @Test
    void deleteBookById() {
        //given
        Optional<Book> randomBook = Optional.of(new Book(
                1,
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        ));

        //when
        Mockito.when(testRepository.findById(any()))
                .thenReturn(randomBook);
        GetBookResponse responseOfDeletedBook = testBookService.deleteBookById(1);

        //then
        assertEquals("Brandon Sanderson",responseOfDeletedBook.getAuthor());
    }

    @Test
    void deleteBookByIdIsNull() {
        //given
        Optional<Book> randomBook = Optional.empty();
        //when

        Mockito.when(testRepository.findById(any()))
                .thenReturn(randomBook);
        GetBookResponse responseOfDeletedBook = testBookService.deleteBookById(1);

        //then
        assertEquals(null,responseOfDeletedBook);
    }

    @Test
    void update() {
        //given
        Optional<Book> randomBook = Optional.of(new Book(
                1,
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        ));
        UpdateBookReqest testUpdateRequest = new UpdateBookReqest();
        testUpdateRequest.setAuthor("J.R.R. Tolkien");
        testUpdateRequest.setTitle("The Hobbit");
        testUpdateRequest.setDescription("A fantasy novel");
        testUpdateRequest.setIsAvailable(true);
        //when
        Mockito.when(testRepository.findById(any()))
                .thenReturn(randomBook);
        GetBookResponse response = testBookService.update(1,testUpdateRequest);
        //then

        assertEquals("Brandon Sanderson",response.getAuthor());
    }
    @Test
    void updateIsNull() {
        //given
        Optional<Book> randomBook = Optional.empty();
        UpdateBookReqest testUpdateRequest = new UpdateBookReqest();
        testUpdateRequest.setAuthor("J.R.R. Tolkien");
        testUpdateRequest.setTitle("The Hobbit");
        testUpdateRequest.setDescription("A fantasy novel");
        testUpdateRequest.setIsAvailable(true);

        //when
        Mockito.when(testRepository.findById(any()))
                .thenReturn(randomBook);
        GetBookResponse response = testBookService.update(1,testUpdateRequest);

        //then
        assertEquals(null,response);

    }

    @Test
    void getAllBooks() {
        //given
        Book randomBook =new Book(
                1,
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        );
        Book randomBook2 = new Book(
                1,
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                "The first book in the 'Mistborn' series, set in a world with unique magic systems.",
                true
        );
        List<Book> testList = new ArrayList<>();
        testList.add(randomBook);
        testList.add(randomBook2);

        //when
        Mockito.when(testRepository.findAll())
                        .thenReturn(testList);
        GetBooksResponse returnedList = testBookService.getAllBooks();

        //then
        assertEquals(2,returnedList.getBooks().size());
    }
}