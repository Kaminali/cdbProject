package com.excilys.cdb.controller.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.view.Page;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	private Locale locale;
	
	private Client client;
	
	private WebTarget computerTarget;
	
	public Dashboard() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String loadOrRefreshG(final ModelMap pModel,  
			@RequestParam(value="p", defaultValue="1") long p,
			@RequestParam(value="selection", required = false) final String selection,
			@RequestParam(value="nb", defaultValue="10") long nb,
			@RequestParam(value="nbB", defaultValue="-1") long nbB,
			@RequestParam(value="search", required = false) String search,
			@RequestParam(value="searchC", required = false) String searchC,
			@RequestParam(value="lang", required = false) String lang
			) {

		locale = LocaleContextHolder.getLocale();
		
		operation(pModel, p, selection, nb, nbB, search, searchC);
		
		return "dashboard";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String loadOrRefreshP(final ModelMap pModel,  
			@RequestParam(value="p", defaultValue="1") long p,
			@RequestParam(value="selection", required = false) final String selection,
			@RequestParam(value="nb", defaultValue="10") long nb,
			@RequestParam(value="nbB", defaultValue="-1") long nbB,
			@RequestParam(value="search", required = false) String search,
			@RequestParam(value="searchC", required = false) String searchC,
			@RequestParam(value="lang", required = false) String lang
			) {


		locale = LocaleContextHolder.getLocale();
		
		operation(pModel, p, selection, nb, nbB, search, searchC);

		return "dashboard";
	}

	private void operation(final ModelMap pModel, long p, String selection, long nb, long nbB, String search, String searchC) {

		if(selection != null) {
			String[] computers = selection.split(",");
			ArrayList<Long> computersId = new ArrayList<Long>();
			for(String computer : computers) {
				computersId.add(Long.valueOf(computer));
			}
			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/removeComputers");
			computerTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(computersId, "application/json"), String.class);
		}

		nb = (nbB != -1) ? nbB : nb;
		p = (nbB != -1) ? 1l : p;

		Page page = new Page();
		page.setPage(p);
		page.setNbElementPage(nb);

		List<ComputerDTO> computerList;

		if( (search != null && search != "") || (searchC != null && searchC != "")) {
			
			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getComputersSearch");
			search = (search != null) ? search : searchC;
			computerList = computerTarget.path("/"+search+"/"+(p-1)*nb+"/"+nb+"/"+locale.getLanguage())
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ComputerDTO>>() {});
					
			pModel.addAttribute("searchC", search);
			

			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getNb");
			Integer nb2 = computerTarget.path("/"+search).request(MediaType.APPLICATION_JSON).get(new GenericType<Integer>() {});
			page.setNbElement(nb2);
			
			page.setSearch(search);
		}
		else {
			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getComputers");
			computerList = computerTarget.path("/"+(p-1)*nb+"/"+nb+"/"+locale.getLanguage())
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ComputerDTO>>() {});
			pModel.addAttribute("searchC", "");
			computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getNb");
			Integer nb2 = computerTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<Integer>() {});
			page.setNbElement(nb2);

		}
		pModel.addAttribute("resultatC", computerList);
		page.setNbChoice(3);
		pModel.addAttribute("pagination", page);
	}
}