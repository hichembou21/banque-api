package org.hich.metier;

import java.util.List;

import org.hich.entities.Employe;

public interface EmployeMetier {
	
	public Employe addEmploye(Employe employe);
	
	public List<Employe> listEmploye();
	
	public Employe getEmployeById(Long id);

	

}
