package com.btkAkademi.rentACar.business.requests.carMaintanaceRequests;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintananceRequest {
	private int id;
	private LocalDate maintananceStart;
	private LocalDate maintananceEnd;
	private int carId;
}
