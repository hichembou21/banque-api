package org.hich;

//import org.hich.dao.CompteRepository;
//import org.hich.dao.OperationRepository;
import org.hich.entities.AppRole;
import org.hich.entities.Client;
import org.hich.entities.Compte;
import org.hich.entities.CompteCourant;
import org.hich.entities.CompteEpargne;
import org.hich.entities.Employe;
import org.hich.metier.CompteMetier;
import org.hich.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import java.util.Date;
//
//import org.hich.dao.ClientRepository;
//import org.hich.dao.CompteRepository;
//import org.hich.dao.OperationRepository;
//import org.hich.entities.Client;
//import org.hich.entities.Compte;
//import org.hich.entities.CompteCourant;
//import org.hich.entities.CompteEpargne;
//import org.hich.entities.Retrait;
//import org.hich.entities.Versement;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootApplication
@SpringBootApplication
public class BanqueOnLigneApplication implements CommandLineRunner {
	
//	@Autowired
//	private ClientRepository clientRepository;
//	
	@Autowired
	private CompteMetier compteMetier;
	
//	@Autowired
//	private OperationRepository operationRepository;
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BanqueOnLigneApplication.class, args);
		
	}
	
	@Bean
	public BCryptPasswordEncoder getBCriptPE() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public ObjectMapper getObjectMaper() {
//		return new ObjectMapper();
//	}

	@Override
	public void run(String... args) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		accountService.saveEmploye(new Employe("admin", "admin", "1234", null));
		accountService.saveClient(new Client("hichem", "hichem", "1234", dateFormat.parse("27/03/1982"), "hichem@gmail.com"));
		accountService.saveClient(new Client("samir", "samir", "1234", dateFormat.parse("27/03/1975"), "samir@gmail.com"));
		accountService.saveClient(new Client("hich", "hich", "1234", dateFormat.parse("27/03/1982"), "ggg@gmail.com"));

		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "EMPLOYE"));
		accountService.saveRole(new AppRole(null, "USER"));
		
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "EMPLOYE");
		accountService.addRoleToUser("admin", "USER");
		
		accountService.addRoleToUser("hichem", "EMPLOYE");
		accountService.addRoleToUser("hichem", "USER");
		accountService.addRoleToUser("samir", "USER");

//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		/*Client c1 = clientRepository.save(new Client("bobo", "hichem", dateFormat.parse("27/03/1982"), "hichem@gmail"));
		Client c2 = clientRepository.save(new Client("brahimi", "yacine", dateFormat.parse("14/05/1989"), "yacine@gmail"));
		Client c3 = clientRepository.save(new Client("mahrez", "riyad", dateFormat.parse("17/06/1990"), "riyad@gmail"));*/
		
		Compte cpt1 = new CompteCourant("cc1", 1000, (Client) accountService.findUserByUsername("hichem"), 500);
		compteMetier.addCompte(cpt1);
		Compte cpt2 = new CompteCourant("cc2", 400, (Client) accountService.findUserByUsername("hichem"), 200);
		compteMetier.addCompte(cpt2);		
		Compte cpt3 = new CompteCourant("cc3", 200, (Client) accountService.findUserByUsername("samir"), 400);
		compteMetier.addCompte(cpt3);		
		Compte cpt4 = new CompteEpargne("cc4", 0, (Client) accountService.findUserByUsername("hich"), 2.5);
		compteMetier.addCompte(cpt4);

//
//		operationRepository.save(new Versement(new Date(), 1000, cpt1));
//		operationRepository.save(new Versement(new Date(), 500, cpt2));
//		operationRepository.save(new Versement(new Date(), 1000, cpt3));
//		operationRepository.save(new Retrait(new Date(), 200, cpt3));
//		operationRepository.save(new Retrait(new Date(), 100, cpt4));
	}
}
