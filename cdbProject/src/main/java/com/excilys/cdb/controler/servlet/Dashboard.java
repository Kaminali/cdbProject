/**
 * 
 */
package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.ComputerServices;

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

		long p = (request.getParameter("p") != null) ? Long.valueOf(request.getParameter("p")) : 1;
		long nb = (request.getParameter("nb") != null) ? Long.valueOf(request.getParameter("nb")) : 10;
		nb = (request.getParameter("nbB") != null) ? Long.valueOf(request.getParameter("nbB")) : nb;
		p = (request.getParameter("nbB") != null) ? 1 : p;
		
		List<ComputerDTO> computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer((p-1)*nb, nb));

		request.setAttribute("resultatC", computerList);
		request.setAttribute("page", p);
		request.setAttribute("offset", nb);
		request.setAttribute("max", computerServices.getNb());

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);
	}
}