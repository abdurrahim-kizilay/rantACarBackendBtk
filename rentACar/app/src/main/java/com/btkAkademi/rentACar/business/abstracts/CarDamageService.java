package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarDamageService {
	Result add(CreateCarDamageRequest createCarDamageRequest);

}
