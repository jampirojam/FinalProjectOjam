package id.jampirojam.finalproject.service;

import java.text.ParseException;

import id.jampirojam.finalproject.model.TripSchedule;
import id.jampirojam.finalproject.payload.request.TripScheduleRequest;

public interface TripScheduleService {
	TripSchedule addNewTrip(TripScheduleRequest tripScheduleRequest) throws ParseException;

	TripSchedule updatingTrip(Long id, TripScheduleRequest tripScheduleRequest) throws ParseException;
}
