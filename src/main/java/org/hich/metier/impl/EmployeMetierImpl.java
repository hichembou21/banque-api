package org.hich.metier.impl;

import java.util.List;

import org.hich.dao.EmployeRepository;
import org.hich.entities.Employe;
import org.hich.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeMetierImpl implements EmployeMetier {

	@Autowired
	private EmployeRepository employRepository;
	
	@Override
	public Employe addEmploye(Employe employe) {
		
		return employRepository.save(employe);
	}

	@Override
	public List<Employe> listEmploye() {
		
		return employRepository.findAll();
	}

	@Override
	public Employe getEmployeById(Long id) {
		// TODO Auto-generated method stub
		return employRepository.findById(id).orElse(null);
	}
	

}
