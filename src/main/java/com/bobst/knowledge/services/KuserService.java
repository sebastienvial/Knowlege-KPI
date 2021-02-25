package com.bobst.knowledge.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobst.knowledge.models.DataPeriod;
import com.bobst.knowledge.models.KPI_USER;
import com.bobst.knowledge.models.UserFull;
import com.bobst.knowledge.repository.KpiActivityRepository;
import com.bobst.knowledge.repository.KpiCompanyRepository;
import com.bobst.knowledge.repository.KpiRepository;

@Service
public class KuserService {
	
	@Autowired
	public KpiRepository repUser;
	
	@Autowired
	public KpiActivityRepository repActivity;
	
	@Autowired
	public KpiCompanyRepository repCompany;
	
	@Autowired
	EntityManagerFactory emf;

	public List<KPI_USER> getAllUsers() {
		return repUser.findAll();
	}
	
	public List<KPI_USER> getAllActiveUsers() {
		return repUser.findAllActiveUserGlobal();
	}
	
	public List<KPI_USER> getUsersTest() {
		return repUser.findUserTest();
	}
	
	public KPI_USER getUserByEmail(String email) {
		return repUser.findUserByEmail(email);
	}
	
	public List<String> getAllPrimaryPl() {
		return repUser.findAllPrimaryPl();
	}
	
	public List<String> getAllPosition() {
		return repUser.findAllPosition();
	}
	
	public List<String> getAllSite() {
		return repCompany.findAllSite();
	}
	
	public String getLocationIdFromSite(String site) {
		return repCompany.findLocationIdFromSite(site);
	}
	
	public String getSiteFromLocationId(String locationId) {
		return repCompany.findSiteFromLocationId(locationId);
	}
	
	private String completeSql(String sql, String typeU, String site, String pl) {
		String sqlC = sql;
		if (!site.equals("ALL")) {
			sqlC = sqlC + " and site='" + site + "'";
		}
		
		if (!pl.equals("ALL")) {
			sqlC = sqlC +  " and primary_pl='" + pl + "'";
		}
		
		if (typeU.equals("UserActive")) {
			sqlC = sqlC +  " and (views>0 or contributions>0)";
		}
		
		if (typeU.equals("UserContributing")) {
			sqlC = sqlC +  " and contributions>0";
		}
		
		return sqlC;
	}
	
	private String completeSql(String sql, String site, String pl) {
		String sqlC = sql;
		if (!site.equals("ALL")) {
			sqlC = sqlC + " and site='" + site + "'";
		}
		
		if (!pl.equals("ALL")) {
			sqlC = sqlC +  " and primary_pl='" + pl + "'";
		}		
		return sqlC;
	}
	
	public Integer getNbrActiveUsers(String period, Integer c, String site, String pl) {
		Integer res = 0;
		if (site.equals("ALL")) {
			res = repActivity.findNbrActiveUsers(period, c); 
		} else {
			res = repUser.findNbrConnectedUsersBySite();
		}
		return res;
	} 
	
	
	public List<UserFull> getUsersFull(String period, String typeU, String site, String pl) {
		EntityManager em = emf.createEntityManager();
		String sql = "Select NEW com.bobst.knowledge.models.UserFull(u.email, c.site, u.position, u.primaryPl, a.VIEWS, a.CONTRIBUTIONS, a.KBA, a.WIKIS) from KPI_ACTIVITY a, KPI_USER u, KPI_COMPANY c where c.locationId = u.locationId and Upper(a.EMAIL) = upper(u.email) and a.PERIOD like '" + period + "%'";
		
		sql = completeSql(sql,typeU,site,pl);
		TypedQuery query = em.createQuery(sql+ " ORDER BY a.WIKIS DESC, a.CONTRIBUTIONS DESC, a.VIEWS DESC", UserFull.class);
		
		List<UserFull> lst = query.getResultList();
		em.close();
		return lst;
		
	}
	
	
	
