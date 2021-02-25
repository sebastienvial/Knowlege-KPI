package com.bobst.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bobst.knowledge.models.KPI_ROLE;

public interface KpiRoleRepository extends JpaRepository<KPI_ROLE,Integer> {
	
	@Query(
			value = "SELECT ROLE FROM KPI_ROLE WHERE EMAIL= :email",
			nativeQuery = true)
	String findRoleByEmail(@Param("email") String email);
}
