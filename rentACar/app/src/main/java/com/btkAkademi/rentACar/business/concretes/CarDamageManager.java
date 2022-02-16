package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {
	// Dependencies
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	// Dependency Injection
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService, CarService carService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	// Adds a damage to car
	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		Result result = BusinessRules.run(
				checkIfCarIsExists(createCarDamageRequest.getCarId()));
		if (result != null) {
			return result;
		}
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		carDamage.setId(0);
		this.carDamageDao.save(carDamage);
		return new SuccessResult("Araba hasarı eklendi");
	}
	
	//Helpers 
	
	// Checks if car is exists
	private Result checkIfCarIsExists(int carId) {
		if (!carService.findCarById(carId).isSuccess()) {
			return new ErrorResult("Araba bulunamadı");
		} else
			return new SuccessResult();
	}

}
