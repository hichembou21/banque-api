package org.hich.services;

import java.util.List;

import org.hich.entities.Employe;
import org.hich.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/employes")
public class EmployeRestService {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private EmployeMetier employeMetier;

	@RequestMapping(method=RequestMethod.POST)
	public Employe addEmploye(@RequestBody Employe employe) {
		return accountService.saveEmploye(employe);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Employe> listEmploye() {
		return employeMetier.listEmploye();
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public Employe getEmploye(@PathVariable String username) {
		return (Employe)accountService.findUserByUsername(username);
	}
	
	
	
}
