package com.btkAkademi.rentACar.dataAccess.abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import com.btkAkademi.rentACar.entities.concretes.Car;


public interface CarDao extends JpaRepository<Car,Integer> {
	Car getById(int id);

}
