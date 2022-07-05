package id.jampirojam.finalproject.service;

import id.jampirojam.finalproject.model.User;
import id.jampirojam.finalproject.payload.request.SignupRequest;
import id.jampirojam.finalproject.payload.request.UserRequest;

public interface UserService {

	User registerNewUser(SignupRequest SignupRequest);

	User updatingUser(UserRequest userRequest);
}
