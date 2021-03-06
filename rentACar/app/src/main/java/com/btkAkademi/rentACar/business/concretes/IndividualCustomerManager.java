package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
    
    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        Result result= BusinessRules.run(checkIfEmailExists(createIndividualCustomerRequest.getEmail()),checkAge(createIndividualCustomerRequest.getBirthDate()));
        
        if(result != null) {
			return result;
		}
    	IndividualCustomer individualCustomer=this.modelMapperService.forRequest().map(createIndividualCustomerRequest,IndividualCustomer.class);
    	this.individualCustomerDao.save(individualCustomer);
    	return new SuccessResult("Bireysel Müşteri Eklendi");
    }


    private Result checkIfEmailExists(String email){
    	IndividualCustomer individualCustomer=individualCustomerDao.getIndividualCustomerByEmail(email);
        if(individualCustomer==null){
            return new SuccessResult();
        }
        return new ErrorResult("Tekrar eden email");
    }
    
    private Result checkAge(LocalDate date){
    	LocalDate now = LocalDate.now();
	    if(now.getYear()-date.getYear()<18){
	        return new ErrorResult("Yaş 18 den küçük olamaz");
	    }
	    return new SuccessResult();
    }
}
