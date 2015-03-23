/**
 * 
 */
package com.excilys.cdb.controler.dtoMapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.model.bean.Company;

/**
 * @author excilys
 *
 */
public final class MapCompanyDTO {

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
		company.setId(companyDto.getId());company.setName(companyDto.getName());
		return company;
	}

	public static CompanyDTO ModelToDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		if (company != null) {
			companyDto.setId((company.getId() != null) ? company.getId() : null);
			companyDto.setName((company.getName() != null) ? company.getName() : "");
		}
		return companyDto;
	}

}
