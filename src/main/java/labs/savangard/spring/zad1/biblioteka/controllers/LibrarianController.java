package labs.savangard.spring.zad1.biblioteka.controllers;

import com.savangard.labs.rest.model.BookSchema.CreateBookReqest;
import com.savangard.labs.rest.model.BookSchema.GetBooksResponse;
import com.savangard.labs.rest.model.BookSchema.UpdateBookReqest;
import labs.savangard.spring.zad1.biblioteka.models.Book;
import labs.savangard.spring.zad1.biblioteka.services.implementations.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("library/librarian")
public class LibrarianController {

    @Autowired
    private BookServiceImp bookService;

    @GetMapping("/getAll")
    GetBooksResponse getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody CreateBookReqest book) {

        bookService.addBook(book);
        return ResponseEntity.ok(book.getTitle() + " został dodany");
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Book> getById(@PathVariable Integer id) {
        return ResponseEntity.of(bookService.findBookById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBookById(@PathVariable Integer id, @RequestBody UpdateBookReqest updateBookReqest) {
        if (id != null) {
            bookService.update(id, updateBookReqest);
            return ResponseEntity.ok(updateBookReqest.getTitle() + " został updatowany");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Integer id) {
        if (id != null) {
            Optional<Book> book = bookService.findBookById(id);
            if (book.isPresent()) {
                bookService.deleteBookById(id);
                return ResponseEntity.ok(book.get().getTitle() + " został usunięty");
            } else {
                return ResponseEntity.noContent().build();
            }

        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
