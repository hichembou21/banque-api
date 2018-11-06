package org.hich.metier.impl;

import java.util.List;
import org.hich.dao.ClientRepository;
import org.hich.entities.Client;
import org.hich.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMetierImpl implements ClientMetier {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@Override
	public Client addClient(Client client) {

		return clientRepository.save(client);
	}

	@Override
	public List<Client> listClient() {
		
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public Client getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}
}
