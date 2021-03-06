/**
 * 
 */
package com.excilys.cdb.view;

import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.cdb.controller.dto.CompanyDTO;
import com.excilys.cdb.controller.dto.ComputerDTO;
/**
 * @author excilys
 *
 */

@Component
@EnableTransactionManagement
public class TerminalUI {

	private StringBuffer menu;
	private String newLine;
	private int demande;
	private Scanner sc;
	private Client client;

	public TerminalUI() {


		client = ClientBuilder.newBuilder().register(JacksonFeature.class)
		            .build();
		
		newLine = System.getProperty("line.separator");

		menu = new StringBuffer("1 : afficher les ordinateur");

		menu.append(newLine);
		menu.append("2 : information détaillé sur un ordinateur");
		menu.append(newLine);
		menu.append("3 : afficher les entreprises");
		menu.append(newLine);
		menu.append("4 : ajouter un ordinateur");
		menu.append(newLine);
		menu.append("5 : modifier un ordinateur");
		menu.append(newLine);
		menu.append("6 : supprimer un ordinateur");
		menu.append(newLine);
		menu.append("7 : supprimer une entreprise");
		menu.append(newLine);
		menu.append("8 : quitter l'application");
		menu.append(newLine);

		demande = 0;

		sc = new Scanner(System.in);
	}

	public void destroy() {
		sc.close();
	}

	public void launchUI() {

		while (demande != 8) {

			System.out.println(menu);

			try {
				demande = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				demande = 9999;
			}

			switch (demande) {

			case 1:
				computerList();
				break;
			case 2:
				computerDetail();
				break;
			case 3:
				companyList();
				break;
			case 4:
				addComputer();
				break;
			case 5:
				updateComputer();
				break;
			case 6:
				removeComputer();
				break;
			case 7:
				removeCompany();
				break;
			}

			System.out.println("");

			if (demande != 8 && demande != 9999) {
				System.out
						.println("Appuyer sur n'importe quelle touche pour revenir au menu");
				sc.nextLine();
			}

			if (demande == 9999) {
				System.out.println("Erreur de saisie");
				sc.nextLine();
			}
			System.out.println("");
		}
	}

	/**
	 * display all computer
	 */
	private void computerList() {
		
		WebTarget computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getComputers");
		List<ComputerDTO> computerList = computerTarget.path("/-1/-1/null")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ComputerDTO>>() {});
		for (ComputerDTO computer : computerList) {
			System.out.println(computer.toStringMin());
		}
	}

	/**
	 * display a computer with detail
	 */
	private void computerDetail() {
		System.out.println("taper un id valide d'ordinateur");
		long id = -2;
		try {
			id = Long.parseLong(sc.nextLine());

		} catch (Exception e) {
			demande = 9999;
		}

		try {
			WebTarget computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/getComputer");
			ComputerDTO computer = computerTarget.path("/"+id+"/null")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<ComputerDTO>() {});
			System.out.println(computer.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Display all company
	 */
	private void companyList() {
		
		WebTarget companyTarget = client.target("http://localhost:8580/cdbProject/rest/companyService/getCompanys");
		
		List<CompanyDTO> companyList = companyTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<CompanyDTO>>() {}); 
		
		for (CompanyDTO company : companyList) {
			System.out.println(company.toString());
		}
	}

	/**
	 * add a computer
	 */
	private void addComputer() {
		System.out.println("Taper le nom de l'ordinateur (obligatoire)");
		String name;
		String introduced;
		String discontinued;
		Long idCompany;
		name = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper la date d'arriver sur le marché (ou passer au point suivant avec \"Entrée\")");
		introduced = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper la date d'abandon du pc (ou passer au point suivant avec \"Entrée\")");
		discontinued = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper l'id de l'entreprise associé (ou passer au point suivant avec \"Entrée\")");
		try {
			idCompany = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			idCompany = null;
		}

		try {
			ComputerDTO computerDto = new ComputerDTO();
			computerDto.setName(name);
			computerDto.setIntroduced(introduced);
			computerDto.setDiscontinued(discontinued);
			computerDto.setCompanyId(idCompany);
			
			WebTarget computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/addComputer");
			computerTarget.request().post(Entity.entity(computerDto, "application/json"));

			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * update a computer
	 */
	private void updateComputer() {
		System.out.println("Taper l'id de l'ordinateur");
		Long id;
		String name;
		String introduced;
		String discontinued;
		Long idCompany;

		try {
			id = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			id = null;
		}
		System.out.println("Taper le nom de l'ordinateur (obligatoire)");
		name = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper la date d'arriver sur le marché (ou passer au point suivant avec \"Entrée\")");
		introduced = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper la date d'abandon du pc (ou passer au point suivant avec \"Entrée\")");
		discontinued = sc.nextLine();

		System.out.println(newLine);
		System.out
				.println("Taper l'id de l'entreprise associé (ou passer au point suivant avec \"Entrée\")");
		try {
			idCompany = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			idCompany = null;
		}

		try {
			ComputerDTO computerDto = new ComputerDTO();
			computerDto.setId(id);
			computerDto.setName(name);
			computerDto.setIntroduced(introduced);
			computerDto.setDiscontinued(discontinued);
			computerDto.setCompanyId(idCompany);
			
			WebTarget computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/updateComputer");
			computerTarget.request().post(Entity.entity(computerDto, "application/json"));
			
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * remove a computer
	 */
	private void removeComputer() {
		System.out.println("Taper l'id de l'ordinateur");
		Long id;

		try {
			id = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			id = null;
		}

		WebTarget computerTarget = client.target("http://localhost:8580/cdbProject/rest/computerService/removeComputer");
		String result = computerTarget.path("/"+id).request(MediaType.APPLICATION_JSON).get(new GenericType<String>() {});
		
		System.out.println(result);

	}
	
	/**
	 * remove a company
	 * WARNING : remove all computer linked to this company
	 */
	private void removeCompany()  {
		System.out.println("Taper l'id de l'entreprise");
		Long id;

		try {
			id = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			id = null;
		}

		try {
			System.out.println(id);
			
			WebTarget companyTarget = client.target("http://localhost:8580/cdbProject/rest/companyService/removeCompany");
			String result = companyTarget.path("/"+id).request(MediaType.APPLICATION_JSON).get(new GenericType<String>() {});
			
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
