package si.ojam.finalproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import si.ojam.finalproject.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "SELECT * FROM ticket t INNER JOIN user u on t.user_id = u.id where u.username = :passenger", nativeQuery = true)
	List<Ticket> findByPassenger(String passenger);

}
