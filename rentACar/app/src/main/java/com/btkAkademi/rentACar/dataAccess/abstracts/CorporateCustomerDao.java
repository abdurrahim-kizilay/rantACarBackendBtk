package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer>{
	
	CorporateCustomer getCorporateCustomerByEmail(String email);
	CorporateCustomer getCorporateCustomerByCorporateName(String corporateName);
	
}
