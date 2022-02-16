package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@GetMapping("getAll")
	public DataResult<List<RentalListDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
		return rentalService.getAll(pageNo, pageSize);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest ) {
		return this.rentalService.add(createRentalRequest);
	}
}
