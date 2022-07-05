package si.ojam.finalproject.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import si.ojam.finalproject.model.Ticket;
import si.ojam.finalproject.model.TripSchedule;
import si.ojam.finalproject.model.User;
import si.ojam.finalproject.payload.request.TicketRequest;
import si.ojam.finalproject.repository.TicketRepository;
import si.ojam.finalproject.repository.TripScheduleRepository;
import si.ojam.finalproject.repository.UserRepository;
import si.ojam.finalproject.service.TicketService;

@AllArgsConstructor
@Component
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TripScheduleRepository tripScheduleRepository;

	public Optional<User> checkIfUserPresent(String currentUser) {

		Optional<User> user = userRepository.findByUsername(currentUser);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
		}
		return user;
	}

	public Optional<TripSchedule> checkIfTripScheduleAvailable(TicketRequest ticketRequest) throws ParseException {

		Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId());

		String journeyDate = ticketRequest.getJourneyDate();
		String requestedDate = tripSchedule.get().getTripDate();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = sdf.parse(requestedDate);
		Date tripDate = sdf.parse(journeyDate);

		if (!tripSchedule.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip schedule not found");
		}
		if (!myDate.equals(tripDate)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trip found at date " + journeyDate);
		}
		if (tripSchedule.get().getAvailableSeats() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticked sold out");
		}
		return tripSchedule;
	}

	@Override
	public Ticket bookingTicket(TicketRequest ticketRequest) throws ParseException {

		// get logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();

		Optional<User> user = checkIfUserPresent(currentUser);
		Optional<TripSchedule> tripSchedule = checkIfTripScheduleAvailable(ticketRequest);
		
		try {
			Ticket ticket = new Ticket()
					.setSeatNumber(tripSchedule.get().getTripDetail().getBus().getCapacity() - tripSchedule.get().getAvailableSeats())
					.setCancellable(false)
					.setJourneyDate(ticketRequest.getJourneyDate())
					.setPassenger(user.get())
					.setTripSchedule(tripSchedule.get());

			ticketRepository.save(ticket);

			tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1);

			tripScheduleRepository.save(tripSchedule.get());

			return ticket;
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

	@Override
	public Ticket updatingTicket(Long id, TicketRequest ticketRequest) throws ParseException {

		Optional<Ticket> ticket = ticketRepository.findById(id);
		
		if (!ticket.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket not found");
		}

		Optional<TripSchedule> tripSchedule = checkIfTripScheduleAvailable(ticketRequest);
		
		ticket.get().setJourneyDate(ticketRequest.getJourneyDate());
		ticket.get().setTripSchedule(tripSchedule.get());

		Ticket savedTicket = ticketRepository.save(ticket.get());

		return savedTicket;
	}

}
