package com.btkAkademi.rentACar.business.concretes;

import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.pyamentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class PaymentManager implements PaymentService{
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}
	
	
	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {	
		
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		int rentalId= createPaymentRequest.getRentalId();
		
		Rental rental = rentalService.findRentalById(rentalId).getData();
	
		payment.setTotalAmount(totalPriceCalculator(rental));
		
		payment.setId(0);
				
		
		this.paymentDao.save(payment);
		
		return new SuccessResult("Payment eklendi");
	}
	
	private double totalPriceCalculator(Rental rental) {
		
		double totalPrice = 0.0;
		
		int days = Period.between(rental.getReturnDate(),rental.getRentDate()).getDays();
		
		if(days==0) days=1;
		
		totalPrice+=days*rental.getCar().getDailyPrice();
		 
		for(AdditionalService additionalService:rental.getAddtionalServices()) {
			
			totalPrice+=additionalService.getPrice();
			
		}
		
		return totalPrice;
	}

	
}
