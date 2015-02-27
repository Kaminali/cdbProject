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

import com.excilys.cdb.controler.dto.ComputerDTO;
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
		
		ArrayList<ComputerDTO> computerList = computerServices.getAllComputeur((p-1)*nb, nb);

		request.setAttribute("resultatC", computerList);
		request.setAttribute("page", p);
		request.setAttribute("offset", nb);
		request.setAttribute("max", computerServices.getNb());
		request.setAttribute("pagination", pagination(p, nb, computerServices.getNb()));

		getServletContext().getRequestDispatcher("/views/dashboard.jsp")
				.forward(request, response);
	}

	private String pagination(long p, long nb, int max) {
		long last = max / nb + 1;
		StringBuilder pag = new StringBuilder();

		pag.append("<div class=\"container text-center\"><ul class=\"pagination\">");

		if (p > 1) {
			pag.append("<li><a href=\"Dashboard?p=");
			pag.append(p - 1);
			pag.append("&nb=");
			pag.append(nb);
			pag.append("\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span></a></li>");
		}
		for (long i = p - 5; i <= p + 5; i++) {
			if (i > 0 && i <= last) {
				pag.append("<li><a href=\"Dashboard?p=");
				pag.append(i);
				pag.append("&nb=");
				pag.append(nb);
				pag.append("\">");
				pag.append(i);
				pag.append("</a></li>");
			}
		}
		if (p < last) {
			pag.append("<li><a href=\"Dashboard?p=");
			pag.append(p + 1);
			pag.append("&nb=");
			pag.append(nb);
			pag.append("\" aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span></a></li>");
		}
		pag.append("</ul>");
		pag.append("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
		pag.append("<form action=\"Dashboard?p=");
		pag.append(p);
		pag.append("\" method=\"post\">");
		pag.append("<button name=\"nbB\" type=\"button\" value=\"10\" class=\"btn btn-default\" >10</button>");
		pag.append("<button name=\"nbB\" type=\"button\" value=\"50\" class=\"btn btn-default\" >50</button>");
		pag.append("<button name=\"nbB\" type=\"button\" value=\"100\" class=\"btn btn-default\" >100</button>");
		pag.append("</form></div></div>");

		return pag.toString();
	}
}