package id.jampirojam.finalproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.jampirojam.finalproject.model.Role;
import id.jampirojam.finalproject.model.UserRoles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRoles name);
}