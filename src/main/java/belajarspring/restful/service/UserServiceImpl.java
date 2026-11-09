package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.RegisterUserRequest;
import belajarspring.restful.model.UpdateUserRequest;
import belajarspring.restful.model.UserResponse;
import belajarspring.restful.repository.UserRepository;
import belajarspring.restful.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public void register(RegisterUserRequest registerUserRequest) {
        validationService.validate(registerUserRequest);

        if (userRepository.existsById(registerUserRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(BCrypt.hashpw(registerUserRequest.getPassword(), BCrypt.gensalt()));
        user.setName(registerUserRequest.getName());

        userRepository.save(user);
    }

    @Override
    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Override
    @Transactional
    public UserResponse update(User user, UpdateUserRequest updateUserRequest) {
        validationService.validate(updateUserRequest);

        if (Objects.nonNull(updateUserRequest.getName())) {
            user.setName(updateUserRequest.getName());
        }

        if (Objects.nonNull(updateUserRequest.getPassword())) {
            user.setPassword(BCrypt.hashpw(updateUserRequest.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}
