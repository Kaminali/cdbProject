/**
 * 
 */
package com.excilys.cdb.controler.dto;


import com.excilys.cdb.controler.validator.Date;

import javax.validation.constraints.NotNull;

/**
 * @author excilys
 *
 */
public class ComputerDTO {

	private Long id;
	
	@NotNull(message="name null")
	private String name;
	
	@Date(message="fromat error")
	private String introduced;
	
	@Date(message="fromat error")
	private String discontinued;

	private long companyId;

	private String companyName;

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

	//@DateTimeFormat(pattern="yyyy-MM-dd")
	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
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
		computer.append(companyName);
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long id) {
		this.companyId = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
