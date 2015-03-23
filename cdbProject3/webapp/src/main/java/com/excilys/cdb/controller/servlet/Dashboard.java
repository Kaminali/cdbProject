package com.excilys.cdb.controller.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.controler.services.IComputerServices;
import com.excilys.cdb.controller.dto.ComputerDTO;
import com.excilys.cdb.controller.dtoMapper.MapComputerDTO;
import com.excilys.cdb.view.Page;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	@Autowired
	private IComputerServices computerServices;
	
	private Locale locale;

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
			computerServices.deleteComputer(computersId);
		}

		nb = (nbB != -1) ? nbB : nb;
		p = (nbB != -1) ? 1l : p;

		Page page = new Page();
		page.setPage(p);
		page.setNbElementPage(nb);

		List<ComputerDTO> computerList;
		if( (search != null && search != "") || (searchC != null && searchC != "")) {
			computerList = (search != null) ? 
					MapComputerDTO.ModelToDto(computerServices.getByName(search , (p-1)*nb, nb), locale)
					:  MapComputerDTO.ModelToDto(computerServices.getByName(String.valueOf(searchC) , (p-1)*nb, nb), locale);
					pModel.addAttribute("searchC", (search != null) ? "&search="+search : "&search="+searchC);
					page.setNbElement(computerServices.getNb(search));
					page.setSearch((search != null) ? search : searchC);
		}
		else {
			computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer((p-1)*nb, nb), locale);
			pModel.addAttribute("searchC", "");
			page.setNbElement(computerServices.getNb());

		}
		pModel.addAttribute("resultatC", computerList);
		page.setNbChoice(3);
		pModel.addAttribute("pagination", page);
	}
}