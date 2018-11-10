package org.hich.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employe extends AppUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="CODE_EMP_SUP")
	private Employe employeSup;
	
	@ManyToMany
	@JoinTable(name="EMPLOYES_GROUPS")
	private Collection<Groupe> groupes;

	public Employe getEmployeSup() {
		return employeSup;
	}

	public void setEmployeSup(Employe employeSup) {
		this.employeSup = employeSup;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}
	
	public Employe() {
		super();
	}

	public Employe(String name, String username, String password, Employe employeSup) {
		super(name, username, password);
		this.employeSup = employeSup;
	}

}
