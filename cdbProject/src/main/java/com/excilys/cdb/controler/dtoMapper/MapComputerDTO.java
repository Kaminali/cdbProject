package com.excilys.cdb.controler.dtoMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.model.bean.Computer;

public final class MapComputerDTO {

	public static List<Computer> DtoToModel(
			List<ComputerDTO> computerDtoL) {
		List<Computer> computerL = new ArrayList<Computer>();
		for (ComputerDTO computerDto : computerDtoL) {
			computerL.add(DtoToModel(computerDto));
		}
		return computerL;
	}

	public static List<ComputerDTO> ModelToDto(List<Computer> computerL, Locale locale) {
		List<ComputerDTO> computerDtoL = new ArrayList<ComputerDTO>();
		for (Computer computer : computerL) {
			computerDtoL.add(ModelToDto(computer, locale));
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
			//computer.setIntroduced(null);
		}
		try {
			computer.setDiscontinued(LocalDate.parse(computerDto.getDiscontinued()));
		} catch (Exception e) {
			//computer.setDiscontinued(null);
		}

		computer.setCompany(MapCompanyDTO.DtoToModel(computerDto
				.getCompanyDto()));

		return computer;
	}

	public static ComputerDTO ModelToDto(Computer computer, Locale locale) {

		String dateInt;
		String dateDisc;
		
		if(locale != null) {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(
	                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
	                .toFormatter(locale);
			dateInt = (computer.getIntroduced() != null) ? computer.getIntroduced().format(formatter) : null;
			dateDisc = (computer.getDiscontinued() != null) ? computer.getDiscontinued().format(formatter) : null;
		}
		else {
			dateInt = (computer.getIntroduced() != null) ? computer.getIntroduced().toString() : null;
			dateDisc = (computer.getDiscontinued() != null) ? computer.getDiscontinued().toString() : null;
		}
		
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setName(computer.getName());
		computerDto.setId(computer.getId());
		computerDto.setIntroduced(dateInt);
		computerDto.setDiscontinued(dateDisc);
		computerDto.setCompanyDto(MapCompanyDTO.ModelToDto(computer.getCompany()));

		return computerDto;
	}
}
