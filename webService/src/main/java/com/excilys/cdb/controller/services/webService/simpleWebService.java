package com.excilys.cdb.controller.services.webService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.controller.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controller.services.IComputerServices;

@Path("/hello")
@Component
@Produces("application/json")
public class simpleWebService {

	@Autowired
	IComputerServices computerServices;
	
	@GET
	@Path("/{param}")
	public List<ComputerDTO> getMsg(@PathParam("param") String msg) {
 
		

		List<ComputerDTO> computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer(1, 50), null);
		
		String output = "Jersey say : " + msg;
 
		return computerList;
 
	}
	 
}
