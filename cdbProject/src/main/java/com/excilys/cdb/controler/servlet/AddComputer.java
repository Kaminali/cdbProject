package com.excilys.cdb.controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dto.ComputerDTO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		if(request.getParameter("computerName") != null) {
			computerDto.setName(request.getParameter("computerName"));
			computerDto.setIntroduced(request.getParameter("introduced"));
			computerDto.setDiscontinued(request.getParameter("discontinued"));
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(Long.valueOf(request.getParameter("companyId")));
			computerDto.setCompanyDto(companyDto);
		}
		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
	}

}
