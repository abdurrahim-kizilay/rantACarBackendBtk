package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService  modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporotateCustomerRequest) {
		Result result = BusinessRules.run(checkIfEmailExists(createCorporotateCustomerRequest.getEmail()),checkIfCorporateNameExists(createCorporotateCustomerRequest.getCorporateName()));
		if(result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporotateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult("Şirket müşterisi eklendi");
	}
	
	private Result checkIfEmailExists(String email) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getCorporateCustomerByEmail(email);
		if(corporateCustomer == null) {
			return new SuccessResult();
		}
		return new ErrorResult("Tekrar eden  email");	
	}

	private Result checkIfCorporateNameExists(String corporateName) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getCorporateCustomerByCorporateName(corporateName);
		if(corporateCustomer == null) {
			return new SuccessResult();
		}
		return new ErrorResult("Tekrar eden  şirket ismi");	
	}
}
