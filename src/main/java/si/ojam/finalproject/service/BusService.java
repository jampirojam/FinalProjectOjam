package si.ojam.finalproject.service;

import si.ojam.finalproject.model.Bus;
import si.ojam.finalproject.payload.request.BusRequest;

public interface BusService {

	Bus addNewBus(BusRequest busRequest);

	Bus updatingBus(Long id, BusRequest busRequest);
}
