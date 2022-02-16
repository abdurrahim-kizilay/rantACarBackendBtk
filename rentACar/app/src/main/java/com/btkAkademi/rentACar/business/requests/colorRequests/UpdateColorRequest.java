package com.btkAkademi.rentACar.business.requests.colorRequests;

import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	private int id;
	private String name;
}
