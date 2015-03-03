/**
 * 
 */
package com.excilys.cdb.controler.dtoMapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

/**
 * @author excilys
 *
 */
public class MapCompanyDTO {

	public static List<Company> DtoToModel(List<CompanyDTO> companyDtoL) {
		List<Company> companyL = new ArrayList<Company>();
		for (CompanyDTO companyDto : companyDtoL) {
			companyL.add(DtoToModel(companyDto));
		}
		return companyL;
	}

	public static List<CompanyDTO> ModelToDto(List<Company> companyL) {
		List<CompanyDTO> companyDtoL = new ArrayList<CompanyDTO>();
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
