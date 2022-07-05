package id.jampirojam.finalproject.service;

import id.jampirojam.finalproject.model.Agency;
import id.jampirojam.finalproject.payload.request.AgencyRequest;

public interface AgencyService {

	Agency updatingAgency(Long id, AgencyRequest agencyDetail);

	Agency addNewAgency(AgencyRequest agencyRequest);

}
