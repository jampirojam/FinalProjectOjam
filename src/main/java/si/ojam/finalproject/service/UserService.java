package si.ojam.finalproject.service;

import si.ojam.finalproject.model.User;
import si.ojam.finalproject.payload.request.SignupRequest;
import si.ojam.finalproject.payload.request.UserRequest;

public interface UserService {

	User registerNewUser(SignupRequest SignupRequest);

	User updatingUser(UserRequest userRequest);
}
