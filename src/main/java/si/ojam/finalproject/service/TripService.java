package si.ojam.finalproject.service;

import org.springframework.stereotype.Component;

import si.ojam.finalproject.model.Trip;
import si.ojam.finalproject.payload.request.TripRequest;

@Component
public interface TripService {

	Trip addNewTrip(TripRequest tripRequest);
}
