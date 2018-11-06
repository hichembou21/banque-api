package org.hich.metier;

import org.hich.entities.Compte;

public interface CompteMetier {
	
	public Compte addCompte(Compte compte);
	public Compte getCompte(String code);

}
