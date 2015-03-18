/**
 * 
 */
package com.excilys.cdb.model.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1936752071160451297L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = true, length = 255)
	private String name;
	
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
