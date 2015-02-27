package com.excilys.cdb.controler.dtoMapper;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.validate.CheckValues;
import com.excilys.cdb.model.bean.Computer;

public class MapComputerDTO {


	public static ArrayList<Computer> DtoToModel(ArrayList<ComputerDTO> computerDtoL) {
		ArrayList<Computer> computerL = new ArrayList<Computer>();
		for (ComputerDTO computerDto : computerDtoL) {
			computerL.add(DtoToModel(computerDto));
		}
		return computerL;
	}

	public static ArrayList<ComputerDTO> ModelToDto(ArrayList<Computer> computerL) {
		ArrayList<ComputerDTO> computerDtoL = new ArrayList<ComputerDTO>();
		for (Computer computer : computerL) {
			computerDtoL.add(ModelToDto(computer));
		}
		return computerDtoL;
	}
	
	
	public static Computer DtoToModel(ComputerDTO computerDto) {
		Computer computer = new Computer();
		computer.setName(computerDto.getName());
		computer.setId(computerDto.getId());
		
		Timestamp introducedTS;
		try {
			introducedTS = CheckValues.stringToTimestamp(computerDto.getIntroduced());
		} catch (Exception e) {
			introducedTS = null;
		}
		computer.setIntroduced(introducedTS);
		
		Timestamp discontinuedTS;
		try {
			discontinuedTS = CheckValues.stringToTimestamp(computerDto.getDiscontinued());
		} catch (Exception e) {
			discontinuedTS = null;
		}
		computer.setDiscontinued(discontinuedTS);
		
		
		computer.setCompany(MapCompanyDTO.DtoToModel(computerDto.getCompanyDto()));
		
		
		return computer;
	}
	
	public static ComputerDTO ModelToDto(Computer computer) {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setName(computer.getName());
		computerDto.setId(computer.getId());
		computerDto.setIntroduced((computer.getIntroduced() != null) ? computer.getIntroduced().toString() : "");
		computerDto.setDiscontinued((computer.getDiscontinued() != null) ? computer.getDiscontinued().toString() : "");
		computerDto.setCompanyDto(MapCompanyDTO.ModelToDto(computer.getCompany()));
		
		return computerDto;
	}
}
