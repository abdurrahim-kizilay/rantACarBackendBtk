package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Color;

public interface ColorService {
	DataResult<List<ColorListDto>> getAll();
	Result add(CreateColorRequest createColorRequest);
    Result update(UpdateColorRequest updateColorRequest);
	
	DataResult<Color> getById(int id);
}
