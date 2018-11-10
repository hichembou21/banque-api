package org.hich.services;

import org.hich.metier.OperationMetier;
import org.hich.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
@RequestMapping(value="/operations")
public class OperationRestService {

	@Autowired
	private OperationMetier operationMetier;

	@RequestMapping(method=RequestMethod.GET) 
	public PageOperation listOperations(
			@RequestParam String codeCompte, 
			@RequestParam int page, 
			@RequestParam int size) {
		return operationMetier.listOperations(codeCompte, page, size);
	}

	@RequestMapping(value="/crediter", method=RequestMethod.PUT) 
	public boolean crediter(
			@RequestParam String codeCompte, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.crediter(codeCompte, montant, employe);
	}

	@RequestMapping(value="/debiter", method=RequestMethod.PUT)
	public boolean debiter(
			@RequestParam String codeCompte, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.debiter(codeCompte, montant, employe);
	}

	@RequestMapping(value="/virement", method=RequestMethod.PUT)
	public boolean virement(
			@RequestParam String codeCompte1, 
			@RequestParam String codeCompte2, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.virement(codeCompte1, codeCompte2, montant, employe);
	}
	
	

}
