package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	private String email;
    private String password;
    private String corporateName;
    private String taxNumber;
}
