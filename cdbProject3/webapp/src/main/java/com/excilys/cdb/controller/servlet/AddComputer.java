package com.excilys.cdb.controller.servlet;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controler.services.ICompanyServices;
import com.excilys.cdb.controler.services.IComputerServices;
import com.excilys.cdb.controller.dto.CompanyDTO;
import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.controller.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.controller.dtoMapper.MapComputerDTO;


@Controller
@RequestMapping("/addComputer")
public class AddComputer  {
	
	@Autowired
	private IComputerServices computerServices;
	
	@Autowired
	private ICompanyServices companyServices;

	private Locale locale;
	
    @RequestMapping(method = RequestMethod.GET)
    public String loadPage(final ModelMap pModel,  
    		@RequestParam(value="id", defaultValue="-1") long id,
    		@RequestParam(value="result", required = false) final String result
    		) {

		operation(pModel, id, result);
        return "addComputer";
    
    }

    
    @RequestMapping(method = RequestMethod.POST)
    public String add(ModelMap pModel, @Valid ComputerDTO computerDTO, BindingResult result,
    		@RequestParam(value="id", defaultValue="-1") long id) {
	    if(result.hasErrors()) {
		    pModel.addAttribute("computerDTO", computerDTO);
			pModel.addAttribute("result", "echec");
			operation(pModel, id, "echec");
		    return "addComputer";
	    }
	    if(id != -1 || computerDTO.getId() != null) {
		    computerServices.updateComputer(MapComputerDTO.DtoToModel(computerDTO));
	    }
	    else {
		    computerServices.insertComputer(MapComputerDTO.DtoToModel(computerDTO));
	    }
		pModel.addAttribute("result", "succes");
		operation(pModel, id, "succes");
	    return "addComputer";
    }
    
    private void operation(ModelMap pModel, long id, String result) {    	
    	List<CompanyDTO> companyList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());

		pModel.addAttribute("computerEdit", null);
		if(id != -1) {
			ComputerDTO computerDto = MapComputerDTO.ModelToDto(computerServices.getComputerById(id), null);
			pModel.addAttribute("computerEdit", computerDto);
			pModel.addAttribute("computerDTO", computerDto);
		}
		else {
			pModel.addAttribute("computerDTO", new ComputerDTO());
		}

		pModel.addAttribute("companyL", companyList);
		pModel.addAttribute("result", (result != null) ? result : "");

		locale = LocaleContextHolder.getLocale();
		pModel.addAttribute("langTag", locale.toLanguageTag());
    	
    }

}
