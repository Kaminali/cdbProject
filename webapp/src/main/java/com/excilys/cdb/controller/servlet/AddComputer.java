package com.excilys.cdb.controller.servlet;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controller.dto.CompanyDTO;
import com.excilys.cdb.controller.dto.ComputerDTO;


@Controller
@RequestMapping("/addComputer")
public class AddComputer  {

	private Locale locale;
	
	private Client client;
	
	private WebTarget computerTarget;
	
	public AddComputer() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	}

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
	    	computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/updateComputer");
	    	computerTarget.request().post(Entity.entity(computerDTO, "application/json"));
	    }
	    else {
	    	computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/addComputer");
	    	computerTarget.request().post(Entity.entity(computerDTO, "application/json"));
	    }
		pModel.addAttribute("result", "succes");
		operation(pModel, id, "succes");
	    return "addComputer";
    }
    
    private void operation(ModelMap pModel, long id, String result) {    	
    	computerTarget = client.target("http://localhost:8580/cdbProject/rest/companyService/getCompanys");
		List<CompanyDTO> companyList = computerTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<CompanyDTO>>() {}); 

		pModel.addAttribute("computerEdit", null);
		if(id != -1) {
			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getComputer");
			ComputerDTO computerDto = computerTarget.path("/"+id+"/null")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<ComputerDTO>() {});
			
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
