/**
 * 
 */
package com.excilys.cdb.controler.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controler.services.ComputerServices;
import com.excilys.cdb.model.bean.Computer;

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
		long nb = (request.getParameter("nb") != null) ? Long.valueOf(request.getParameter("p")) : 10;
		
		ArrayList<Computer> computerList = computerServices.getAllComputeur(p, nb);

		StringBuilder rep = new StringBuilder();

		for (Computer computer : computerList) {

			rep.append("<tr><td class=\"editMode\"><input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\"");
			rep.append(computer.getId());
			rep.append("\"></td><td><a href=\"editComputer.html\" onclick=\"\">");
			rep.append(computer.getName());
			rep.append("</a></td><td>");
			rep.append(computer.getIntroduced());
			rep.append("</td><td>");
			rep.append(computer.getDiscontinued());
			rep.append("</td><td>");
			rep.append((-1 == computer.getCompany().getId()) ? "" : computer
					.getCompany().getName());
			rep.append("</td></tr>");

		}

		request.setAttribute("resultat", rep.toString());
		request.setAttribute("pagination", pagination(request));

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);
	}

	private String pagination(HttpServletRequest request) {
		long p = (request.getParameter("p") != null) ? Long.valueOf(request.getParameter("p")) : 1;
		long nb = (request.getParameter("nb") != null) ? Long.valueOf(request.getParameter("p")) : 10;

		StringBuilder pag = new StringBuilder();

		pag.append("<div class=\"container text-center\"><ul class=\"pagination\">");

		pag.append("<li><a href=\"Dashboard?p=");
		pag.append(p-1);
		pag.append("&nb=");
		pag.append(nb);
		pag.append("\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span></a></li>");

		for (long i = p - 2; i <= p + 2; i++) {
			pag.append("<li><a href=\"Dashboard?p=");
			pag.append(i);
			pag.append("&nb=");
			pag.append(nb);
			pag.append(">");
			pag.append(i);
			pag.append("</a></li>");
		}

		pag.append("<li><a href=\"Dashboard?p=");
		pag.append(p+1);
		pag.append("&nb=");
		pag.append(nb);
		pag.append("\" aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span></a></li>");

		pag.append("</ul></div>");

		pag.append("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
		pag.append("<button type=\"button\" class=\"btn btn-default\">10</button>");
		pag.append("<button type=\"button\" class=\"btn btn-default\">50</button>");
		pag.append("<button type=\"button\" class=\"btn btn-default\">100</button>");
		pag.append("</div>");

		return pag.toString();
	}
}