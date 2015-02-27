/**
 * 
 */
package com.excilys.cdb.controler.dtoMapper;

import java.util.ArrayList;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

/**
 * @author excilys
 *
 */
public class MapCompanyDTO {

	public static ArrayList<Company> DtoToModel(ArrayList<CompanyDTO> companyDtoL) {
		ArrayList<Company> companyL = new ArrayList<Company>();
		for (CompanyDTO companyDto : companyDtoL) {
			companyL.add(DtoToModel(companyDto));
		}
		return companyL;
	}

	public static ArrayList<CompanyDTO> ModelToDto(ArrayList<Company> companyL) {
		ArrayList<CompanyDTO> companyDtoL = new ArrayList<CompanyDTO>();
		for (Company company : companyL) {
			companyDtoL.add(ModelToDto(company));
		}
		return companyDtoL;
	}

	public static Company DtoToModel(CompanyDTO companyDto) {
		Company company = new Company();
		company.setId(companyDto.getId());
		if (companyDto.getId() != null && companyDto.getId() != -1 && (companyDto.getName() == null || companyDto.getName() == "")) {
			CompanyDAO companyDAO = new CompanyDAO(ConnectionManager.getInstance());
			company = companyDAO.getById(companyDto.getId());
		} else {
			company.setName(companyDto.getName());
		}
		return company;
	}

	public static CompanyDTO ModelToDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setId(company.getId());
		companyDto.setName((company.getName() != null) ? company.getName() : "");
		return companyDto;
	}

}
