package id.jampirojam.finalproject.service;

import id.jampirojam.finalproject.model.Stop;
import id.jampirojam.finalproject.payload.request.StopRequest;

public interface StopService {

	Stop addNewStop(StopRequest stopReq);

	Stop updatingStop(Long id, StopRequest stopReq);
}
