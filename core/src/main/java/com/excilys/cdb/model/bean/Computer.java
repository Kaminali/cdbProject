/**
 * 
 */
package com.excilys.cdb.model.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.cdb.model.converter.LocalDatePersistenceConverter;


@Entity
@Table(name = "computer")
public class Computer implements Serializable {

	private static final long serialVersionUID = -6159552519467659239L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "introduced")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate introduced;

	@Column(name = "discontinued")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate discontinued;

	@JoinColumn(name = "company_id")
	@ManyToOne
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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = (introduced != null && introduced.toString() != "0000-00-00 00:00:00") ? introduced.toLocalDateTime().toLocalDate() : null;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = (discontinued != null && discontinued.toString() != "0000-00-00 00:00:00") ? discontinued.toLocalDateTime().toLocalDate() : null;
	}

	public void setDiscontinued(LocalDate discontinued) {
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
		computer.append((getCompany() != null) ? getCompany().getName() : "");
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

	public void setCompany(Company company) {
		this.company = company;
	}

}
