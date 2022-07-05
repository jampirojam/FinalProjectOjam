package id.jampirojam.finalproject.service;

import id.jampirojam.finalproject.model.Bus;
import id.jampirojam.finalproject.payload.request.BusRequest;

public interface BusService {

	Bus addNewBus(BusRequest busRequest);

	Bus updatingBus(Long id, BusRequest busRequest);
}
