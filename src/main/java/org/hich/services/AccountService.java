package org.hich.services;

import org.hich.entities.AppRole;
import org.hich.entities.AppUser;
import org.hich.entities.Client;
import org.hich.entities.Employe;

public interface AccountService {
	
	public Client saveClient(Client client);
	public Employe saveEmploye(Employe employe);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUsername(String username);	
}
