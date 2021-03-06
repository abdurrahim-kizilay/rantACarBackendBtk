package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintananceListDto {
	private int id;
	private LocalDate maintananceStart;
	private LocalDate maintananceEnd;
}
