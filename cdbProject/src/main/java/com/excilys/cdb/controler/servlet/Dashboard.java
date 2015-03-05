/**
 * 
 */
package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.ComputerServices;
import com.excilys.cdb.view.Page;

/**
 * @author excilys
 *
 */
@WebServlet("/Dashboard")
public class Dashboard extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	public Dashboard() { 
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ComputerServices computerServices = new ComputerServices();

		
		if(request.getParameter("selection") != null) {
			String[] computers = request.getParameter("selection").split(",");
			ArrayList<Long> computersId = new ArrayList<Long>();
			for(String computer : computers) {
				computersId.add(Long.valueOf(computer));
			}
			computerServices.deleteComputer(computersId);
		}
		
		long p = (request.getParameter("p") != null) ? Long.valueOf(request.getParameter("p")) : 1l;
		long nb = (request.getParameter("nb") != null) ? Long.valueOf(request.getParameter("nb")) : 10l;
		nb = (request.getParameter("nbB") != null) ? Long.valueOf(request.getParameter("nbB")) : nb;
		p = (request.getParameter("nbB") != null) ? 1l : p;
		
		Page page = new Page();
		
		page.setPage(p);
		page.setNbElementPage(nb);
		
		List<ComputerDTO> computerList;
		if(request.getParameter("search") != null || request.getAttribute("searchC") != null) {
			computerList = (request.getParameter("search") != null) ? 
					MapComputerDTO.ModelToDto(computerServices.getByName(request.getParameter("search") , (p-1)*nb, nb))
					:  MapComputerDTO.ModelToDto(computerServices.getByName(String.valueOf(request.getAttribute("searchC")) , (p-1)*nb, nb));
			request.setAttribute("searchC", (request.getParameter("search") != null) ? 
					"&search="+request.getParameter("search")
					: "&search="+request.getAttribute("searchC"));
			page.setNbElement(computerServices.getNb(request.getParameter("search")));
			page.setSearch((request.getParameter("search") != null) ? 
					request.getParameter("search") 
					: (String) request.getAttribute("searchC"));
		}
		else {
			computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer((p-1)*nb, nb));
			request.setAttribute("searchC", "");
			page.setNbElement(computerServices.getNb());
		}
		request.setAttribute("resultatC", computerList);
		System.out.println(page.getPage());
		page.setNbChoice(3);
		request.setAttribute("pagination", page);

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);

		
	}
}