/**
 * 
 */
package com.excilys.cdb.controller.dtoMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.controller.dto.CompanyDTO;
import com.excilys.cdb.model.bean.Company;

/**
 * @author excilys
 *
 */
public final class MapCompanyDTO {

	public static List<Company> DtoToModel(List<CompanyDTO> companyDtoL) {
		return companyDtoL.stream().map(b -> MapCompanyDTO.DtoToModel(b)).collect(Collectors.toList());
	}

	public static List<CompanyDTO> ModelToDto(List<Company> companyL) {
		return companyL.stream().map(b -> MapCompanyDTO.ModelToDto(b)).collect(Collectors.toList());
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
