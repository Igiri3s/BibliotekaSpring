package labs.savangard.spring.zad1.biblioteka.mappers;

import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBookResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BookMapper {

    public Book mapFromCreateRequest(CreateBookReqest createBookReqest){

        return new Book(createBookReqest.getTitle(),createBookReqest.getAuthor(),createBookReqest.getDescription(),createBookReqest.getIsAvailable());
    }

    public UpdateBookReqest mapFromBookToUpdateRequest(Book book){

        UpdateBookReqest updateBookReqest = new UpdateBookReqest();
        updateBookReqest.setTitle(book.getTitle());
        updateBookReqest.setAuthor(book.getAuthor());
        updateBookReqest.setDescription(book.getDescription());
        updateBookReqest.setIsAvailable(book.getIsAvailable());
        return updateBookReqest;
    }

    public GetBookResponse mapFromBookToGetResponse(Book book){

        GetBookResponse getBookRequest = new GetBookResponse();
        getBookRequest.setBookId(book.getBookId());
        getBookRequest.setTitle(book.getTitle());
        getBookRequest.setAuthor(book.getAuthor());
        getBookRequest.setDescription(book.getDescription());
        getBookRequest.setIsAvailable(book.getIsAvailable());
        return getBookRequest;
    }
}
