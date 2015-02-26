/**
 * 
 */
package com.excilys.cdb.model.bean;

import java.sql.Timestamp;

/**
 * @author Nicolas Guibert
 *
 */
public class Computer {

	private Long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Company company;

	public Computer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		String newLine=System.getProperty("line.separator"); 
		
		StringBuffer computer = new StringBuffer("Identifiant de l'ordinateur : ");
		
		computer.append(id);
		computer.append(newLine);
		computer.append("\tNom de l'ordinateur : ");
		computer.append(name);
		computer.append(newLine);
		computer.append("\tArrivé en : ");
		computer.append(introduced);
		computer.append(newLine);
		computer.append("\tAbandonné en : ");
		computer.append(discontinued);
		computer.append(newLine);
		computer.append("\tEntreprise : ");
		computer.append(getCompany().getName());
		computer.append(newLine);
		
		return computer.toString();
	}
	
	public String toStringMin() {
		String newLine=System.getProperty("line.separator"); 
		StringBuffer computer = new StringBuffer("id : ");
		
		computer.append(id);
		computer.append("  \tNom : ");
		computer.append(name);
		computer.append(newLine);
		
		return computer.toString();
	}

	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

}
