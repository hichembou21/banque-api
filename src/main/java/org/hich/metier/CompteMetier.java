package org.hich.metier;

import org.hich.entities.Compte;

public interface CompteMetier {
	
	public Compte addCompte(Compte compte, Long codeClient);
	public Compte getCompte(String code);
	public Compte getCompteForClient(String code, Long codeClient);

}
