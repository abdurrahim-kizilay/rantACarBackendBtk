package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int returnedKilometer;
	private int rentedKlimeter;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
}
