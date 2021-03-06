package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarMaintananceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintanaceRequests.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarMaintananceService {
	DataResult<List<CarMaintananceListDto>> getAll();
	Result add(CreateCarMaintananceRequest createCarMaintanaceRequest);
    boolean checkIfCarIsInMaintanace(int carId);
}
