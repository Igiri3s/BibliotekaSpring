package labs.savangard.spring.zad1.biblioteka.services.interfaces;


import com.savangard.labs.rest.model.BorrowSchema.*;

public interface BorrowService {

    GetBorrowResponse borrowBook(CreateBorrowReqest createBorrowReqest);

    GetBorrowResponse endBorrowing(Integer id);

    BorrowDto findById(Integer id);

    GetBorrowsResponse getAllBorrowsFromSpecificUser(Integer id);


}
