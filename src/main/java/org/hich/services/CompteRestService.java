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
@RequestMapping(value="/comptes")
public class CompteRestService {

	@Autowired
	private CompteMetier compteMetier;

	@RequestMapping(value="/{codeClient}", method=RequestMethod.POST)
	public Compte addCompte(@RequestBody Compte compte, @PathVariable Long codeClient) {
		return compteMetier.addCompte(compte, codeClient);
	}

	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public Compte getCompte(@PathVariable String code) {
		return compteMetier.getCompte(code);
	}
	
	
}
