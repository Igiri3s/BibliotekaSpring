package labs.savangard.spring.zad1.biblioteka.services.implementations;

import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBookResponse;
import com.savangard.labs.rest.model.BookSchema.GetBooksResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.mappers.BookMapper;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import labs.savangard.spring.zad1.biblioteka.repositories.BookRepository;
import labs.savangard.spring.zad1.biblioteka.services.interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImp implements BookService {


    private BookRepository bookRepository;
    private BookMapper mapper;

    @Override
    public GetBookResponse addBook(CreateBookReqest createBookReqest) {
        Book newBook = mapper.mapFromCreateRequest(createBookReqest);
        bookRepository.save(newBook);
        return mapper.mapFromBookToGetResponse(newBook);
    }

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public GetBookResponse deleteBookById(Integer id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);
        if (bookToDelete.isPresent()) {
            GetBookResponse responseOfDeletedBook = mapper.mapFromBookToGetResponse(bookToDelete.get());
            bookRepository.deleteById(id);
            return responseOfDeletedBook;
        }
        return null;
    }

    @Override
    public GetBookResponse update(Integer id, UpdateBookReqest updateBookReqest) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book updatedBook = oldBook.get();
            updatedBook.setAuthor(updatedBook.getAuthor());
            updatedBook.setTitle(updateBookReqest.getTitle());
            updatedBook.setDescription(updateBookReqest.getDescription());
            updatedBook.setIsAvailable(updateBookReqest.getIsAvailable());
            bookRepository.save(updatedBook);

            return mapper.mapFromBookToGetResponse(updatedBook);
        }
        return null;
    }

    @Override
    public GetBooksResponse getAllBooks() {
        List<Book> listOfBooks = bookRepository.findAll();
        GetBooksResponse responseOfAllBook = new GetBooksResponse();
        for (Book book : listOfBooks) {
            GetBookResponse oneBookResponse = mapper.mapFromBookToGetResponse(book);
            responseOfAllBook.getBooks().add(oneBookResponse);
        }
        return responseOfAllBook;
    }

}
