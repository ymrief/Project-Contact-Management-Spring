package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.RegisterUserRequest;
import belajarspring.restful.model.UpdateUserRequest;
import belajarspring.restful.model.UserResponse;

public interface UserService {

    void register(RegisterUserRequest registerUserRequest);

    UserResponse get(User user);

    UserResponse update(User user, UpdateUserRequest updateUserRequest);
}
