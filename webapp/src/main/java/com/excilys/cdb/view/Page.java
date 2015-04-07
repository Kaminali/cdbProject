package com.excilys.cdb.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Page {

	private long page;
	private long nbElement;
	private long nbElementPage;
	private long nbpage;
	private String search;
	private boolean order;
	private long nbChoice;
	private int orderCol;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getNbElement() {
		return nbElement;
	}

	public void setNbElement(long nbElement) {
		this.nbElement = nbElement;
		if (this.nbElementPage != 0) {
			this.nbpage = 1 + (this.nbElement / this.nbElementPage);
		}
	}

	public long getNbElementPage() {
		return nbElementPage;
	}

	public void setNbElementPage(long nbElementPage) {
		this.nbElementPage = nbElementPage;
		if (this.nbElementPage != 0) {
			this.nbpage = 1 + (this.nbElement / this.nbElementPage);
		}
	}

	public long getNbpage() {
		return nbpage;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean getOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public long getNbChoice() {
		return nbChoice;
	}

	public void setNbChoice(long nbChoice) {
		this.nbChoice = nbChoice;
	}

	public void setOrderCol(int orderCol) {
		this.orderCol = orderCol;
	}
	
	public int getOrderCol() {
		return this.orderCol;
	}
	
	@Override
	public String toString(){
		return "page : " + page + " nb : " + nbElement + " nbPpage : " + nbElementPage;
	}

}