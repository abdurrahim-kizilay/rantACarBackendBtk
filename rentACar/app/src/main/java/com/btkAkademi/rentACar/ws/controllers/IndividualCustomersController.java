package com.btkAkademi.rentACar.ws.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCusotmerService;

	public IndividualCustomersController(IndividualCustomerService individualCusotmerService) {
		super();
		this.individualCusotmerService = individualCusotmerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomer) {
		return this.individualCusotmerService.add(createIndividualCustomer);
	}
	
	

}
