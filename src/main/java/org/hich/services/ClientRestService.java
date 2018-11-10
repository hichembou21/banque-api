package org.hich.services;

import java.util.List;

import org.hich.entities.AppUser;
import org.hich.entities.Client;
import org.hich.metier.ClientMetier;
import org.hich.services.util.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
@RequestMapping(value="/clients")
public class ClientRestService {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ClientMetier clientMetier;
		
	@RequestMapping(method=RequestMethod.POST)
	public Client addClient(@RequestBody RegisterForm userForm) {
		
		if (!userForm.getPassword().equals(userForm.getConfirmPassword()))
			throw new RuntimeException("You Must Comfirm the password");
		
		AppUser user = accountService.findUserByUsername(userForm.getUsername());
		if (user != null) throw new RuntimeException("User already exist");
        
		if (clientMetier.getClientByEmail(userForm.getEmail()) != null)
			throw new RuntimeException("Email already Used");
		
		Client client = new Client();
		client.setName(userForm.getName());
		client.setUsername(userForm.getUsername());
		client.setPassword(userForm.getPassword());
		client.setDateNaissance(userForm.getBirthdate());
		client.setEmail(userForm.getEmail());
		accountService.saveClient(client);
		accountService.addRoleToUser(userForm.getUsername(), "User");
		return client;
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Client> listClient() {
		return clientMetier.listClient();
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public AppUser getClient(@PathVariable String username) {
		return accountService.findUserByUsername(username);
	}

}
