package labs.savangard.spring.zad1.biblioteka.services.implementations;

import com.savangard.labs.rest.model.UserSchema.CreateUserReqest;
import com.savangard.labs.rest.model.UserSchema.GetUserResponse;
import labs.savangard.spring.zad1.biblioteka.mappers.UserMapper;
import labs.savangard.spring.zad1.biblioteka.models.User;
import labs.savangard.spring.zad1.biblioteka.repositories.UserRepository;
import labs.savangard.spring.zad1.biblioteka.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
