package com.excilys.cdb.controller.dtoMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;

public final class MapComputerDTO {
	public static List<Computer> DtoToModel(List<ComputerDTO> computerDtoL) {
		return computerDtoL.stream().map(b -> MapComputerDTO.DtoToModel(b)).collect(Collectors.toList());
	}

	public static List<ComputerDTO> ModelToDto(List<Computer> computerL, String lang) {
		return computerL.stream().map(b -> MapComputerDTO.ModelToDto(b, lang)).collect(Collectors.toList());
		
	}

	public static Computer DtoToModel(ComputerDTO computerDto) {
		Computer computer = new Computer();
		computer.setName(computerDto.getName());
		computer.setId(computerDto.getId());
		computer.setIntroduced((computerDto.getIntroduced() != null && computerDto.getIntroduced() != "") ? 
				LocalDate.parse(computerDto.getIntroduced()) : null);
		computer.setDiscontinued((computerDto.getDiscontinued() != null && computerDto.getDiscontinued() != "") ? 
				LocalDate.parse(computerDto.getDiscontinued()) : null);
		if (computerDto.getCompanyId() != null) {
			if (computerDto.getCompanyId() > -1) {
				Company company = new Company();
				company.setId(computerDto.getCompanyId());
				company.setName(computerDto.getCompanyName());
				computer.setCompany(company);
			}
		}
		return computer;
	}
	
	public static ComputerDTO ModelToDto(Computer computer, String lang) {
		String dateInt;
		String dateDisc;
		if (lang != null && lang != "null") {
			Locale locale = new Locale(lang);
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.append(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
					.toFormatter(locale);
			dateInt = (computer.getIntroduced() != null) ? computer
					.getIntroduced().format(formatter) : null;
			dateDisc = (computer.getDiscontinued() != null) ? computer
					.getDiscontinued().format(formatter) : null;
		} else {
			dateInt = (computer.getIntroduced() != null) ? computer
					.getIntroduced().toString() : null;
			dateDisc = (computer.getDiscontinued() != null) ? computer
					.getDiscontinued().toString() : null;
		}
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setName(computer.getName());
		computerDto.setId(computer.getId());
		computerDto.setIntroduced(dateInt);
		computerDto.setDiscontinued(dateDisc);
		computerDto.setCompanyName((computer.getCompany() != null) ? computer
				.getCompany().getName() : null);
		computerDto.setCompanyId((computer.getCompany() != null) ? computer
				.getCompany().getId() : -1);
		return computerDto;
	}
}