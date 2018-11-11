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

	@RequestMapping(value="/operations/crediter", method=RequestMethod.PUT)
	public boolean crediter(
			@RequestParam String codeCompte, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.crediter(codeCompte, montant, employe);
	}

	@RequestMapping(value="/operations/debiter", method=RequestMethod.PUT)
	public boolean debiter(
			@RequestParam String codeCompte, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.debiter(codeCompte, montant, employe);
	}

	@RequestMapping(value="/client/operations/virement", method=RequestMethod.PUT)
	public boolean virement(
			@RequestParam String codeCompte,
			@RequestParam String codeCompte2, 
			@RequestParam double montant, 
			@RequestParam Long employe) {
		return operationMetier.virement(codeCompte, codeCompte2, montant, employe);
	}

//	@RequestMapping(value="/client/operations/virement", method=RequestMethod.PUT)
//	public boolean virementFromClient(
//			@RequestParam String codeCompte,
//			@RequestParam String codeCompte2,
//			@RequestParam double montant,
//			@RequestParam Long employe) {
//		return operationMetier.virement(codeCompte, codeCompte2, montant, employe);
//	}

}
