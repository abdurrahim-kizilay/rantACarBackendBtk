package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.CarMaintananceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintanaceRequests.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintananceDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintanance;

@Service
public class CarMaintananceManager implements CarMaintananceService{

	private CarMaintananceDao carMaintanaceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private CarService carService;
	
	@Autowired
	public CarMaintananceManager(CarMaintananceDao carMaintanaceDao, ModelMapperService modelMapperService,@Lazy RentalService rentalService,CarService carService) {
		super();
		this.carMaintanaceDao = carMaintanaceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService =rentalService;
		this.carService = carService;
	}

	@Override
	public DataResult<List<CarMaintananceListDto>> getAll() {
		List<CarMaintanance> carMaintanance = this.carMaintanaceDao.findAll();
		List<CarMaintananceListDto> response = carMaintanance.stream()
				.map(carMaintanace->modelMapperService.forDto()
				.map(carMaintanace, CarMaintananceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintananceListDto>>(response);
	}
	
	

	@Override
	public Result add(CreateCarMaintananceRequest createCarMaintanaceRequest) {
		Result result = BusinessRules.run(
				checkIfCarIsInRental(createCarMaintanaceRequest.getCarId()),
				checkIfCarIsExists(createCarMaintanaceRequest.getCarId()));
		if(result != null) {
			return result;
		}
		CarMaintanance carMaintanance = this.modelMapperService.forRequest().map(createCarMaintanaceRequest, CarMaintanance.class);
		carMaintanance.setId(0);
		this.carMaintanaceDao.save(carMaintanance);
		return new SuccessResult("Araba bak??ma eklendi");
	}
	
	private Result checkIfCarIsInRental(int carId) {
		
		if(this.rentalService.checkIfCarIsInRental(carId)) {
			return new ErrorResult("Araba kirada");
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIsExists(int carId) {
		if(!carService.findCarById(carId).isSuccess()) {
			return new ErrorResult("Araba mevcut de??il");
		}
		else return new SuccessResult();
	}
	
	
	public boolean checkIfCarIsInMaintanace(int carId) {
		if(this.carMaintanaceDao.findByCarIdAndMaintananceEndIsNull(carId)!=null) {
			return true;
		}
		return false;		
	}

}

//Arabalar bak??ma g??nderilebilir
//Bak??mda olan arabalar listelenebilmelidir
//Bak??mda olan araba kiralanamaz