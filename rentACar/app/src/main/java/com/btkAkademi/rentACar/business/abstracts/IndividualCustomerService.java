package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}

