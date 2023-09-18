package labs.savangard.spring.zad1.biblioteka.services.interfaces;

import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBookResponse;
import com.savangard.labs.rest.model.BookSchema.GetBooksResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.models.Book;

import java.util.Optional;

public interface BookService {
    GetBookResponse addBook(CreateBookReqest createBookReqest);

    GetBookResponse update(Integer id, UpdateBookReqest updateBookReqest);

    GetBookResponse deleteBookById(Integer id);

    Optional<Book> findBookById(Integer id);

    GetBooksResponse getAllBooks();

}
