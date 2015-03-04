package com.excilys.cdb.view.tagLib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Pagination extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086144314382824259L;
	private String page;
	private String max;
	private String offset;
	private String search;
	
	public int doStartTag() throws JspException {
		
		try {
			long p = Long.valueOf(this.page);
			long nb = Long.valueOf(this.offset);
			long max = Long.valueOf(this.max);
			String search = (this.getSearch());
			long last = max / nb + 1;
			
			pageContext.getOut().println("<div class=\"container text-center\"><ul class=\"pagination\">");

			if (p > 1) {
				pageContext.getOut().println("<li><a href=\"Dashboard?p=" + (p-1));
				pageContext.getOut().println("&nb=" + nb + search);
				pageContext.getOut().println("\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span></a></li>");
			}
			for (long i = p - 5; i <= p + 5; i++) {
				if (i > 0 && i <= last) {
					pageContext.getOut().println("<li><a href=\"Dashboard?p=" + i);
					pageContext.getOut().println("&nb=" + nb + search);
					pageContext.getOut().println("\">");
					if(p==i) {
						pageContext.getOut().println("<b>");
					}
					pageContext.getOut().println(i);
					if(p==i) {
						pageContext.getOut().println("</b>");
					}
					pageContext.getOut().println("</a></li>");
				}
			}
			if (p < last) {
				pageContext.getOut().println("<li><a href=\"Dashboard?p=" + (p+1));
				pageContext.getOut().println("&nb=" + nb + search);
				pageContext.getOut().println("\" aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span></a></li>");
			}
			pageContext.getOut().println("</ul>");
			pageContext.getOut().println("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
			pageContext.getOut().println("<form action=\"Dashboard\" method=\"post\">");

			pageContext.getOut().println("<input type=\"text\" value=\"" + search + "\" name=\"search\" hidden=\"true\" />");
			pageContext.getOut().println("<button name=\"nbB\" type=\"submit\" value=\"10\" class=\"btn btn-default\" >10</button>");
			pageContext.getOut().println("<button name=\"nbB\" type=\"submit\" value=\"50\" class=\"btn btn-default\" >50</button>");
			pageContext.getOut().println("<button name=\"nbB\" type=\"submit\" value=\"100\" class=\"btn btn-default\" >100</button>");
			pageContext.getOut().println("</form></div></div>");
			
		} catch (IOException e) {
			throw new JspException("I/O Error", e);
		}
		return SKIP_BODY;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * @return the offset
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(String offset) {
		this.offset = offset;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

}
