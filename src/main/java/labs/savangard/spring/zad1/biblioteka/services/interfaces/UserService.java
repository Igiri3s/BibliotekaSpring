package labs.savangard.spring.zad1.biblioteka.services.interfaces;

import com.savangard.labs.rest.model.UserSchema.CreateUserReqest;
import com.savangard.labs.rest.model.UserSchema.GetUserResponse;
import com.savangard.labs.rest.model.UserSchema.GetUsersResponse;

public interface UserService {
    GetUserResponse createUserRequest(CreateUserReqest createuserReqest);

    GetUsersResponse getAllUsers();
}
