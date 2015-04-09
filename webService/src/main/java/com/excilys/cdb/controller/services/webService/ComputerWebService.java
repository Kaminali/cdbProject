package com.excilys.cdb.controller.services.webService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.controller.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controller.services.IComputerServices;

@Path("/computerService")
@Component
public class ComputerWebService {

	@Autowired
	IComputerServices computerServices;
	
	@GET
	@Path("/getComputers/{index}/{offset}/{lang}")
	@Produces("application/json")
	/**
	 * 
	 * @param begin
	 * @param nb
	 * @param lang : can be null. The lang for format the date for the display
	 * @return a list of computerDTO, with a size limited with the param nb, begin at the index declare with the param begin
	 * you can send -1 for begin and nb for a complete list
	 */
	public List<ComputerDTO> get(@PathParam("index") int index, @PathParam("offset") int offset, @PathParam("lang") String lang) {
 
		if(lang == "null" || lang == "") {
			lang = null;
		}
		List<ComputerDTO> computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer(index, offset), lang);
		
		return computerList;
 
	}
	
	@GET
	@Path("/getComputersSearch/{search}/{index}/{offset}/{lang}")
	@Produces("application/json")
	/**
	 * 
	 * @param name
	 * @param begin
	 * @param nb
	 * @param lang : can be null. The lang for format the date for the display
	 * @return a list of computerDTO, with the name restriction, a size limited with the param nb and begin at the index declare with the param begin
	 * you can send -1 for begin and nb for a complete list with the restriction
	 */
	public List<ComputerDTO> getByName(@PathParam("search") String search, @PathParam("index") int index,
			@PathParam("offset") int offset, @PathParam("lang") String lang) {
 
		if(lang == "null" || lang == "") {
			lang = null;
		}
		List<ComputerDTO> computerList = MapComputerDTO.ModelToDto(computerServices.getByName(search, index, offset), lang);
		
		return computerList;
 
	}
	
	@GET
	@Path("/getComputer/{id}/{lang}")
	@Produces("application/json")
	/**
	 * 
	 * @param id
	 * @param lang : can be null. The lang for format the date for the display
	 * @return the computerDTO with the param id, or null if it not exist.
	 */
	public ComputerDTO getComputer(@PathParam("id") long id, @PathParam("lang") String lang) {
 
		if(lang.equals("null") || lang.equals("")) {
			lang = null;
		}
		ComputerDTO computer = MapComputerDTO.ModelToDto(computerServices.getComputerById(id), lang);
		
		return computer;
 
	}

	@GET
	@Path("/getNb/{search}")
	@Produces("application/json")
	/**
	 * 
	 * @param name
	 * @return the number of computer, with the name restriction
	 */
	public Integer getNb(@PathParam("search") String search) {
 
		return computerServices.getNb(search);
	}

	@GET
	@Path("/getNb")
	@Produces("application/json")
	/**
	 * 
	 * @return the number of computer
	 */
	public Integer getNb() {
 
		return computerServices.getNb();
	}
	
	@POST
	@Path("/addComputer")
	@Consumes("application/json")
	/**
	 * add a computer
	 * @param computerDTO
	 */
	public void addComputer(ComputerDTO computer) {
		computerServices.insertComputer(MapComputerDTO.DtoToModel(computer));
 
	}
	
	@POST
	@Path("/updateComputer")
	@Consumes("application/json")
	/**
	 * update a computer
	 * @param computerDTO
	 */
	public void updateComputer(ComputerDTO computer) {
		computerServices.updateComputer(MapComputerDTO.DtoToModel(computer));
 
	}

	@GET
	@Path("/removeComputer/{id}")
	@Produces("application/json")
	/**
	 * delete a computer
	 * @param the computer id
	 */
	public String removeComputer(@PathParam("id") long id) {
		
		try {
			computerServices.deleteComputer(id);
			return "ok";
		} catch (Exception e) {
			return "error : " + e.getMessage();
		}
 
	}

	@POST
	@Path("/removeComputers")
	@Consumes("application/json")
	@Produces("application/json")
	/**
	 * delete a list of computer
	 * @param the list of the computersId
	 */
	public String removeComputers(List<Long> computersId) {
		
		try {
			computerServices.deleteComputer(computersId);
			return "ok";
		} catch (Exception e) {
			return "error : " + e.getMessage();
		}
 
	}
	 
}
