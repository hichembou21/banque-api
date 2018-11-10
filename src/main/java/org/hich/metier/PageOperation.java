package org.hich.metier;

import java.io.Serializable;
import java.util.List;

import org.hich.entities.Operation;

public class PageOperation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Operation> operations;
	private int numeroPage;
	private int nombreOpsPerPage;
	private int totalOps;
	
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public int getNumeroPage() {
		return numeroPage;
	}
	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}
	public int getNombreOpsPerPage() {
		return nombreOpsPerPage;
	}
	public void setNombreOpsPerPage(int nombreOpsPerPage) {
		this.nombreOpsPerPage = nombreOpsPerPage;
	}
	public int getTotalOps() {
		return totalOps;
	}
	public void setTotalOps(int totalOps) {
		this.totalOps = totalOps;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	private int totalPages;
		

}
