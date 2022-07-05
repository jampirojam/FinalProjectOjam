package id.jampirojam.finalproject.service;

import org.springframework.stereotype.Component;

import id.jampirojam.finalproject.model.Trip;
import id.jampirojam.finalproject.payload.request.TripRequest;

@Component
public interface TripService {

	Trip addNewTrip(TripRequest tripRequest);
}
