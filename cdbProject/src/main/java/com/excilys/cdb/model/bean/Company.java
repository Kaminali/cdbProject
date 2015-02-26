/**
 * 
 */
package com.excilys.cdb.model.bean;

/**
 * @author excilys
 *
 */
public class Company {
	
	private Long id;
	private String name;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String newLine=System.getProperty("line.separator"); 
		
		StringBuffer company = new StringBuffer("Identifiant de l'entreprise : ");
		
		company.append(id);
		company.append(newLine);
		company.append("\tNom de l'entreprise : ");
		company.append(name);
		company.append(newLine);
		
		return company.toString();
	}

}
