package id.jampirojam.finalproject.service;

import java.text.ParseException;

import id.jampirojam.finalproject.model.Ticket;
import id.jampirojam.finalproject.payload.request.TicketRequest;

public interface TicketService {
	Ticket bookingTicket(TicketRequest ticketRequest) throws ParseException;

	Ticket updatingTicket(Long id, TicketRequest ticketRequest) throws ParseException;
}
