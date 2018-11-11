package org.hich.metier.impl;

import java.util.Date;

import org.hich.dao.ClientRepository;
import org.hich.dao.CompteRepository;
import org.hich.entities.Client;
import org.hich.entities.Compte;
import org.hich.metier.CompteMetier;
import org.hich.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Compte addCompte(Compte compte, Long codeClient) {

		Client client = clientRepository.findById(codeClient).orElse(null);
		compte.setClient(client);
		compte.setDateCreation(new Date());
		return compteRepository.save(compte);
	}

	@Override
	public Compte getCompte(String code) {

		Compte compte = compteRepository.findById(code).orElse(null);
		if (compte == null) throw new RuntimeException("Compte inexistant !!");
		return compte;
	}
	
	

}
