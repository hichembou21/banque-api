package org.hich.dao;

import org.hich.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select op from Operation op where op.compte.Code=:x")
	public Page<Operation> getOperationsOfCompte(@Param("x") String CodeCompte, Pageable pageable);
	
}
