package org.hich.services;

import java.util.List;

import org.hich.entities.AppUser;
import org.hich.entities.Client;
import org.hich.entities.Employe;
import org.hich.metier.EmployeMetier;
import org.hich.services.util.EmployeForm;
import org.hich.services.util.RegisterForm;
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
	public Employe addEmploye(@RequestBody EmployeForm employeForm) {
		if (!employeForm.getPassword().equals(employeForm.getConfirmPassword()))
			throw new RuntimeException("You Must Comfirm the password");

		AppUser user = accountService.findUserByUsername(employeForm.getUsername());
		if (user != null) throw new RuntimeException("User already exist");

		Employe employe = new Employe();
		employe.setName(employeForm.getName());
		employe.setUsername(employeForm.getUsername());
		employe.setPassword(employeForm.getPassword());
		employe.setEmployeSup(employeForm.getEmployeSup());
		accountService.saveEmploye(employe);
		accountService.addRoleToUser(employeForm.getUsername(), "EMPLOYE");
		accountService.addRoleToUser(employeForm.getUsername(), "USER");
		return employe;
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
