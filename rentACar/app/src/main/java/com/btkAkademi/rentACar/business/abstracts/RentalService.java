package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	boolean checkIfCarIsInRental(int carId);
	DataResult<Rental> findRentalById(int id);
	DataResult<List<RentalListDto>> getAll(int pageNo, int pageSize);
	
}
