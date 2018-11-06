package org.hich.metier;

import java.util.List;
import org.hich.entities.Client;

public interface ClientMetier {
	
	public Client addClient(Client client);
	public List<Client> listClient();  
	public Client getClientById(Long id);
	public Client getClientByEmail(String email);

}
