package labs.savangard.spring.zad1.biblioteka.controllers;

import com.savangard.labs.rest.model.BorrowSchema.BorrowDto;
import com.savangard.labs.rest.model.BorrowSchema.CreateBorrowReqest;
import com.savangard.labs.rest.model.BorrowSchema.GetBorrowsResponse;
import labs.savangard.spring.zad1.biblioteka.services.implementations.BorrowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("library/borrow")
public class BorrowController {

    private BorrowServiceImp borrowService;

    @Autowired
    public BorrowController(BorrowServiceImp borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/getAll")
    GetBorrowsResponse getAllBorrows() {
        return borrowService.getAllBorrows();

    }

    @GetMapping("/getAll/{id}")
    GetBorrowsResponse getAllBorrowsFromSpecificUser(@PathVariable("id") Integer id) {
        return borrowService.getAllBorrowsFromSpecificUser(id);


    }

    @PostMapping("/add")
    public ResponseEntity<String> createBorrow(@RequestBody CreateBorrowReqest borrow) {

        borrowService.borrowBook(borrow);
        return ResponseEntity.ok(": ksiazka o id: " + borrow.getBookId() + " zostala wyporzyczona");
    }

    @GetMapping("/find/{id}")
    BorrowDto getById(@PathVariable Integer id) {
        return borrowService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBorrow(@PathVariable Integer id) {
        if (id != null) {
            Optional<BorrowDto> foundedBorrow = Optional.ofNullable(borrowService.findById(id));
            if (foundedBorrow.isPresent()) {
                BorrowDto borrowToDelete = foundedBorrow.get();
                borrowService.endBorrowing(id);
                return ResponseEntity.ok("wyporzyczenie o id: " + borrowToDelete.getBorrowId() + " zostało usunięte");
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
