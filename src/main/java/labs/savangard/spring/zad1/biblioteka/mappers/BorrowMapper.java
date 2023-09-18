package labs.savangard.spring.zad1.biblioteka.mappers;


import com.savangard.labs.rest.model.BorrowSchema.BorrowDto;
import com.savangard.labs.rest.model.BorrowSchema.GetBorrowResponse;
import labs.savangard.spring.zad1.biblioteka.models.Borrow;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BorrowMapper {

    public GetBorrowResponse mapFromBorrowToGetResponse(Borrow borrow) {

        GetBorrowResponse getBorrowRequest = new GetBorrowResponse();
        getBorrowRequest.setBorrowId(borrow.getBorrowId());

        return getBorrowRequest;
    }

    public BorrowDto fromBorrowToBorrowDto(Borrow borrow){

        BorrowDto borrowDto = new BorrowDto();

        borrowDto.setBorrowId(borrow.getBorrowId());
        borrowDto.setBookId(borrow.getBook().getBookId());
        borrowDto.setUserId(borrow.getUser().getUserId());

        return borrowDto;

    }
}
