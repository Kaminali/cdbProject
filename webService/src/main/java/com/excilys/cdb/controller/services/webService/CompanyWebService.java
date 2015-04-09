package com.excilys.cdb.controller.services.webService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.dto.CompanyDTO;
import com.excilys.cdb.controller.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.controller.services.ICompanyServices;

@Path("/companyService")
@Component
public class CompanyWebService {

	@Autowired
	ICompanyServices companyServices;
	
	@GET
	@Path("/getCompanys")
	@Produces("application/json")
	/**
	 * 
	 * @return all company in a list
	 */
	public List<CompanyDTO> getComputers() {
 
		List<CompanyDTO> computerList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());
		
		return computerList;
 
	}

	@GET
	@Path("/removeCompany/{id}")
	@Produces("application/json")
	/**
	 * delete a company
	 * @param company id
	 * WARNING : delete all related computer
	 */
	public String removeCompany(@PathParam("id") long id) {
		try {
			companyServices.deleteCompany(id);
			return "ok";
		} catch (Exception e) {
			return "error : " + e.getMessage();
		}
 
	}
	 
}
