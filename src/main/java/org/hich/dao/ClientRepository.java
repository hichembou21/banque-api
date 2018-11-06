package org.hich.dao;

import org.hich.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Client findByEmail(String email);
	
}

