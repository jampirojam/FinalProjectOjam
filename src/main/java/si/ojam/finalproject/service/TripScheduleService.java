package si.ojam.finalproject.service;

import java.text.ParseException;

import si.ojam.finalproject.model.TripSchedule;
import si.ojam.finalproject.payload.request.TripScheduleRequest;

public interface TripScheduleService {
	TripSchedule addNewTrip(TripScheduleRequest tripScheduleRequest) throws ParseException;

	TripSchedule updatingTrip(Long id, TripScheduleRequest tripScheduleRequest) throws ParseException;
}
