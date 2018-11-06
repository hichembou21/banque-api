package org.hich.metier.impl;

import java.util.Date;

import org.hich.dao.CompteRepository;
import org.hich.entities.Compte;
import org.hich.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {

	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public Compte addCompte(Compte compte) {
		
		compte.setDateCreation(new Date());
		return compteRepository.save(compte);
	}

	@Override
	public Compte getCompte(String code) {
		
		return compteRepository.findById(code).orElse(null);
	}
	
	

}
