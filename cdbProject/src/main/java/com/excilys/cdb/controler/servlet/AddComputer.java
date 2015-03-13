package com.excilys.cdb.controler.servlet;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.ICompanyServices;
import com.excilys.cdb.controler.services.IComputerServices;


@Controller
@RequestMapping("/addComputer")
public class AddComputer  {
	
	@Autowired
	private IComputerServices computerServices;
	
	@Autowired
	private ICompanyServices companyServices;

    @RequestMapping(method = RequestMethod.GET)
    public String loadPage(final ModelMap pModel,  
    		@RequestParam(value="id", defaultValue="-1") long id,
    		@RequestParam(value="computerId", defaultValue="-1") long idComputer,
    		@RequestParam(value="result", required = false) final String result
    		) {

		operation(pModel, id, idComputer, result);
        return "addComputer";
    
    }


    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(final ModelMap pModel,  
    		@RequestParam(value="id", defaultValue="-1") long id,
    		@RequestParam(value="computerId", defaultValue="-1") long idComputer,
    		@RequestParam(value="computerName", required = false) final String computerName,
    		@RequestParam(value="introduced", required = false) final String introduced,
    		@RequestParam(value="discontinued", required = false) final String discontinued,
    		@RequestParam(value="companyId", required = false) final String companyId,
    		@RequestParam(value="computerId", required = false) final String computerId
    		) {
    	
    	ComputerDTO computerDto = new ComputerDTO();
		String result = null;
		
		if (computerName != null) {
			computerDto.setName(computerName);
			computerDto.setIntroduced(introduced);
			computerDto.setDiscontinued(discontinued);
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(Long.valueOf(companyId));
			computerDto.setCompanyDto(companyDto);
			if (computerId != null) {
				computerDto.setId(Long.valueOf(computerId));
				computerServices.updateComputer(MapComputerDTO.DtoToModel(computerDto));
			} else {
				computerServices.insertComputer(MapComputerDTO.DtoToModel(computerDto));
			}
				result = "succes";
		}
		
		pModel.addAttribute("result", result);
		operation(pModel, id, idComputer, result);
        return "addComputer";
    
    }
    
    private void operation(ModelMap pModel, long id, long idComputer, String result) {    	
    	List<CompanyDTO> companyList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());

		id = (idComputer != -1) ? idComputer : id;

		pModel.addAttribute("computerEdit", null);
		if(id != -1) {
			pModel.addAttribute("computerEdit", MapComputerDTO.ModelToDto(computerServices.getComputerById(id)));
		}

		pModel.addAttribute("companyL", companyList);
		pModel.addAttribute("result", (result != null) ? result : "");
    	
    }
    
/*
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICompanyServices companyServices = new CompanyServices();
		IComputerServices computerServices = new ComputerServices();

		List<CompanyDTO> companyList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());

		long id = (request.getParameter("computerId") != null) ? Long.valueOf(request.getParameter("computerId"))
				: (request.getParameter("id") != null) ? Long.valueOf(request.getParameter("id"))  : -1l;

		request.setAttribute("computerEdit", null);
		request.setAttribute("computerEdit", MapComputerDTO.ModelToDto(computerServices.getComputerById(id)));

		request.setAttribute("companyL", companyList);
		request.setAttribute("result", (request.getAttribute("result") != null) ? request.getAttribute("result") : "");

		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
	}*/
    
    /*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		String result = null;
		if (request.getParameter("computerName") != null) {
			computerDto.setName(request.getParameter("computerName"));
			computerDto.setIntroduced(request.getParameter("introduced"));
			computerDto.setDiscontinued(request.getParameter("discontinued"));
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(Long.valueOf(request.getParameter("companyId")));
			computerDto.setCompanyDto(companyDto);
			IComputerServices computerServices = new ComputerServices();
			try {
				if (request.getParameter("computerId") != null) {
					computerDto.setId(Long.valueOf(request.getParameter("computerId")));
					computerServices.updateComputer(MapComputerDTO.DtoToModel(computerDto));
				} else {
					computerServices.insertComputer(MapComputerDTO.DtoToModel(computerDto));
				}
				result = "succes";
			} catch (Exception e) {
				e.printStackTrace();
				result = "operation fail";
			}

		}
		request.setAttribute("result", result);
		this.doGet(request, response);
	}*/

}
