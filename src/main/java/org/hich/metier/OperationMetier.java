package org.hich.metier;

public interface OperationMetier {
		
	public boolean crediter(String codeCompte, double montant, Long employe);
	public boolean debiter(String codeCompte, double montant, Long employe);
	public boolean virement(String codeCompte1, String codeCompte2, double montant, Long employe);
	public PageOperation listOperations(String codeCompte, int page, int size); 

}