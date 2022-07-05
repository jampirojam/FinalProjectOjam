package si.ojam.finalproject.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import si.ojam.finalproject.model.Role;
import si.ojam.finalproject.model.User;
import si.ojam.finalproject.model.UserRoles;
import si.ojam.finalproject.payload.request.SignupRequest;
import si.ojam.finalproject.payload.request.UserRequest;
import si.ojam.finalproject.repository.RoleRepository;
import si.ojam.finalproject.repository.UserRepository;
import si.ojam.finalproject.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	public void checkIfUserAlreadyRegistered(SignupRequest SignupRequest) {

		if (userRepository.existsByUsername(SignupRequest.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
		}
		if (userRepository.existsByEmail(SignupRequest.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
		}
	}

	public Set<Role> handleUserRole(Set<String> strRoles) {

		Set<Role> roles = new HashSet<>();
		if (strRoles.isEmpty()) {
			Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		return roles;
	}

	@Override
	public User registerNewUser(SignupRequest SignupRequest) {

		checkIfUserAlreadyRegistered(SignupRequest);

		User user = new User(
				SignupRequest.getUsername(),
				SignupRequest.getEmail(),
				encoder.encode(SignupRequest.getPassword()),
				SignupRequest.getFirstName(),
				SignupRequest.getLastName(),
				SignupRequest.getMobileNumber());

		Set<String> strRoles = SignupRequest.getRole();
		Set<Role> roles = handleUserRole(strRoles);

		user.setRoles(roles);

		try {
			User savedUser = userRepository.save(user);
			return savedUser;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

	@Override
	public User updatingUser(UserRequest userRequest) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();

		Optional<User> user = userRepository.findByUsername(currentUser);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
		}

		user.get().setFirstName(userRequest.getFirstName());
		user.get().setLastName(userRequest.getLastName());
		user.get().setMobileNumber(userRequest.getMobileNumber());

		User updatedUser = userRepository.save(user.get());
		return updatedUser;
	}

}