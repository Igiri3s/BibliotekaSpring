package labs.savangard.spring.zad1.biblioteka.services.implementations;

import com.savangard.labs.rest.model.UserSchema.CreateUserReqest;
import com.savangard.labs.rest.model.UserSchema.GetUserResponse;
import com.savangard.labs.rest.model.UserSchema.GetUsersResponse;
import com.savangard.labs.rest.model.UserSchema.UserDto;
import labs.savangard.spring.zad1.biblioteka.mappers.UserMapper;
import labs.savangard.spring.zad1.biblioteka.models.User;
import labs.savangard.spring.zad1.biblioteka.repositories.UserRepository;
import labs.savangard.spring.zad1.biblioteka.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {


    private UserRepository userRepository;
    private UserMapper mapper;

    @Override
    public GetUserResponse createUserRequest(CreateUserReqest createuserReqest) {
        User user = new User(createuserReqest.getName(), createuserReqest.getSurname(), createuserReqest.getIsAdmin());
        userRepository.save(user);
        return mapper.mapFromUserToGetResponse(user);
    }

    @Override
    public GetUsersResponse getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        GetUsersResponse responseOfAllUsers = new GetUsersResponse();
        for (User user : listOfUsers) {
            UserDto oneUserResponse = mapper.fromUserToUserDto(user);
            responseOfAllUsers.getUsers().add(oneUserResponse);
        }
        return responseOfAllUsers;
    }
}
