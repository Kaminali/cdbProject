package com.excilys.cdb.controler.dtoMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.model.bean.Computer;

public class MapComputerDTO {

	public static List<Computer> DtoToModel(
			List<ComputerDTO> computerDtoL) {
		List<Computer> computerL = new ArrayList<Computer>();
		for (ComputerDTO computerDto : computerDtoL) {
			computerL.add(DtoToModel(computerDto));
		}
		return computerL;
	}

	public static List<ComputerDTO> ModelToDto(
			List<Computer> computerL) {
		List<ComputerDTO> computerDtoL = new ArrayList<ComputerDTO>();
		for (Computer computer : computerL) {
			computerDtoL.add(ModelToDto(computer));
		}
		return computerDtoL;
	}

	public static Computer DtoToModel(ComputerDTO computerDto) {
		Computer computer = new Computer();
		computer.setName(computerDto.getName());
		computer.setId(computerDto.getId());

		try {
			computer.setIntroduced(LocalDate.parse(computerDto.getIntroduced()));
		} catch (Exception e) {
			computer.setIntroduced(null);
		}
		try {
			computer.setDiscontinued(LocalDate.parse(computerDto.getDiscontinued()));
		} catch (Exception e) {
			computer.setDiscontinued(null);
		}

		computer.setCompany(MapCompanyDTO.DtoToModel(computerDto
				.getCompanyDto()));

		return computer;
	}

	public static ComputerDTO ModelToDto(Computer computer) {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setName(computer.getName());
		computerDto.setId(computer.getId());
		computerDto.setIntroduced((computer.getIntroduced() != null) ? computer
				.getIntroduced().toString() : null);
		computerDto
				.setDiscontinued((computer.getDiscontinued() != null) ? computer
						.getDiscontinued().toString() : null);
		computerDto.setCompanyDto(MapCompanyDTO.ModelToDto(computer
				.getCompany()));

		return computerDto;
	}
}
