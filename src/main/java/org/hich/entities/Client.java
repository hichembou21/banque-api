package org.hich.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Client extends AppUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(unique=true)
	private String email;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String nom, String username, String password, Date dateNaissance, String email) {
		super(nom,username, password);
		
		this.dateNaissance = dateNaissance;
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public Collection<Compte> getComptes() {
		return comptes;
	}

	@JsonSetter
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
		

}
