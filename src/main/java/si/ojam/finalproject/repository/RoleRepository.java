package si.ojam.finalproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.ojam.finalproject.model.Role;
import si.ojam.finalproject.model.UserRoles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRoles name);
}