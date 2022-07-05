package si.ojam.finalproject.service;

import java.text.ParseException;

import si.ojam.finalproject.model.Ticket;
import si.ojam.finalproject.payload.request.TicketRequest;

public interface TicketService {
	Ticket bookingTicket(TicketRequest ticketRequest) throws ParseException;

	Ticket updatingTicket(Long id, TicketRequest ticketRequest) throws ParseException;
}
