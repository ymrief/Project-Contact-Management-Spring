package belajarspring.restful.service;

import belajarspring.restful.model.LoginUserRequest;
import belajarspring.restful.model.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginUserRequest loginUserRequest);
}
