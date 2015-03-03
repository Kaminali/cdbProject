package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.CompanyServices;
import com.excilys.cdb.controler.services.ComputerServices;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CompanyServices companyServices = new CompanyServices();
		ComputerServices computerServices = new ComputerServices();

		List<CompanyDTO> companyList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());

		long id = (request.getParameter("id") != null) ? Long.valueOf(request
				.getParameter("id")) : -1l;

		request.setAttribute("computerEdit",
				MapComputerDTO.ModelToDto(computerServices.getComputerById(id)));

		request.setAttribute("companyL", companyList);
		request.setAttribute(
				"result",
				(request.getAttribute("result") != null) ? request
						.getAttribute("result") : "");

		getServletContext().getRequestDispatcher("/views/addComputer.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			ComputerServices computerServices = new ComputerServices();
			try {
				if (request.getParameter("computerId") != null) {
					computerDto.setId(Long.valueOf(request.getParameter("computerId")));
					computerServices.updateComputer(computerDto);
				} else {
					computerServices.insertComputer(computerDto);
				}
				result = "succes";
			} catch (Exception e) {
				e.printStackTrace();
				result = "operation fail";
			}

		}
		request.setAttribute("result", result);
		this.doGet(request, response);
	}

}
