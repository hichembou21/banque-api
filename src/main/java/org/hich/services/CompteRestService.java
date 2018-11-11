package org.hich.services;

import org.hich.entities.Compte;
import org.hich.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
public class CompteRestService {

	@Autowired
	private CompteMetier compteMetier;

	@RequestMapping(value="/client/comptes/{code}/{codeClient}", method=RequestMethod.GET)
	public Compte getCompteForClient(@PathVariable String code, @PathVariable Long codeClient) {
		return compteMetier.getCompteForClient(code, codeClient);
	}

	@RequestMapping(value="/comptes/{codeClient}", method=RequestMethod.POST)
	public Compte addCompte(@RequestBody Compte compte, @PathVariable Long codeClient) {
		return compteMetier.addCompte(compte, codeClient);
	}

	@RequestMapping(value="/comptes/{code}", method=RequestMethod.GET)
	public Compte getCompte(@PathVariable String code) {
		return compteMetier.getCompte(code);
	}

}
