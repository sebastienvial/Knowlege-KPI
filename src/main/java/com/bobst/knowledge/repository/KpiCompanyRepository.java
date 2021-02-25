package com.bobst.knowledge.repository;

import java.util.List;
import com.bobst.knowledge.models.KPI_COMPANY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KpiCompanyRepository extends JpaRepository<KPI_COMPANY,Integer> {
	@Query(
			value = "SELECT DISTINCT(SITE) FROM KPI_COMPANY ORDER BY SITE",
			nativeQuery = true)
	List<String> findAllSite();
	
	@Query(
			value = "SELECT LOCATION_ID FROM KPI_COMPANY WHERE SITE= :site",
			nativeQuery = true)
	String findLocationIdFromSite(@Param("site") String site);
	
	@Query(
			value = "SELECT SITE FROM KPI_COMPANY WHERE LOCATION_ID= :locationId",
			nativeQuery = true)
	String findSiteFromLocationId(@Param("locationId") String locationId);

}
