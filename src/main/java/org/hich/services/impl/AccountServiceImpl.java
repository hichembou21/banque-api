package org.hich.services.impl;

import org.hich.dao.ClientRepository;
import org.hich.dao.EmployeRepository;
import org.hich.dao.RoleRepository;
import org.hich.dao.UserRepository;
import org.hich.entities.AppRole;
import org.hich.entities.AppUser;
import org.hich.entities.Client;
import org.hich.entities.Employe;
import org.hich.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EmployeRepository employeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Client saveClient(Client client) {
		String hashPW = bCryptPasswordEncoder.encode(client.getPassword());
		client.setPassword(hashPW);
		return clientRepository.save(client);
	}
	
	@Override
	public Employe saveEmploye(Employe employe) {
		String hashPW = bCryptPasswordEncoder.encode(employe.getPassword());
		employe.setPassword(hashPW);
		return employeRepository.save(employe);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {

		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
	}

	@Override
	public AppUser findUserByUsername(String username) {
		
			return userRepository.findByUsername(username);
	}
		

}
