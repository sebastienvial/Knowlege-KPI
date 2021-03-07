package com.bobst.knowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bobst.knowledge.models.KPI_USER;


public interface KpiRepository extends JpaRepository<KPI_USER,Integer> {
	
	@Query(
			value = "SELECT * FROM KPI_USER WHERE STATUS_GLOBAL = 'Active'",
			nativeQuery = true)
	List<KPI_USER> findAllActiveUserGlobal();
	
	@Query(
			value = "SELECT * FROM KPI_USER WHERE STATUS_GLOBAL = 'Active' AND LOCATION_ID='1210' ORDER BY email",
			nativeQuery = true)
	List<KPI_USER> findUserTest();
	
	@Query(
			value = "SELECT * FROM KPI_USER WHERE UPPER(EMAIL) = Upper(:email)",
			nativeQuery = true)
	KPI_USER findUserByEmail(@Param("email") String email);	
	
	
	@Query(
			value = "SELECT COUNT(*) FROM KPI_USER WHERE STATUS_GLOBAL = 'Active'",
			nativeQuery = true)
	Integer findNbrActiveUserGlobal();
	
	@Query(
			value = "SELECT COUNT(*) FROM KPI_USER WHERE STATUS_KNOWLEDGE = 'Active'",
			nativeQuery = true)
	Integer findNbrConnectedUsers();
	
	@Query(
			value = "SELECT COUNT(u.email) FROM KPI_USER u, KPI_ENTITY, e WHERE u.LOCATION_ID=e.LOCATION_ID and e.SITE='SAM' and STATUS_KNOWLEDGE = 'Active'",
			nativeQuery = true)
	Integer findNbrConnectedUsersBySite();
	
	
	@Query(
			value = "SELECT DISTINCT(PRIMARY_PL) FROM KPI_USER ORDER BY PRIMARY_PL",
			nativeQuery = true)
	List<String> findAllPrimaryPl();
	
	@Query(
			value = "SELECT DISTINCT(POSITION) FROM KPI_USER ORDER BY POSITION",
			nativeQuery = true)
	List<String> findAllPosition();
	
	@Query(
			value = "SELECT DISTINCT(EMAIL) FROM KPI_USER ORDER BY EMAIL",
			nativeQuery = true)
	List<String> findAllEmail();
	
	
	
	
	
	
}
