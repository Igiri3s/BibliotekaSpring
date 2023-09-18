package labs.savangard.spring.zad1.biblioteka.mappers;


import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBookResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    private BookMapper testMapper = new BookMapper();

    @Test
    void mapFromCreateRequest() {
        //given
        CreateBookReqest testCreateRequest = new CreateBookReqest();
        testCreateRequest.setAuthor("julia");
        testCreateRequest.setDescription("miala");
        testCreateRequest.setTitle("kota");
        testCreateRequest.setIsAvailable(true);
        //when
        Book testBook = testMapper.mapFromCreateRequest(testCreateRequest);

        //then
        assertEquals("julia",testBook.getAuthor());
        assertEquals("miala",testBook.getDescription());
        assertEquals("kota",testBook.getTitle());
        assertTrue(testBook.getIsAvailable());
    }

    @Test
    void mapFromBookToUpdateRequest() {
        //given
        Book book = new Book(
                "The Hobbit",
                "J.R.R. Tolkien",
                "A fantasy novel",
                true
        );
        //when
        UpdateBookReqest testUpdateRequest = testMapper.mapFromBookToUpdateRequest(book);
        //then
        assertEquals("The Hobbit",testUpdateRequest.getTitle());
        assertEquals("J.R.R. Tolkien",testUpdateRequest.getAuthor());
        assertEquals("A fantasy novel",testUpdateRequest.getDescription());
        assertTrue(testUpdateRequest.getIsAvailable());

    }

    @Test
    void mapFromBookToGetResponse() {
        //given
        Book book = new Book(
                1,
                "The Hobbit",
                "J.R.R. Tolkien",
                "A fantasy novel",
                true
        );
        //when
        GetBookResponse testGetResposne = testMapper.mapFromBookToGetResponse(book);
        //then
        assertEquals(1,testGetResposne.getBookId());
        assertEquals("The Hobbit",testGetResposne.getTitle());
        assertEquals("J.R.R. Tolkien",testGetResposne.getAuthor());
        assertEquals("A fantasy novel",testGetResposne.getDescription());
        assertTrue(testGetResposne.getIsAvailable());
    }
}