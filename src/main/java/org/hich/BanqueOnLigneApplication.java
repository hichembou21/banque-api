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
		Employe e1 = (Employe) accountService.findUserByUsername("admin");

		accountService.saveEmploye(new Employe("hichem", "hichem", "1234",e1));
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

		Employe c1 = (Employe) accountService.findUserByUsername("hichem");
		Client c2 = (Client) accountService.findUserByUsername("samir");
		Client c3 = (Client) accountService.findUserByUsername("hich");

		Compte cpt1 = new CompteCourant("cc1", 1000, 500, e1);
		compteMetier.addCompte(cpt1, c1.getId());
		Compte cpt2 = new CompteCourant("cc2", 400, 200, e1);
		compteMetier.addCompte(cpt2, c1.getId());
		Compte cpt3 = new CompteCourant("cc3", 200, 400, e1);
		compteMetier.addCompte(cpt3, c2.getId());
		Compte cpt4 = new CompteEpargne("ce4", 100, 2.5, e1);
		compteMetier.addCompte(cpt4, c3.getId());


	}
}
