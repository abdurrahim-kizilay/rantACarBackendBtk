package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintananceService carMaintanaceService;
	private CityService cityService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,CarMaintananceService carMaintanaceService,CityService cityService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintanaceService = carMaintanaceService;
		this.cityService = cityService;
	}

	@Override
	public DataResult<List<RentalListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);		
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}
	
	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
				checkKilometer(createRentalRequest.getRentedKlimeter(), createRentalRequest.getReturnedKilometer()),
				checkRentalDate(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()),
				checkIfCarIsInMaintanace(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId())		
			);
		
		if(result != null) {
			return result;
		}
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Kiralama Başarılı");
	}
	
	
	@Override
	public boolean checkIfCarIsInRental(int carId) {
		if(this.rentalDao.findByCarIdAndReturnDateIsNull(carId)!=null) {
			return true;
		}
		return false;
	}
	
	@Override
	public DataResult<Rental> findRentalById(int id) {
		if(rentalDao.existsById(id)) {
			return new SuccessDataResult<Rental>(rentalDao.getById(id));
		}
		else return new ErrorDataResult<Rental>();

	}
	

	private Result checkRentalDate(LocalDate rentDate, LocalDate returnDate) {
		if(rentDate.isAfter(returnDate)) {
			return new ErrorResult("Kiralama tarihi dönüş tarihinden önce olamaz");
		}
		return new SuccessResult();
	}
	
	private Result checkKilometer(int rentKilometer,int returnKilometer) {
		if(returnKilometer<rentKilometer) {
			return new ErrorResult("Kiralama km'si dönüş km'sinden yüksek olamaz");
		}
		return new SuccessResult();
	} 
	
	private Result checkIfCustomerIdExists(int id) {
		var customer = this.customerService.getCustomerById(id).getData();
		if(customer == null) {
			return new ErrorResult("Böyle bir kullanıcı yok");
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIsInMaintanace(int carId) {
		if(carMaintanaceService.checkIfCarIsInMaintanace(carId)) {
			return new ErrorResult("Araba bakımda");
		}
		return new SuccessResult();
	}

	private Result checkIfCityExist(int cityId) {
		if (!cityService.findCityById(cityId).isSuccess()) {
			return new ErrorResult("Şehir bulunamadı");
		}
		return new SuccessResult();
	}
	



	
	
}
