/**
 * 
 */
package com.excilys.cdb.view;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.services.ICompanyServices;
import com.excilys.cdb.controler.services.IComputerServices;
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

	@Autowired
	private IComputerServices computerServices;

	@Autowired
	private ICompanyServices companyServices;
	
	public TerminalUI() {

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

	
	private void computerList() {
		List<ComputerDTO> computerList = MapComputerDTO.ModelToDto(computerServices.getAllComputer(-1l, -1l), null);
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
			demande = 9999;
		}

		try {
			ComputerDTO computer = MapComputerDTO.ModelToDto(computerServices.getComputerById(id), null);
			System.out.println(computer.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void companyList() {
		List<CompanyDTO> companyList = MapCompanyDTO.ModelToDto(companyServices.getAllCompany());
		for (CompanyDTO company : companyList) {
			System.out.println(company.toString());
		}
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

		try {
			ComputerDTO computerDto = new ComputerDTO();
			computerDto.setName(name);
			computerDto.setIntroduced(introduced);
			computerDto.setDiscontinued(discontinued);
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(idCompany);
			computerDto.setCompanyDto(companyDto);
			computerServices.insertComputer(MapComputerDTO.DtoToModel(computerDto));
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
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
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(idCompany);
			computerDto.setCompanyDto(companyDto);
			computerServices.updateComputer(MapComputerDTO.DtoToModel(computerDto));
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
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
			computerServices.deleteComputer(id);
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
	
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
			companyServices.deleteCompany(id);
			System.out.println("Réussite de l'opération");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
