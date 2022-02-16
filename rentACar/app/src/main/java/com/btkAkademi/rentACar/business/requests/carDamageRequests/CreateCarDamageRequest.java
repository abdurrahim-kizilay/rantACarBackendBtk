package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.carMaintanaceRequests.CreateCarMaintananceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	
	int id;
	String description;
	int carId;
	
}
