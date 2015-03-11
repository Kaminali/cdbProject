/**
 * 
 */
package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.ComputerServices;
import com.excilys.cdb.controler.services.IComputerServices;
import com.excilys.cdb.view.Page;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {


    
	/*@Autowired*/
	private IComputerServices computerServices;

    @RequestMapping(method = RequestMethod.GET)
    public String afficherBonjour(final ModelMap pModel,  
    		@RequestParam(value="p", defaultValue="1") final long pT,
    		@RequestParam(value="selection", defaultValue="1") final String selection,
    		@RequestParam(value="nb", defaultValue="10") final long nbT,
    		@RequestParam(value="nbB", defaultValue="-1") final long nbBT,
    		@RequestParam(value="search", defaultValue="1") final String search,
    		@RequestParam(value="searchC", defaultValue="1") final String searchC
    		) {
       // pModel.addAttribute("personne", p);
        
        
		if(selection != "-1") {
			String[] computers = selection.split(",");
			ArrayList<Long> computersId = new ArrayList<Long>();
			for(String computer : computers) {
				computersId.add(Long.valueOf(computer));
			}
			computerServices.deleteComputer(computersId);
		}
		
		long nb = (nbBT != -1) ? nbBT : nbT;
		long p = (nbBT != -1) ? 1l : pT;
		
		Page page = new Page();
		
		page.setPage(p);
		page.setNbElementPage(nb);
		
		List<ComputerDTO> computerList;
		if(search != "-1" || searchC != "-1") {
			computerList = (search != "-1") ? 
					MapComputerDTO.ModelToDto(computerServices.getByName(search , (p-1)*nb, nb))
					:  MapComputerDTO.ModelToDto(computerServices.getByName(String.valueOf(searchC) , (p-1)*nb, nb));
					pModel.addAttribute("searchC", (search != null) ? "&search="+search : "&search="+searchC);
			page.setNbElement(computerServices.getNb(search));
			page.setSearch((search != null) ? search : searchC);
		}
		else {
			computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer((p-1)*nb, nb));
			pModel.addAttribute("searchC", "");
			page.setNbElement(computerServices.getNb());
		}
		pModel.addAttribute("resultatC", computerList);
		page.setNbChoice(3);
		pModel.addAttribute("pagination", page);

		
        return "dashboard";
    
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String afficherBonjour2(final ModelMap pModel) {
        pModel.addAttribute("personne", "depuis un controleur");
		return "";
    }
    
    private void operation() {


		
	}
}