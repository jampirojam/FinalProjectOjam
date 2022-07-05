package si.ojam.finalproject.service;

import si.ojam.finalproject.model.Stop;
import si.ojam.finalproject.payload.request.StopRequest;

public interface StopService {

	Stop addNewStop(StopRequest stopReq);

	Stop updatingStop(Long id, StopRequest stopReq);
}
