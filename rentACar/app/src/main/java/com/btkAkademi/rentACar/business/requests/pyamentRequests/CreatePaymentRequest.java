package com.btkAkademi.rentACar.business.requests.pyamentRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	 private LocalDate paymentDate;
	 private Integer rentalId;
}
