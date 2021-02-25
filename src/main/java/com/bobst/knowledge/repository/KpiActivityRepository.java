package com.bobst.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bobst.knowledge.models.KPI_ACTIVITY;

public interface KpiActivityRepository  extends JpaRepository<KPI_ACTIVITY,Integer> {
	
	// contributions = -1 : NbrActiveUsers
	// contributions =  0 : NbrContributingUsers
	@Query(
			value = "SELECT COUNT(DISTINCT EMAIL) FROM KPI_ACTIVITY a WHERE a.PERIOD = :period AND a.CONTRIBUTIONS > :contributions",
			nativeQuery = true)
			Integer findNbrActiveUsers(@Param("period") String period, @Param("contributions") Integer contributions);
	
	
	@Query(
			value = "select sum(views) from KPI_ACTIVITY where period = :period",
			nativeQuery = true)
			Integer findNbrViews(@Param("period") String period);
	
	@Query(
			value = "select sum(contributions) from KPI_ACTIVITY where period = :period",
			nativeQuery = true)
			Integer findNbrContributions(@Param("period") String period);
	
	@Query(
			value = "select sum(kba)+ sum(wikis) from KPI_ACTIVITY where period = :period",
			nativeQuery = true)
			Integer findNbrNewArticles(@Param("period") String period);
	
	
}
