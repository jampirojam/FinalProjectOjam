package si.ojam.finalproject.service;

import si.ojam.finalproject.model.Agency;
import si.ojam.finalproject.payload.request.AgencyRequest;

public interface AgencyService {

	Agency updatingAgency(Long id, AgencyRequest agencyDetail);

	Agency addNewAgency(AgencyRequest agencyRequest);

}