	public List<KPI_USER> getUsers(String period, String typeU, String site, String pl) {
		EntityManager em = emf.createEntityManager();
		String sql = "Select u from KPI_ACTIVITY a, KPI_USER u, KPI_COMPANY c where c.locationId = u.locationId and Upper(a.EMAIL) = upper(u.email) and a.PERIOD like '" + period + "%'";
		
		sql = completeSql(sql,typeU,site,pl);
		TypedQuery query = em.createQuery(sql, KPI_USER.class);
		
		List<KPI_USER> lst = query.getResultList();
		em.close();
		return lst;
		
	}
	
	
	public Integer getNbrUsers(String period, String typeU, String site, String pl) {
		EntityManager em = emf.createEntityManager();
		String sql = "Select count(distinct u.email) from kpi_activity a, kpi_user u, kpi_company c where c.location_ID = u.location_ID and Upper(a.email) = upper(u.email) and a.period like '" + period + "%'";
		
		sql = completeSql(sql,typeU,site,pl);
		
		Query query = em.createNativeQuery(sql);
		Integer res=0;
		if (query.getSingleResult()!=null)
			res = Integer.parseInt(query.getSingleResult().toString());
		
		em.close();
		return res;
	}
	
	
	public Integer getNbrOf(String period, String typeS, String site, String pl) {
		
		Integer res=0;
		
		if (typeS.contains("User")) {
		   res = getNbrUsers(period, typeS,site,pl);	
		} else {
			
			EntityManager em = emf.createEntityManager();
			if (typeS.equals("articles")) 
				typeS = "kba) + sum(a.wikis";
			
			String sql = "Select sum(a." + typeS + ") from kpi_activity a, kpi_user u, kpi_company c where c.location_ID = u.location_ID and Upper(a.email) = upper(u.email) and a.period like '" + period + "%'";
			//String sql = "Select count(a." + typeS + ") from kpi_activity a, kpi_user u, kpi_company c where c.location_ID = u.location_ID and Upper(a.email) = upper(u.email) and a.period like '" + period + "%'";
			
			//String sql = "Select count(*) from kpi_User";
			sql = completeSql(sql,site,pl);
			
			Query query = em.createNativeQuery(sql);
			
			if (query.getSingleResult()!=null)
				res = Integer.parseInt(query.getSingleResult().toString());
			em.close();
		}
		
		return res;
		
	}
	
	public Integer getNbrOfContributions(String period) {
		return repActivity.findNbrContributions(period);
	}
	
	public Integer getNbrOfNewArticles(String period) {
		return repActivity.findNbrNewArticles(period);
	}

	
	
	public List<Object[]> getNbrOfYears(String annee, String typeS, String site, String pl) {

		if (typeS.equals("articles")) 
			typeS = "kba) + sum(a.wikis";
		
			
		EntityManager em = emf.createEntityManager();
		String sql = "Select a.period, sum(a." + typeS + ") from kpi_activity a, kpi_user u, kpi_company c where c.location_ID = u.location_ID and Upper(a.email) = upper(u.email) and period like '" + annee + "%' ";
		sql = completeSql(sql,site,pl);
		sql = sql + "  group by a.period order by a.period";
		
		Query query = em.createNativeQuery(sql);
		
		List<Object[]> lst = query.getResultList();
		em.close();
		return lst;
		
	}
	
	
	public List<Object[]> getNbrUsersYears(String annee, String typeU, String site, String pl) {
		EntityManager em = emf.createEntityManager();
		String sql = "Select a.period, count(distinct u.email) from kpi_activity a, kpi_user u, kpi_company c where c.location_ID = u.location_ID and Upper(a.email) = upper(u.email) and a.period like '" + annee + "%' ";
		sql = completeSql(sql,typeU,site,pl);
		sql = sql + "  group by a.period order by a.period";
		
		Query query = em.createNativeQuery(sql);
		
		List<Object[]> lst = query.getResultList();
		em.close();
		return lst;
	}
	
	
	
	

}
