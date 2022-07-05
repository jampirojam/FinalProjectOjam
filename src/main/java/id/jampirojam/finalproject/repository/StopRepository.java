package id.jampirojam.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.jampirojam.finalproject.model.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {

	@Query(value = "SELECT * FROM tb_stop t WHERE LOWER( t.name ) LIKE  %:name%", nativeQuery = true)
	List<Stop> findByName(String name);

	List<Stop> findByCode(String code);

	Optional<Stop> findById(int sourceStopId);
}