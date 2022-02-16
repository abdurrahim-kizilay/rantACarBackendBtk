package com.btkAkademi.rentACar.ws.controllers;

import com.btkAkademi.rentACar.business.dtos.CarMaintananceListDto;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.requests.carMaintanaceRequests.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carMaintanaces")
public class CarMaintanancesController {
	
	private CarMaintananceService carMaintananceService;

	public CarMaintanancesController(CarMaintananceService carMaintananceService) {
		super();
		this.carMaintananceService = carMaintananceService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CarMaintananceListDto>> getall() {
		return this.carMaintananceService.getAll();
	}

	
	@PostMapping("add")
	public Result add(@RequestBody CreateCarMaintananceRequest createCarMaintanaceRequest) {
		return this.carMaintananceService.add(createCarMaintanaceRequest);
	}

}
