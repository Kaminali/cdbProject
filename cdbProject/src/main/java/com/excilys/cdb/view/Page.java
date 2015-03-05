package com.excilys.cdb.view;

public class Page {

	private long page;
	private long nbElement;
	private long nbElementPage;
	private long nbpage;
	private String search;
	private long order;
	private long nbChoice;

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
		System.out.println(this.nbElement);
		System.out.println(this.nbElementPage);
		if (this.nbElementPage != 0) {
			this.nbpage = 1 + (this.nbElement / this.nbElementPage);
		}
	}

	public long getNbElementPage() {
		return nbElementPage;
	}

	public void setNbElementPage(long nbElementPage) {
		this.nbElementPage = nbElementPage;
		System.out.println(this.nbElement + "/" );
		System.out.println(this.nbElementPage);
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

	public long getOrder() {
		return order;
	}

	public void setOrder(long order) {
		this.order = order;
	}

	public long getNbChoice() {
		return nbChoice;
	}

	public void setNbChoice(long nbChoice) {
		this.nbChoice = nbChoice;
	}

}