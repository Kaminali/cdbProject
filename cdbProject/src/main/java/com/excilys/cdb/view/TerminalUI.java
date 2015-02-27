/**
 * 
 */
package com.excilys.cdb.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.services.ComputerServices;
import com.excilys.cdb.controler.services.CompanyServices;
import com.excilys.cdb.controler.validate.CheckValues;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;

/**
 * @author excilys
 *
 */
public class TerminalUI {

	private ComputerServices computerServices;
	private CompanyServices companyServices;
	private StringBuffer menu;
	private String newLine;
	private int demande;
	private Scanner sc;

	public TerminalUI() {
		computerServices = new ComputerServices();
		companyServices = new CompanyServices();

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
		menu.append("7 : quitter l'application");
		menu.append(newLine);

		demande = 0;

		sc = new Scanner(System.in);
	}

	public void destroy() {
		sc.close();
	}

	public void launchUI() {

		while (demande != 7) {

			System.out.println(menu);

			try {
				demande = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				demande = 8;
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
			}

			System.out.println("");

			if (demande != 7 && demande != 8) {
				System.out
						.println("Appuyer sur n'importe quelle touche pour revenir au menu");
				sc.nextLine();
			}

			if (demande == 8) {
				System.out.println("Erreur de saisie");
				sc.nextLine();
			}
			System.out.println("");
		}
	}

	private void computerList() {
		ArrayList<ComputerDTO> computerList = computerServices.getAllComputeur(-1l, -1l);
		for (ComputerDTO computer : computerList) {
			System.out.println(computer.toStringMin());
		}
	}

	private void computerDetail() {
		System.out.println("taper un id valide d'ordinateur");
		long id = -2;
		try {
			id = Long.parseLong(sc.nextLine());

		} catch (Exception e) {
			demande = 8;
		}

		try {
			CheckValues.checkComputer(id);
			Computer computer = computerServices.getComputeurById(id);
			System.out.println(computer.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void companyList() {
		/*ArrayList<Company> companyList = companyServices.getAllCompany();
		for (Company company : companyList) {
			System.out.println(company.toString());
		}*/
	}

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
/*
		try {
			computerServices.insertComputer(name, introduced, discontinued,
					idCompany);
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}*/
	}

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

		try {
			CheckValues.checkComputer(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
/*
		try {
			computerServices.updateComputer(name, introduced, discontinued,
					idCompany, id);
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}*/
	}

	private void removeComputer() {
		System.out.println("Taper l'id de l'ordinateur");
		Long id;

		try {
			id = Long.parseLong(sc.nextLine());
		} catch (Exception e) {
			id = null;
		}

		try {
			CheckValues.checkComputer(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			computerServices.deleteComputer(id);
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
