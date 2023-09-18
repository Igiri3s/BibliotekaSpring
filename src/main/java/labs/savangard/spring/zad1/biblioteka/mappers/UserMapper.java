package labs.savangard.spring.zad1.biblioteka.mappers;

import com.savangard.labs.rest.model.UserSchema.CreateUserReqest;
import com.savangard.labs.rest.model.UserSchema.GetUserResponse;
import labs.savangard.spring.zad1.biblioteka.models.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserMapper {

    public User mapFromCreateRequest(CreateUserReqest createUserReqest) {

        return new User(createUserReqest.getName(), createUserReqest.getSurname(), createUserReqest.getIsAdmin());
    }

    public GetUserResponse mapFromUserToGetResponse(User user) {

        GetUserResponse getUserRequest = new GetUserResponse();
        getUserRequest.setUserId(user.getUserId());
        getUserRequest.setName(user.getName());
        getUserRequest.setSurname(user.getSurname());
        getUserRequest.setIsAdmin(user.getIsAdmin());

        return getUserRequest;
    }
}
