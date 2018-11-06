package org.hich.metier.impl;

import java.util.Date;

import org.hich.dao.CompteRepository;
import org.hich.dao.EmployeRepository;
import org.hich.dao.OperationRepository;
import org.hich.entities.Compte;
import org.hich.entities.Employe;
import org.hich.entities.Operation;
import org.hich.entities.Retrait;
import org.hich.entities.Versement;
import org.hich.metier.OperationMetier;
import org.hich.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationMetierImpl implements OperationMetier {
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private CompteRepository CompteRepository;
	
	@Autowired
	private EmployeRepository employeRepository;
	
	@Override
	@Transactional
	public boolean crediter(String codeCompte, double montant, Long codeEmploye) {
		
		Compte compte = CompteRepository.findById(codeCompte).orElse(null);
		Employe employe = employeRepository.findById(codeEmploye).orElse(null);
		
		if (compte != null && employe != null) {
			Operation op = new Versement();
			op.setDateOperation(new Date());
			op.setMontant(montant);
			op.setCompte(compte);
			op.setEmploye(employe);
			operationRepository.save(op);
			compte.setSolde(compte.getSolde() + montant);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean debiter(String codeCompte, double montant, Long codeEmploye) {
		
		Compte compte = CompteRepository.findById(codeCompte).orElse(null);
		Employe employe = employeRepository.findById(codeEmploye).orElse(null);
		
		if (compte != null && employe != null) {
			
			if (compte.getSolde() < montant) throw new RuntimeException("Solde Insuffisant");
			
			Operation op = new Retrait();
			op.setDateOperation(new Date());
			op.setMontant(montant);
			op.setCompte(compte);
			op.setEmploye(employe);
			operationRepository.save(op);
			compte.setSolde(compte.getSolde() - montant);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean virement(String codeCompte1, String codeCompte2, double montant, Long codeEmploye) {

		debiter(codeCompte1, montant, codeEmploye);
		crediter(codeCompte2, montant, codeEmploye);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public PageOperation listOperations(String codeCompte, int page, int size) {
		
		Page<Operation> operations = operationRepository.getOperationsOfCompte(codeCompte, new PageRequest(page, size));
		PageOperation pageOperation = new PageOperation();
		pageOperation.setOperations(operations.getContent());
		pageOperation.setNombreOpsPerPage(operations.getNumberOfElements());
		pageOperation.setNumeroPage(operations.getNumber());
		pageOperation.setTotalPages(operations.getTotalPages());
		pageOperation.setTotalOps((int) operations.getTotalElements());
		
		return pageOperation;
	}

}
