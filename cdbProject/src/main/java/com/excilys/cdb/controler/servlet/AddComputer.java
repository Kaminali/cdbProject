package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dto.ComputerDTO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyServices companyServices = new CompanyServices();
		
		
		ArrayList<CompanyDTO> companyList = companyServices.getAllCompany();
		
		StringBuilder listC = new StringBuilder();
		listC.append("<option value=\"0\">--</option>");
		
		for(CompanyDTO company : companyList) {
			listC.append("<option value=\"");
			listC.append(company.getId());
			listC.append("\">");
			listC.append(company.getName());
			listC.append("</option>");
		}
		
		request.setAttribute("companyL", listC.toString());
		
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
			ComputerServices computerServices = new ComputerServices();
			try {
				computerServices.insertComputer(computerDto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
	}

}
